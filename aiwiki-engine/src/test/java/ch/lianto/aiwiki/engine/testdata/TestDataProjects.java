package ch.lianto.aiwiki.engine.testdata;

import ch.lianto.aiwiki.engine.entity.Project;

public class TestDataProjects {
    public final Project basic;

    public TestDataProjects() {
        basic = basicProject();
    }

    public Project basicProject() {
        Project project = new Project();
        project.setName("Project");
        return project;
    }
}
