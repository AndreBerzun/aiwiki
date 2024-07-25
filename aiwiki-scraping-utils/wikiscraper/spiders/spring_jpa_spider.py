import scrapy
from bs4 import BeautifulSoup

from wikiscraper.markdown_writer import MarkdownWriter

TARGET_DIR = '/home/andre/apps/aiwiki/SpringJPA'


class SpringJpaSpider(scrapy.Spider):
    name = 'spring-jpa'
    start_urls = [
        'https://docs.spring.io/spring-data/jpa/reference/data-commons/index.html',
        'https://docs.spring.io/spring-data/jpa/reference/index.html'
    ]

    def parse(self, response):
        soup = BeautifulSoup(response.text, parser='lxml')
        page_name = soup.select_one('h1#page-title').text

        with MarkdownWriter(f'{TARGET_DIR}/{page_name}.md') as markdown:
            for element in soup.select_one('div.doc article').children:
                markdown.write_html_element(element)
        yield from response.follow_all(response.css('nav ul.nav-list li a'), callback=self.parse)
