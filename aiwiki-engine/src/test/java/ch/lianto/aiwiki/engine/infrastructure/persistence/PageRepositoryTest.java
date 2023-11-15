package ch.lianto.aiwiki.engine.infrastructure.persistence;

import ch.lianto.aiwiki.engine.entity.Page;
import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.repository.PageRepository;
import ch.lianto.aiwiki.engine.repository.ProjectRepository;
import ch.lianto.aiwiki.engine.utils.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PageRepositoryTest {
    private TestData data;
    private ProjectRepository projectRepo;
    private PageRepository repo;

    @BeforeEach
    void setUp() {
        data = new TestData();
        projectRepo = new InMemoryProjectRepository();
        repo = new InMemoryPageRepository(projectRepo);
    }

    @Test
    void findEmptyListWhenNoPagesPersisted() {
        List<Page> results = repo.findAll();
        assertThat(results).isEmpty();
    }

    @Test
    void attributesMatchWhenSavingPage() {
        projectRepo.save(data.projects.basic);
        Page page = data.pages.basic;

        Page result = repo.save(page);

        assertThat(result.getName()).isEqualTo(page.getName());
        assertThat(result.getProject().getName()).isEqualTo(page.getProject().getName());
        assertThat(result.getPageSegments()).hasSize(2);
    }

    @Test
    void projectContainsPageWhenAddingNewPage() {
        projectRepo.save(data.projects.basic);
        Page result = repo.save(data.pages.basic);

        Project project = projectRepo.findAll().get(0);

        assertThat(project.getPages()).hasSize(1);
        assertThat(project.getPages().get(0).getName()).isEqualTo(result.getName());
    }

    @Test
    void findCreatedProjectWhenSingleProjectPersisted() {
        projectRepo.save(data.projects.basic);
        repo.save(data.pages.basic);

        List<Page> all = repo.findAll();

        assertThat(all).hasSize(1);
        assertThat(all.get(0).getName()).isEqualTo(data.pages.basic.getName());
    }
}
