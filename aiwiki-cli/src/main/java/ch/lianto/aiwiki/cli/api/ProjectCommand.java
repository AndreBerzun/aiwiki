package ch.lianto.aiwiki.cli.api;

import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.policy.project.ProjectDto;
import ch.lianto.aiwiki.engine.policy.project.ProjectService;
import org.springframework.shell.command.annotation.Command;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Command(command = "project")
public class ProjectCommand {
    private final ProjectService projectService;

    public ProjectCommand(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Command(command = "create", description = "Creates a new project")
    public String createProject(String projectName) {
        projectService.createProject(new ProjectDto(projectName));
        return String.format("Added project <%s>", projectName);
    }

    @Command(command = "ls", description = "Lists all AI Wiki projects")
    public String listProjects() {
        List<Project> projects = projectService.findAllProjects();
        if (projects.isEmpty())
            return "No projects created";
        else
            return "- " + String.join("\n- ", projects.stream().map(Project::getName).toList());
    }

    @Command(command = "import", description = "Imports a single file or alternatively a directory incl. sub-directories on the local file system into the current project")
    public void importFiles(String project, String path) {
        projectService.importLocalPages(project, path);
    }
}
