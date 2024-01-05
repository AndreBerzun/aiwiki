package ch.lianto.aiwiki.engine.policy.page;

import ch.lianto.aiwiki.engine.entity.Page;
import ch.lianto.aiwiki.engine.entity.PageSegment;
import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.infrastructure.nlp.MaxWordSegmentationStrategy;
import ch.lianto.aiwiki.engine.infrastructure.nlp.NoOpEmbeddingProvider;
import ch.lianto.aiwiki.engine.infrastructure.persistence.InMemoryPageRepository;
import ch.lianto.aiwiki.engine.infrastructure.persistence.InMemoryProjectRepository;
import ch.lianto.aiwiki.engine.repository.PageRepository;
import ch.lianto.aiwiki.engine.repository.ProjectRepository;
import ch.lianto.aiwiki.engine.utils.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PageServiceTest {
    private TestData data;
    private PageService service;
    private ProjectRepository projectRepo;
    private PageRepository pageRepo;
    private PageSegmentationStrategy segmentationStrategy;

    @BeforeEach
    void setUp() {
        data = new TestData();
        projectRepo = new InMemoryProjectRepository();
        pageRepo = new InMemoryPageRepository((InMemoryProjectRepository) projectRepo);
        segmentationStrategy = new MaxWordSegmentationStrategy();
        service = new PageService(pageRepo, projectRepo, new NoOpEmbeddingProvider(), segmentationStrategy);
    }

    @Test
    void attributesMatchWhenCreatingPage() {
        projectRepo.save(data.projects.basic);

        Page result = service.createPage(data.pages.basicDto);

        assertThatNamesMatch(result);
        assertThatSegmentsMatch(result);
    }

    private void assertThatNamesMatch(Page result) {
        assertThat(result.getName()).isEqualTo(data.pages.basicDto.name());
        assertThat(result.getProject().getName()).isEqualTo(data.pages.basicDto.projectName());
    }

    private void assertThatSegmentsMatch(Page result) {
        List<String> actualTextSegments = result.getPageSegments().stream().map(PageSegment::getText).toList();
        List<String> expectedTextSegments = segmentationStrategy.segment(data.pages.basicDto.content());

        assertThat(actualTextSegments).isEqualTo(expectedTextSegments);
        assertThat(result.getPageSegments()).allMatch(segment -> segment.getEmbedding() != null);
    }

    @Test
    void findPageWhenCreated() {
        projectRepo.save(data.projects.basic);
        service.createPage(data.pages.basicDto);

        List<Page> results = pageRepo.findAll();

        assertThat(results).hasSize(1);
    }

    @Test
    void projectContainsPageWhenCreatedNewPage() {
        projectRepo.save(data.projects.basic);
        service.createPage(data.pages.basicDto);

        Project project = projectRepo.findAll().get(0);

        assertThat(project.getPages()).hasSize(1);
    }
}
