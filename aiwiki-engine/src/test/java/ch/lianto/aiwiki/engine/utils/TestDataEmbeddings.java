package ch.lianto.aiwiki.engine.utils;

import ch.lianto.aiwiki.engine.entity.Page;
import ch.lianto.aiwiki.engine.entity.PageChunk;

import java.util.List;

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
        Page page = new Page()
            .setName("Pink Floyd")
            .setProject(data.projects.basic);
        page.getChunks().add(
            new PageChunk()
                .setText("Pink Floyd")
                .setEmbeddings(List.of(
                    new double[]{1, 1, 0},
                    new double[]{1.1, 0.9, 0},
                    new double[]{1.2, 1, 0}
                ))
                .setPage(page)
        );
        return page;
    }

    public Page buildEloPage() {
        Page page = new Page()
            .setProject(data.projects.basic)
            .setName("Electric Light Orchestra");
        page.getChunks().add(
            new PageChunk()
                .setText("Electric Light Orchestra")
                .setEmbeddings(List.of(
                    new double[]{1, 1, 0.5},
                    new double[]{1.1, 0.9, 0.5},
                    new double[]{1.2, 1, 0.5}
                ))
                .setPage(page)
        );
        return page;
    }
}
