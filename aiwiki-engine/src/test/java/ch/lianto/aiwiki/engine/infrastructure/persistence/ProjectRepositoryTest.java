package ch.lianto.aiwiki.engine.infrastructure.persistence;

import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.repository.ProjectRepository;
import ch.lianto.aiwiki.engine.testdata.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectRepositoryTest {
    private TestData data;
    private ProjectRepository repo;

    @BeforeEach
    void setUp() {
        data = new TestData();
        repo = new InMemoryProjectRepository();
    }

    @Test
    void findEmptyListWhenNoProjectsPersisted() {
        List<Project> results = repo.findAll();
        assertThat(results).isEmpty();
    }

    @Test
    void attributesMatchWhenSavingProject() {
        Project project = data.projects.basic;

        Project result = repo.save(project);

        assertThat(result.getName()).isEqualTo(project.getName());
        assertThat(result.getPages()).isEmpty();
    }

    @Test
    void findCreatedProjectWhenSingleProjectPersisted() {
        repo.save(data.projects.basic);

        List<Project> all = repo.findAll();

        assertThat(all).hasSize(1);
        assertThat(all.get(0).getName()).isEqualTo(data.projects.basic.getName());
    }
}
