import scrapy
from bs4 import BeautifulSoup

from wikiscraper.markdown_writer import MarkdownWriter

TARGET_DIR = '/home/andre/apps/aiwiki/Oblique/components'


class ObliqueSpider(scrapy.Spider):
    """
    Doesn't work yet. Oblique seems to block bots and I haven't spent time figuring out how to overcome this
    """

    name = "oblique"
    allowed_domains = ["oblique.bit.admin.ch"]
    start_urls = ["https://oblique.bit.admin.ch/components/alert"]

    def parse(self, response):
        soup = BeautifulSoup(response.text, parser='lxml')
        component_name = next(soup.select_one('h2.ui-component-title').stripped_strings).strip()

        custom_tags = ['od-ui-component-description, od-ui-component-api, od-ui-component-api-element']
        with MarkdownWriter(f'{TARGET_DIR}/{component_name}.md', custom_tags) as markdown:
            for child in soup.select_one('article.docs-article').children:
                if child.name in custom_tags:
                    markdown.write_html_element(child)

        yield from response.follow_all(response.css('ul.ob-nav-tree li a'), callback=self.parse)
