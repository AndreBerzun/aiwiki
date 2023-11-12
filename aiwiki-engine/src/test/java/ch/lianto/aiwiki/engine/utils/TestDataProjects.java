package ch.lianto.aiwiki.engine.utils;

import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.service.project.ProjectDto;

public class TestDataProjects {
    public final Project basic;
    public final Project alternate;
    public final ProjectDto basicDto;

    public TestDataProjects() {
        basic = basicProject();
        alternate = alternateProject();
        basicDto = basicDto();
    }

    public Project basicProject() {
        Project project = new Project();
        project.setName("Project");
        return project;
    }

    public Project alternateProject() {
        Project project = new Project();
        project.setName("Alternate Project");
        return project;
    }

    private ProjectDto basicDto() {
        ProjectDto project = new ProjectDto("Project");
        return project;
    }
}
