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
        return new Project()
            .setName("Project");
    }

    public Project alternateProject() {
        return new Project()
            .setName("Alternate Project");
    }

    private ProjectDto basicDto() {
        ProjectDto project = new ProjectDto("Project");
        return project;
    }
}
