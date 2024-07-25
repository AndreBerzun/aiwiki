package ch.lianto.aiwiki.engine.infrastructure.persistence;

import ch.lianto.aiwiki.engine.entity.Page;
import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.repository.PageRepository;
import ch.lianto.aiwiki.engine.utils.TestData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class SimplePersistentProjectRepositoryTest {
    private static final String NON_EXISTENT_PROJECT_STORE = "src/test/resources/simple-persistence/nope.json";
    private static final String BROKEN_PROJECT_STORE = "src/test/resources/simple-persistence/broken.json";
    private static final String BASIC_PROJECT_STORE = "src/test/resources/simple-persistence/basic-project.json";
    private static final String BASIC_PROJECT_WITH_PAGES_STORE = "src/test/resources/simple-persistence/basic-project-with-pages.json";

    private SimplePersistentProjectRepository projectRepo;
    private PageRepository pageRepo;
    private SimplePersistenceProperties properties;
    private TestData data;

    @BeforeEach
    void setUp() {
        data = new TestData();
        properties = new SimplePersistenceProperties();
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(NON_EXISTENT_PROJECT_STORE));
    }

    @Test
    void loadNoDataWhenProjectStoreDoesntExist() {
        createRepoWithProjectStorePath(NON_EXISTENT_PROJECT_STORE);

        projectRepo.loadProjectsData();

        assertThat(projectRepo.findAll()).isEmpty();
    }

    @Test
    void throwRuntimeExceptionWhenLoadingBrokenStore() {
        try {
            createRepoWithProjectStorePath(BROKEN_PROJECT_STORE);
            projectRepo.loadProjectsData();
            fail("Should have thrown ");
        } catch (Exception ex) {

        }
    }

    @Test
    void createEmptyPersistenceFileWhenFlushingEmptyRepo() throws IOException {
        createRepoWithProjectStorePath(NON_EXISTENT_PROJECT_STORE);

        projectRepo.loadProjectsData();
        projectRepo.flushProjectsData();

        Path actualFile = Paths.get(NON_EXISTENT_PROJECT_STORE);
        assertThat(actualFile).exists();
        assertThat(Files.readString(actualFile)).isEqualTo("{}");
    }

    @Test
    void loadBasicProject() {
        createRepoWithProjectStorePath(BASIC_PROJECT_STORE);

        projectRepo.loadProjectsData();

        assertThatRepoContainsBasicProject(false);
    }

    @Test
    void loadBasicProjectWithPages() {
        createRepoWithProjectStorePath(BASIC_PROJECT_WITH_PAGES_STORE);

        projectRepo.loadProjectsData();

        assertThatRepoContainsBasicProject(true);
    }

    private void assertThatRepoContainsBasicProject(boolean withPages) {
        List<Project> results = projectRepo.findAll();
        assertThat(results).hasSize(1);
        assertThat(results.get(0)).isEqualTo(data.projects.basic);

        if (withPages) assertThatProjectContainsPage(results.get(0));
    }

    private void assertThatProjectContainsPage(Project project) {
        assertThat(project.getPages()).hasSize(1);
        assertThat(project.getPages().get(0)).isEqualTo(data.pages.basic);
    }

    @Test
    void setBackReferencesWhenLoadBasicProjectWithPages() {
        createRepoWithProjectStorePath(BASIC_PROJECT_WITH_PAGES_STORE);

        projectRepo.loadProjectsData();

        assertThatBackReferencesSet();
    }

    private void assertThatBackReferencesSet() {
        List<Project> projects = projectRepo.findAll();
        for (Project project : projects) {
            for (Page page : project.getPages()) {
                assertThat(page.getProject()).isEqualTo(project);
                assertThat(page.getChunks()).allMatch(segment -> page.equals(segment.getPage()));
            }
        }
    }

    @Test
    void flushBasicProject() throws IOException {
        createRepoWithProjectStorePath(NON_EXISTENT_PROJECT_STORE);

        projectRepo.save(data.projects.basic);
        projectRepo.flushProjectsData();

        assertThatFlushedFileContainsBasicProject(false);
    }

    @Test
    void flushBasicProjectWithPages() throws IOException {
        createRepoWithProjectStorePath(NON_EXISTENT_PROJECT_STORE);

        projectRepo.save(data.projects.basic);
        pageRepo.save(data.pages.basic);
        projectRepo.flushProjectsData();

        assertThatFlushedFileContainsBasicProject(true);
    }

    private void assertThatFlushedFileContainsBasicProject(boolean withPages) throws IOException {
        String actualJson = Files.readString(Paths.get(NON_EXISTENT_PROJECT_STORE));
        String expectedJson = Files.readString(Paths.get(withPages ? BASIC_PROJECT_WITH_PAGES_STORE : BASIC_PROJECT_STORE));

        assertThat(actualJson).isEqualTo(expectedJson);
    }

    @Test
    void completeFlowWhenLoadedAndFlushedMultipleTimes() throws IOException {
        createRepoWithProjectStorePath(NON_EXISTENT_PROJECT_STORE);

        saveTwoProjectsWhileLoadingAndFlushingStore();

        assertThatRepoReturnsTwoProjects();
    }

    private void assertThatRepoReturnsTwoProjects() {
        List<Project> results = projectRepo.findAll();
        assertThat(results).hasSize(2);
        assertThat(results).anyMatch(p -> p.getName().equals(data.projects.basic.getName()));
        assertThat(results).anyMatch(p -> p.getName().equals(data.projects.alternate.getName()));
    }

    private void saveTwoProjectsWhileLoadingAndFlushingStore() throws IOException {
        projectRepo.save(data.projects.alternate);
        projectRepo.flushProjectsData();
        projectRepo.loadProjectsData();
        projectRepo.save(data.projects.basic);
        projectRepo.flushProjectsData();
        createRepoWithProjectStorePath(NON_EXISTENT_PROJECT_STORE);
        projectRepo.loadProjectsData();
    }

    private void createRepoWithProjectStorePath(String path) {
        properties.setFile(new FileSystemResource(path));
        projectRepo = new SimplePersistentProjectRepository(properties);
        pageRepo = new InMemoryPageRepository(projectRepo);
    }
}
