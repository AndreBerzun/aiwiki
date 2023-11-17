package ch.lianto.aiwiki.engine.service.assistant;

import ch.lianto.aiwiki.engine.entity.PageSegment;
import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.repository.PageSegmentRepository;
import ch.lianto.aiwiki.engine.repository.ProjectRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssistantService {
    private final ProjectRepository projectRepo;
    private final PageSegmentRepository pageSegmentRepo;
    private final ChatClient chatClient;

    public AssistantService(
        ProjectRepository projectRepo,
        PageSegmentRepository pageSegmentRepo,
        ChatClient chatClient
    ) {
        this.projectRepo = projectRepo;
        this.pageSegmentRepo = pageSegmentRepo;
        this.chatClient = chatClient;
    }

    // TODO unit test
    public String ask(String prompt, String projectName) {
        Project project = projectRepo.findByName(projectName);
        List<Similarity<PageSegment>> matchingSegments = pageSegmentRepo.findBySimilarity(prompt, project);
        return chatClient.message(
            prompt,
            matchingSegments.stream()
                .map(Similarity::data)
                .map(PageSegment::getText)
                .toArray(String[]::new)
        );
    }
}
