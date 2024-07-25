import scrapy
from bs4 import BeautifulSoup, Tag

from wikiscraper.markdown_writer import MarkdownWriter

TARGET_DIR = '/home/andre/apps/aiwiki/Bs4'


class Bs4Spider(scrapy.Spider):
    name = "bs4"
    start_urls = ["https://www.crummy.com/software/BeautifulSoup/bs4/doc"]

    def parse(self, response):
        soup = BeautifulSoup(response.text, parser='lxml')

        for section in soup.select_one('div.body').find_all('section', recursive=False):
            self.parse_section(section)

    def parse_section(self, section: Tag):
        page_name = next(section.h1.children)

        with MarkdownWriter(f'{TARGET_DIR}/{page_name}.md') as markdown:
            for child in section.children:
                markdown.write_html_element(child)
