package ch.lianto.aiwiki.engine.infrastructure.batch;

import ch.lianto.aiwiki.engine.batch.BatchJobLauncher;
import ch.lianto.aiwiki.engine.batch.BatchJobResult;
import ch.lianto.aiwiki.engine.entity.Page;
import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.infrastructure.nlp.NoOpEmbeddingProvider;
import ch.lianto.aiwiki.engine.infrastructure.persistence.InMemoryPageRepository;
import ch.lianto.aiwiki.engine.infrastructure.persistence.InMemoryProjectRepository;
import ch.lianto.aiwiki.engine.repository.ProjectRepository;
import ch.lianto.aiwiki.engine.service.page.MaxWordSegmentationStrategy;
import ch.lianto.aiwiki.engine.service.page.PageService;
import ch.lianto.aiwiki.engine.utils.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BatchJobLauncherTest {
    private static final String EMPTY_IMPORT_DIR = "src/test/resources/local-project-import/empty";
    private static final String IMPORT_DIR = "src/test/resources/local-project-import/data";
    private static final String PLAINTEXT_FILE_DIR = IMPORT_DIR + "/single-segment/plaintext";
    private static final String MARKDOWN_FILE_DIR = IMPORT_DIR + "/single-segment/markdown";
    private static final String MIXED_FILE_DIR = IMPORT_DIR + "/multi-segment";
    private BatchJobLauncher jobLauncher;
    private ProjectRepository projectRepo;
    private TestData data;

    @BeforeEach
    void setUp() {
        data = new TestData();
        projectRepo = new InMemoryProjectRepository();
        jobLauncher = new SimpleBatchJobLauncher(new PageService(
            new InMemoryPageRepository((InMemoryProjectRepository) projectRepo),
            projectRepo,
            new NoOpEmbeddingProvider(),
            new MaxWordSegmentationStrategy()
        ));
    }

    @Test
    void noReadNoWrittenWhenEmptyImportDirectory() {
        BatchJobResult results = runProjectImportWithDirectory(EMPTY_IMPORT_DIR);

        assertThatNumberOfPagesReadAndCreatedIsEqualTo(results, 0);
        assertThatCreatedPagesMatchExpected(Collections.emptyList());
    }

    @Test
    void readAndWriteItemWhenImportDirectoryWithOnePlainTextFile() {
        BatchJobResult result = runProjectImportWithDirectory(PLAINTEXT_FILE_DIR);

        assertThatNumberOfPagesReadAndCreatedIsEqualTo(result, 1);
        assertThatCreatedPagesMatchExpected(new ArrayList<>(List.of("Lorem Ipsum Page")));
    }

    @Test
    void readAndWriteItemWhenImportDirectoryWithOneMarkdownFile() {
        BatchJobResult result = runProjectImportWithDirectory(MARKDOWN_FILE_DIR);

        assertThatNumberOfPagesReadAndCreatedIsEqualTo(result, 1);
        assertThatCreatedPagesMatchExpected(new ArrayList<>(List.of("Feature Spec")));
    }

    @Test
    void readAndWriteItemWhenImportDirectoryWithOneMixedFile() {
        BatchJobResult result = runProjectImportWithDirectory(MIXED_FILE_DIR);

        assertThatNumberOfPagesReadAndCreatedIsEqualTo(result, 1);
        assertThatCreatedPagesMatchExpected(new ArrayList<>(List.of("Merged Page")));
    }

    @Test
    void readAndWriteManyItemsWhenImportDirectoryWithManyNestedFiles() {
        BatchJobResult result = runProjectImportWithDirectory(IMPORT_DIR);

        assertThatNumberOfPagesReadAndCreatedIsEqualTo(result, 3);
        assertThatCreatedPagesMatchExpected(new ArrayList<>(List.of("Feature Spec", "Lorem Ipsum Page", "Merged Page")));
    }

    private BatchJobResult runProjectImportWithDirectory(String directory) {
        projectRepo.save(data.projects.basic);

        return jobLauncher.runProjectImportJob(data.projects.basic.getName(), directory);
    }

    private void assertThatNumberOfPagesReadAndCreatedIsEqualTo(BatchJobResult results, int pageCount) {
        assertThat(results.itemsRead()).isEqualTo(pageCount);
        assertThat(results.itemsWritten()).isEqualTo(pageCount);
    }

    private void assertThatCreatedPagesMatchExpected(List<String> expectedPageNames) {
        Project project = projectRepo.findByName(data.projects.basic.getName());
        List<Page> actualPages = project.getPages();

        actualPages.sort(Comparator.comparing(Page::getName));
        expectedPageNames.sort(Comparator.naturalOrder());

        assertThat(project.getPages()).hasSize(expectedPageNames.size());
        for (int i = 0; i < expectedPageNames.size(); i++) {
            Page actualPage = actualPages.get(i);
            String expectedPageName = expectedPageNames.get(i);

            assertThat(actualPage.getName()).isEqualTo(expectedPageName);
            assertThat(actualPage.getProject().getName()).isEqualTo(data.projects.basic.getName());
        }
    }
}
