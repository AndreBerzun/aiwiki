import scrapy
from bs4 import BeautifulSoup, Tag

from benchmarkingscrapers.markdown_writer import MarkdownWriter

TARGET_DIR = '/home/andre/apps/aiwiki/Critrole'


class FandomSpider(scrapy.Spider):
    name = "fandom"
    allowed_domains = ["criticalrole.fandom.com"]
    start_urls = ["https://criticalrole.fandom.com/wiki/The_Draw_of_Destiny"]

    def parse(self, response):
        soup = BeautifulSoup(response.text, parser='lxml')
        title = next(soup.select_one('.page__main h1').stripped_strings).strip()
        episode_code = next(soup.select_one('div[data-source="EpNum"] abbr').stripped_strings).strip()

        start_parsing = False
        with MarkdownWriter(f'{TARGET_DIR}/{episode_code}-{title}.md') as markdown:
            for child in soup.select_one('div#content.page-content div.mw-parser-output').children:
                if isinstance(child, Tag) and child.name == 'h2':
                    if next(child.stripped_strings).strip() == 'Synopsis':
                        start_parsing = True
                    elif next(child.stripped_strings).strip() == 'Quotations':
                        start_parsing = False

                if start_parsing:
                    markdown.write_html_element(child)

        yield from response.follow_all(response.css('td[data-source="NextEp"] a'), callback=self.parse)
