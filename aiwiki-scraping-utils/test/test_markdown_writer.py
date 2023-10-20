import os
import shutil
import unittest

from bs4 import BeautifulSoup

from markdown_writer import MarkdownWriter

TEST_TARGET_DIR = './tests'
TEST_FILE_NAME = f'{TEST_TARGET_DIR}/test.md'


class MarkdownWriterTest(unittest.TestCase):

    def setUp(self) -> None:
        super().setUp()
        os.makedirs(TEST_TARGET_DIR, exist_ok=True)

    def tearDown(self) -> None:
        super().tearDown()
        shutil.rmtree(TEST_TARGET_DIR)

    def test_create_empty_file(self):
        with MarkdownWriter(TEST_FILE_NAME):
            pass

        self.assertTrue(os.path.exists(TEST_FILE_NAME))
        with open(TEST_FILE_NAME) as file:
            self.assertTrue(len(file.read()) == 0)

    def test_write_paragraph(self):
        self._test_write_html(
            '<p>Test paragraph</p>',
            'Test paragraph'
        )

    def test_write_complex_paragraph(self):
        self._test_write_html(
            '''
            <p>
                Test paragraph.<br />
                <bold>Very bold <a href="https://google.com">element</a></bold>.
            </p>
            ''',
            'Test paragraph. Very bold element .'
        )

    def test_write_heading_1(self):
        self._test_write_html(
            '<h1>Test heading</h1>',
            '# Test heading'
        )

    def test_write_heading_2(self):
        self._test_write_html(
            '<h2>Test heading</h2>',
            '## Test heading'
        )

    def test_write_heading_3(self):
        self._test_write_html(
            '<h3>Test heading</h3>',
            '### Test heading'
        )

    def test_write_heading_4(self):
        self._test_write_html(
            '<h4>Test heading</h4>',
            '#### Test heading'
        )

    def test_write_heading_5(self):
        self._test_write_html(
            '<h5>Test heading</h5>',
            '##### Test heading'
        )

    def test_write_heading_6(self):
        self._test_write_html(
            '<h6>Test heading</h6>',
            '###### Test heading'
        )

    def test_write_img(self):
        self._test_write_html(
            '''<img src="https://google.com/image.jpg" alt="Alt Text">''',
            '![Alt Text](https://google.com/image.jpg)'
        )

    def test_write_figure(self):
        self._test_write_html(
            '''
            <figure>
                <img src="https://google.com/image.jpg" alt="Alt Text">
            </figure>
            ''',
            '![Alt Text](https://google.com/image.jpg)'
        )

    def test_write_div(self):
        self._test_write_html(
            '<div>Container</div>',
            'Container'
        )

    def test_write_complex_div(self):
        self._test_write_html(
            '''
            <div>
                <h1>The Test Story</h1>
                
                <p>There once was a test story about something that I don't want to imagine right now.</p>
            </div>
            ''',
            "# The Test Story\n\n"
            "There once was a test story about something that I don't want to imagine right now."
        )

    def test_write_ul(self):
        self._test_write_html(
            '''
            <ul>
                <li>Item 1</li>
                <li>Item 2</li>
                <li>Item 3</li>
            </ul>
            ''',
            '- Item 1\n'
            '- Item 2\n'
            '- Item 3'
        )

    def _test_write_html(self, html: str, expected_markdown: str):
        with MarkdownWriter(TEST_FILE_NAME) as markdown:
            soup = BeautifulSoup(html)
            markdown.write_html_element(next(soup.body.children))

        self.assertTrue(os.path.exists(TEST_FILE_NAME))
        with open(TEST_FILE_NAME) as file:
            self.assertEqual(file.read().strip(), expected_markdown)


if __name__ == '__main__':
    unittest.main()
