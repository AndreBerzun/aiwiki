package ch.lianto.aiwiki.cli.api;

import ch.lianto.aiwiki.cli.service.CliContext;
import org.springframework.shell.command.annotation.Command;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Command(command = "project")
public class ProjectCommand {
    private final CliContext context;
    private List<String> projects = new ArrayList<>();

    public ProjectCommand(CliContext context) {
        this.context = context;
    }

    @Command(command = "create", description = "Creates a new project")
    public String createProject(String projectName) {
        projects.add(projectName);
        return String.format("Added project <%s>", projectName);
    }

    @Command(command = "ls", description = "Lists all AI Wiki projects")
    public String listProjects() {
        return projects.isEmpty() ? "No projects created" : "- " + String.join("\n- ", projects);
    }

    @Command(command = "select", description = "Selects a project for further operations")
    public String selectProject(String projectName) {
        if (projects.contains(projectName))
            context.setSelectedProject(projectName);
        else
            throw new IllegalArgumentException(String.format("Selected project <%s> was not found.", projectName));

        return String.format("Now running project <%s>", projectName);
    }
}
