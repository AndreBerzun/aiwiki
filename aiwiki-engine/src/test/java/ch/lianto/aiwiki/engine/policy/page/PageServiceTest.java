package ch.lianto.aiwiki.engine.policy.page;

import ch.lianto.aiwiki.engine.config.IndexingProperties;
import ch.lianto.aiwiki.engine.entity.Page;
import ch.lianto.aiwiki.engine.entity.PageChunk;
import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.infrastructure.nlp.MaxWordChunkingStrategy;
import ch.lianto.aiwiki.engine.infrastructure.nlp.NoOpChatClient;
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
    private PageChunkingStrategy chunkingStrategy;

    @BeforeEach
    void setUp() {
        data = new TestData();
        projectRepo = new InMemoryProjectRepository();
        pageRepo = new InMemoryPageRepository(projectRepo);
        chunkingStrategy = new MaxWordChunkingStrategy();
        service = new PageService(pageRepo, projectRepo, new ChunkService(new NoOpEmbeddingProvider(), new MaxWordChunkingStrategy(), new NoOpChatClient(), new IndexingProperties()));
    }

    @Test
    void attributesMatchWhenCreatingPage() {
        projectRepo.save(data.projects.basic);

        Page result = service.createPage(data.pages.basicDto);

        assertThatNamesMatch(result);
        assertThatChunksMatch(result);
    }

    private void assertThatNamesMatch(Page result) {
        assertThat(result.getName()).isEqualTo(data.pages.basicDto.name());
        assertThat(result.getProject().getName()).isEqualTo(data.pages.basicDto.projectName());
    }

    private void assertThatChunksMatch(Page result) {
        List<String> actualTextChunks = result.getChunks().stream().map(PageChunk::getText).toList();
        List<String> expectedTextChunks = chunkingStrategy.split(data.pages.basicDto.content());

        assertThat(actualTextChunks).isEqualTo(expectedTextChunks);
        assertThat(result.getChunks()).allMatch(segment -> segment.getEmbeddings() != null);
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
