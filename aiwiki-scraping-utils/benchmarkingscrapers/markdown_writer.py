from bs4 import NavigableString, Tag


class MarkdownWriter:
    CONTAINER_TAGS = ['div', 'section', 'article']

    def __init__(self, file_path: str, custom_container_tags: list[str] = []) -> None:
        super().__init__()
        self.file_path = file_path
        self.custom_container_tags = custom_container_tags

    def __enter__(self):
        self.file = open(self.file_path, 'w')
        return self

    def __exit__(self, exc_type, exc_val, exc_tb):
        self.file.close()

    def write_html_element(self, html):
        contents = self.to_string(html)

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
        elif html.name == 'ul':
            for item in html.find_all('li'):
                self.write_html_element(item)
        elif html.name == 'li':
            self.file.writelines(f'- {contents}\n')
        elif html.name == 'dl':
            self.write_description_list(html)
        elif html.name == 'table':
            for row in html.find_all('tr'):
                self.write_html_element(row)
                self.file.writelines('\n')
            self.file.writelines('\n')
        elif html.name in MarkdownWriter.CONTAINER_TAGS or html.name in self.custom_container_tags:
            for child in html.children:
                self.write_html_element(child)
            if html.name is not 'div':
                self.file.writelines('\n')
            return
        else:
            self.file.writelines(contents)

    def write_description_list(self, description_list: Tag):
        groups = self.group_by_description_title(description_list)
        for title, descriptions in groups.items():
            self.file.write(f'- {self.to_string(title)}: ')
            self.file.write(', '.join(self.to_string(desc) for desc in descriptions))
            self.file.write('\n')

    def group_by_description_title(self, description_list) -> dict[Tag, list[Tag]]:
        segments = {}
        current_key = None
        for element in description_list.children:
            if element.name == 'dt':
                current_key = element
                segments[current_key] = []
            elif element.name == 'dd':
                segments[current_key].append(element)

        return segments

    def to_string(self, html: Tag):
        return ' '.join(list(html.stripped_strings))
