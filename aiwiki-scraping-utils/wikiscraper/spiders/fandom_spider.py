import scrapy
from bs4 import BeautifulSoup, Tag

from wikiscraper.markdown_writer import MarkdownWriter

TARGET_DIR = '/home/andre/apps/aiwiki/Critrole'


class FandomSpider(scrapy.Spider):
    name = "fandom"
    allowed_domains = ["criticalrole.fandom.com"]
    start_urls = ["https://criticalrole.fandom.com/wiki/The_Draw_of_Destiny"]

    def parse(self, response):
        soup = BeautifulSoup(response.text, parser='lxml', features='lxml')
        title = next(soup.select_one('.page__main h1').stripped_strings).strip()
        episode_code = next(soup.select_one('div[data-source="EpNum"] abbr').stripped_strings).strip()

        start_parsing = False
        with MarkdownWriter(f'{TARGET_DIR}/{episode_code}-{title}.md') as markdown:
            for child in soup.select_one('div#content.page-content div.mw-parser-output').children:
                if isinstance(child, Tag) and child.name in ['h2', 'h3']:
                    header = next(child.stripped_strings).strip().lower()
                    if 'featured characters' in header:
                        start_parsing = False
                    elif 'previously on' in header or 'part ii' in header or 'part 2' in header:
                        start_parsing = True
                elif isinstance(child, Tag) and child.name == 'div':
                    if child.h3 and 'break' in next(child.h3.stripped_strings).strip().lower():
                        start_parsing = False

                if start_parsing:
                    markdown.write_html_element(child)

        yield from response.follow_all(response.css('td[data-source="NextEp"] a'), callback=self.parse)
