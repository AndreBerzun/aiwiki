package ch.lianto.aiwiki.engine.service.assistant;

import ch.lianto.aiwiki.engine.entity.Chat;
import ch.lianto.aiwiki.engine.entity.PageSegment;
import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.repository.PageSegmentRepository;
import ch.lianto.aiwiki.engine.repository.ProjectRepository;
import ch.lianto.aiwiki.engine.service.nlp.ChatClient;
import ch.lianto.aiwiki.engine.service.nlp.ChatSummaryProvider;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
public class AssistantService {
    private final ProjectRepository projectRepo;
    private final PageSegmentRepository pageSegmentRepo;
    private final ChatClient chatClient;
    private final ChatSummaryProvider summaryProvider;

    public AssistantService(
        ProjectRepository projectRepo,
        PageSegmentRepository pageSegmentRepo,
        ChatClient chatClient,
        ChatSummaryProvider summaryProvider
    ) {
        this.projectRepo = projectRepo;
        this.pageSegmentRepo = pageSegmentRepo;
        this.chatClient = chatClient;
        this.summaryProvider = summaryProvider;
    }

    public Chat ask(Chat chat, String projectName) {
        Project project = projectRepo.findByName(projectName);
        String prompt = summaryProvider.summarizeLatestQuestion(chat);
        List<Similarity<PageSegment>> matchingSegments = pageSegmentRepo.findBySimilarity(prompt, project);
        Flux<String> answer = chatClient.generateResponseChunks(prompt, toContext(matchingSegments));
        return chat.answer(answer);
    }

    private String[] toContext(List<Similarity<PageSegment>> matchingSegments) {
        return matchingSegments.stream()
            .map(Similarity::data)
            .map(PageSegment::toString)
            .toArray(String[]::new);
    }
}
