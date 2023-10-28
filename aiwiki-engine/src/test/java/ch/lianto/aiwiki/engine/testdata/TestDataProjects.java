package ch.lianto.aiwiki.engine.testdata;

import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.service.data.ProjectDto;

public class TestDataProjects {
    public final Project basic;
    public final ProjectDto basicDto;

    public TestDataProjects() {
        basic = basicProject();
        basicDto = basicDto();
    }

    public Project basicProject() {
        Project project = new Project();
        project.setName("Project");
        return project;
    }

    private ProjectDto basicDto() {
        ProjectDto project = new ProjectDto("Project");
        return project;
    }
}
