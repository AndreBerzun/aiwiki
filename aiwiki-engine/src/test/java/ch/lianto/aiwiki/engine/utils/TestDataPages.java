package ch.lianto.aiwiki.engine.utils;

import ch.lianto.aiwiki.engine.entity.Page;
import ch.lianto.aiwiki.engine.entity.PageSegment;
import ch.lianto.aiwiki.engine.service.page.PageDto;

import java.util.stream.Stream;

public class TestDataPages {
    public final Page basic;
    public final PageDto basicDto;
    private final TestData data;

    public TestDataPages(TestData data) {
        this.data = data;
        this.basic = basicPage();
        this.basicDto = basicDto();
    }

    public Page basicPage() {
        Page page = new Page();
        page.setName("Page");
        page.setProject(data.projects.basic);
        Stream.of(
                "# Heading 1\nLorem ipsum dolores sit ammat.",
                "## Heading 2\nEven more content"
            ).map(PageSegment::new)
            .forEach(page.getPageSegments()::add);
        return page;
    }

    public PageDto basicDto() {
        return new PageDto(
            "Page",
            data.projects.basic.getName(),
            "# Heading 1\n\n" + TestUtils.loremIpsumWithWordCount(500) + "\n\n" +
                "# Heading 3\n\n" + TestUtils.loremIpsumWithWordCount(350) + "\n\n" +
                "# Heading 3\n\n" + TestUtils.loremIpsumWithWordCount(650)
        );
    }
}
