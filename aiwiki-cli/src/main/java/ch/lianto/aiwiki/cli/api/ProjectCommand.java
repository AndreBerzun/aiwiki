package ch.lianto.aiwiki.cli.api;

import ch.lianto.aiwiki.cli.service.CliContext;
import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.service.project.ProjectDto;
import ch.lianto.aiwiki.engine.service.project.ProjectService;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandAvailability;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Command(command = "project")
public class ProjectCommand {
    private final CliContext context;
    private final ProjectService projectService;

    public ProjectCommand(CliContext context, ProjectService projectService) {
        this.context = context;
        this.projectService = projectService;
    }

    @Command(command = "select", description = "Selects a project for further operations")
    public String selectProject(String projectName) {
        Project project = projectService.findByName(projectName);
        context.setSelectedProject(project);

        return String.format("Now running project <%s>", projectName);
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
    @CommandAvailability(provider = "projectAvailability")
    public void importFiles(String path) {
        projectService.importLocalPages(context.getSelectedProject().getName(), path);
    }
}
