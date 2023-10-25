package ch.lianto.aiwiki.cli.service;

import org.springframework.stereotype.Component;

@Component
public class CliContext {
    private String selectedProject;

    public boolean isProjectSelected() {
        return selectedProject != null;
    }

    public String getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(String selectedProject) {
        this.selectedProject = selectedProject;
    }
}
