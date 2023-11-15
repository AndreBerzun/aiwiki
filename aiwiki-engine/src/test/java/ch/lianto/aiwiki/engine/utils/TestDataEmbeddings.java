package ch.lianto.aiwiki.engine.utils;

import ch.lianto.aiwiki.engine.entity.Page;
import ch.lianto.aiwiki.engine.entity.PageSegment;

public class TestDataEmbeddings {
    public final String emptyPrompt;
    public final String bestRockBandPrompt;
    public final Page pinkFloydPage;
    public final Page eloPage;
    private final TestData data;

    public TestDataEmbeddings(TestData data) {
        this.data = data;
        this.emptyPrompt = "";
        this.bestRockBandPrompt = "What is the best prog rock band?";
        this.pinkFloydPage = buildPinkFloydPage();
        this.eloPage = buildEloPage();
    }

    public Page buildPinkFloydPage() {
        Page page = new Page();
        page.setProject(data.projects.basic);
        page.setName("Pink Floyd");
        PageSegment segment = new PageSegment("Pink Floyd");
        segment.setEmbedding(new double[]{1, 1, 1});
        page.getPageSegments().add(segment);
        return page;
    }

    public Page buildEloPage() {
        Page page = new Page();
        page.setProject(data.projects.basic);
        page.setName("Electric Light Orchestra");
        PageSegment segment = new PageSegment("Electric Light Orchestra");
        segment.setEmbedding(new double[]{1, 1, 0.9});
        page.getPageSegments().add(segment);
        return page;
    }
}
