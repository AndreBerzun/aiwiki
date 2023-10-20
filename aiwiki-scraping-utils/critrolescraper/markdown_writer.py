from bs4 import NavigableString


class MarkdownWriter:

    def __init__(self, file_path: str) -> None:
        super().__init__()
        self.file_path = file_path

    def __enter__(self):
        self.file = open(self.file_path, 'w')
        return self

    def __exit__(self, exc_type, exc_val, exc_tb):
        self.file.close()

    def write_html_element(self, html):
        contents = ' '.join(list(html.stripped_strings))

        if isinstance(html, NavigableString) and html.strip() != '':
            self.file.write(html)
        elif html.name == 'p':
            self.file.writelines(f'{contents}\n\n')
        elif html.name == 'h1':
            self.file.writelines(f'# {contents}\n\n')
        elif html.name == 'h2':
            self.file.writelines(f'## {contents}\n\n')
        elif html.name == 'h3':
            self.file.writelines(f'### {contents}\n\n')
        elif html.name == 'h4':
            self.file.writelines(f'#### {contents}\n\n')
        elif html.name == 'h5':
            self.file.writelines(f'##### {contents}\n\n')
        elif html.name == 'h6':
            self.file.writelines(f'###### {contents}\n\n')
        elif html.name == 'img':
            self.file.writelines(f'![{html["alt"]}]({html["src"]})\n\n')
        elif html.name == 'figure':
            self.write_html_element(html.img)
        elif html.name == 'div':
            for child in html.children:
                self.write_html_element(child)
            return
        elif html.name == 'ul':
            for item in html.find_all('li'):
                self.write_html_element(item)
        elif html.name == 'li':
            self.file.writelines(f'- {contents}\n')
        else:
            self.file.writelines(contents)
