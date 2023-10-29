package ch.lianto.aiwiki.cli.service;

import ch.lianto.aiwiki.engine.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class CliContext {
    private Project selectedProject;

    public boolean isProjectSelected() {
        return selectedProject != null;
    }

    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }
}
