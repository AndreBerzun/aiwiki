package ch.lianto.aiwiki.engine.infrastructure.persistence;

import ch.lianto.aiwiki.engine.entity.Page;
import ch.lianto.aiwiki.engine.entity.PageChunk;
import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.policy.assistant.Similarity;
import ch.lianto.aiwiki.engine.repository.PageChunkRepository;
import ch.lianto.aiwiki.engine.repository.PageRepository;
import ch.lianto.aiwiki.engine.repository.ProjectRepository;
import ch.lianto.aiwiki.engine.utils.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PageChunkRepositoryTest {
    private TestData data;
    private PageChunkRepository chunkRepo;
    private ProjectRepository projectRepo;
    private PageRepository pageRepo;

    @BeforeEach
    void setUp() {
        data = new TestData();
        projectRepo = new InMemoryProjectRepository();
        pageRepo = new InMemoryPageRepository(projectRepo);
        chunkRepo = new InMemoryPageChunkRepository(projectRepo, (text, type) -> new double[]{1, 1, 0});
    }

    @Test
    void returnsEmptyListWhenEmptySearchText() {
        projectRepo.save(data.projects.basic);
        pageRepo.save(data.embeddings.pinkFloydPage);

        List<Similarity<PageChunk>> results = chunkRepo.findBySimilarity(data.embeddings.emptyPrompt);

        assertThat(results).isEmpty();
    }

    @Test
    void returnsEmptyListWhenNoPagesCreated() {
        projectRepo.save(data.projects.basic);

        List<Similarity<PageChunk>> results = chunkRepo.findBySimilarity(data.embeddings.bestRockBandPrompt);

        assertThat(results).isEmpty();
    }

    @Test
    void returnsPageThatMatchesPrompt() {
        Project project = projectRepo.save(data.projects.basic);
        Page relatedPage = pageRepo.save(data.embeddings.pinkFloydPage);

        List<Similarity<PageChunk>> results = chunkRepo.findBySimilarity(data.embeddings.bestRockBandPrompt);

        assertThat(results).hasSize(1);
        assertThat(results.stream().map(Similarity::data).toList()).isEqualTo(relatedPage.getChunks());
    }

    @Test
    void dontIncludeUnrelatedPages() {
        projectRepo.save(data.projects.basic);
        Page relatedPage = pageRepo.save(data.embeddings.pinkFloydPage);
        Page unrelatedPage = pageRepo.save(data.pages.basic);

        List<Similarity<PageChunk>> results = chunkRepo.findBySimilarity(data.embeddings.bestRockBandPrompt);

        assertThat(results).hasSize(1);
        assertThat(results.stream().map(Similarity::data).toList()).isEqualTo(relatedPage.getChunks());
    }

    @Test
    void orderMatchingPagesBySimilarity() {
        projectRepo.save(data.projects.basic);
        Page mostRelevant = pageRepo.save(data.embeddings.pinkFloydPage);
        Page relevant = pageRepo.save(data.embeddings.eloPage);
        pageRepo.save(data.pages.basic);

        List<Similarity<PageChunk>> results = chunkRepo.findBySimilarity(data.embeddings.bestRockBandPrompt);

        assertThat(results).hasSize(2);
        assertThat(results.get(0).data()).isEqualTo(mostRelevant.getChunks().get(0));
        assertThat(results.get(1).data()).isEqualTo(relevant.getChunks().get(0));
    }

    @Test
    void findOneMatchingChunkGivenQuote() {
        projectRepo.save(data.projects.basic);
        pageRepo.save(data.pages.basic);
        String quote = "Lorem";

        List<PageChunk> chunks = chunkRepo.findByTextContaining(quote);

        assertThat(chunks)
            .hasSize(1)
            .allMatch(chunk -> chunk.getText().contains(quote));
    }

    @Test
    void findMultipleMatchingChunksGivenQuote() {
        projectRepo.save(data.projects.basic);
        pageRepo.save(data.pages.basic);
        String quote = "Heading";

        List<PageChunk> chunks = chunkRepo.findByTextContaining(quote);

        assertThat(chunks)
            .hasSize(2)
            .allMatch(chunk -> chunk.getText().contains(quote));
    }
}
