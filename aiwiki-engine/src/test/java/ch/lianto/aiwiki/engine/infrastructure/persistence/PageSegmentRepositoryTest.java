package ch.lianto.aiwiki.engine.infrastructure.persistence;

import ch.lianto.aiwiki.engine.entity.Page;
import ch.lianto.aiwiki.engine.entity.PageSegment;
import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.repository.PageRepository;
import ch.lianto.aiwiki.engine.repository.PageSegmentRepository;
import ch.lianto.aiwiki.engine.repository.ProjectRepository;
import ch.lianto.aiwiki.engine.policy.assistant.Similarity;
import ch.lianto.aiwiki.engine.utils.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PageSegmentRepositoryTest {
    private TestData data;
    private PageSegmentRepository pageSegmentRepo;
    private ProjectRepository projectRepo;
    private PageRepository pageRepo;

    @BeforeEach
    void setUp() {
        data = new TestData();
        projectRepo = new InMemoryProjectRepository();
        pageRepo = new InMemoryPageRepository(projectRepo);
        pageSegmentRepo = new InMemoryPageSegmentRepository(projectRepo, (text) -> new double[]{1, 1, 1});
    }

    @Test
    void returnsEmptyListWhenEmptySearchText() {
        projectRepo.save(data.projects.basic);

        List<Similarity<PageSegment>> results = pageSegmentRepo.findBySimilarity(data.embeddings.emptyPrompt, data.projects.basic);

        assertThat(results).isEmpty();
    }

    @Test
    void returnsEmptyListWhenNoPagesCreated() {
        projectRepo.save(data.projects.basic);

        List<Similarity<PageSegment>> results = pageSegmentRepo.findBySimilarity(data.embeddings.bestRockBandPrompt, data.projects.basic);

        assertThat(results).isEmpty();
    }

    @Test
    void returnsEmptyListWhenMatchingPagesButFromDifferentProject() {
        projectRepo.save(data.projects.basic);
        projectRepo.save(data.projects.alternate);
        pageRepo.save(data.embeddings.pinkFloydPage);

        List<Similarity<PageSegment>> results = pageSegmentRepo.findBySimilarity(data.embeddings.bestRockBandPrompt, data.projects.alternate);

        assertThat(results).isEmpty();
    }

    @Test
    void returnsPageThatMatchesPrompt() {
        Project project = projectRepo.save(data.projects.basic);
        Page relatedPage = pageRepo.save(data.embeddings.pinkFloydPage);

        List<Similarity<PageSegment>> results = pageSegmentRepo.findBySimilarity(data.embeddings.bestRockBandPrompt, project);

        assertThat(results).hasSize(1);
        assertThat(results.stream().map(Similarity::data).toList()).isEqualTo(relatedPage.getPageSegments());
    }

    @Test
    void dontIncludeUnrelatedPages() {
        projectRepo.save(data.projects.basic);
        Page relatedPage = pageRepo.save(data.embeddings.pinkFloydPage);
        Page unrelatedPage = pageRepo.save(data.pages.basic);

        List<Similarity<PageSegment>> results = pageSegmentRepo.findBySimilarity(data.embeddings.bestRockBandPrompt, data.projects.basic);

        assertThat(results).hasSize(1);
        assertThat(results.stream().map(Similarity::data).toList()).isEqualTo(relatedPage.getPageSegments());
    }

    @Test
    void orderMatchingPagesBySimilarity() {
        projectRepo.save(data.projects.basic);
        Page mostRelevant = pageRepo.save(data.embeddings.pinkFloydPage);
        Page relevant = pageRepo.save(data.embeddings.eloPage);
        pageRepo.save(data.pages.basic);

        List<Similarity<PageSegment>> results = pageSegmentRepo.findBySimilarity(data.embeddings.bestRockBandPrompt, data.projects.basic);

        assertThat(results).hasSize(2);
        assertThat(results.get(0).data()).isEqualTo(mostRelevant.getPageSegments().get(0));
        assertThat(results.get(1).data()).isEqualTo(relevant.getPageSegments().get(0));
    }
}
