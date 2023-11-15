package ch.lianto.aiwiki.engine.service.assistant;

import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.repository.ProjectRepository;
import org.springframework.stereotype.Component;

@Component
public class AssistantService {
    private final ProjectRepository projectRepo;

    public AssistantService(ProjectRepository projectRepo) {
        this.projectRepo = projectRepo;
    }

    public String ask(String prompt, String projectName) {
        Project project = projectRepo.findByName(projectName);
        // Collect PageSegments by similarity
        // Pass to chat provider

        return "";
    }
}
