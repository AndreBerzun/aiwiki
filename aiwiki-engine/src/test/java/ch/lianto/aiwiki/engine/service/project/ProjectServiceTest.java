package ch.lianto.aiwiki.engine.service.project;

import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.infrastructure.persistence.InMemoryProjectRepository;
import ch.lianto.aiwiki.engine.utils.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectServiceTest {
    private TestData data;
    private ProjectService service;

    @BeforeEach
    void setUp() {
        data = new TestData();
        service = new ProjectService(new InMemoryProjectRepository(), ProjectMapper.INSTANCE);
    }

    @Test
    void findEmptyListWhenNoProjectsCreated() {
        List<Project> results = service.findAllProjects();
        assertThat(results).isEmpty();
    }

    @Test
    void attributesMatchWhenSavingProject() {
        ProjectDto project = data.projects.basicDto;

        Project result = service.createProject(project);

        assertThat(result.getName()).isEqualTo(project.name());
        assertThat(result.getPages()).isEmpty();
    }

    @Test
    void findCreatedProjectWhenSingleProjectPersisted() {
        service.createProject(data.projects.basicDto);

        List<Project> all = service.findAllProjects();

        assertThat(all).hasSize(1);
        assertThat(all.get(0).getName()).isEqualTo(data.projects.basicDto.name());
    }
}
