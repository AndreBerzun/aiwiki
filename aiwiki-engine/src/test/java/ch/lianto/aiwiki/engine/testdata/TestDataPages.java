package ch.lianto.aiwiki.engine.testdata;

import ch.lianto.aiwiki.engine.entity.Page;
import ch.lianto.aiwiki.engine.entity.PageSegment;

import java.util.stream.Stream;

public class TestDataPages {
    public final Page basic;
    private final TestData data;

    public TestDataPages(TestData data) {
        this.data = data;
        this.basic = basicPage();
    }

    public Page basicPage() {
        Page page = new Page();
        page.setName("Page");
        page.setProject(data.projects.basic);
        Stream.of(
            "# Heading 1\nLorem ipsum dolores sit ammat.",
            "## Heading 2\nEven more content"
        ).map(p -> {
            PageSegment segment = new PageSegment();
            segment.setText(p);
            return segment;
        }).forEach(page.getPageSegments()::add);
        return page;
    }
}
