package ch.lianto.aiwiki.engine.policy.assistant;

import ch.lianto.aiwiki.engine.entity.Chat;
import ch.lianto.aiwiki.engine.entity.PageChunk;
import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.policy.nlp.ChatClient;
import ch.lianto.aiwiki.engine.policy.nlp.ChatRequest;
import ch.lianto.aiwiki.engine.repository.PageChunkRepository;
import ch.lianto.aiwiki.engine.repository.ProjectRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
public class AssistantService {
    private final ProjectRepository projectRepo;
    private final PageChunkRepository chunkRepo;
    private final ChatClient chatClient;

    public AssistantService(
        ProjectRepository projectRepo,
        PageChunkRepository chunkRepo,
        ChatClient chatClient
    ) {
        this.projectRepo = projectRepo;
        this.chunkRepo = chunkRepo;
        this.chatClient = chatClient;
    }

    public Chat ask(Chat chat, String projectName) {
        var project = projectRepo.findByName(projectName);
        var prompt = summarizeLatestPromptInChat(chat);
        var matchingChunks = search(prompt, project);
        var answer = chatClient.generateResponseChunks(ChatRequest.rag(prompt, toContext(matchingChunks)));
        return chat.answer(answer);
    }

    private String summarizeLatestPromptInChat(Chat chat) {
        if (chat.getMessages().size() == 1) return chat.getMessages().get(0).text();
        else return chatClient.generateResponse(ChatRequest.summary(chat));
    }

    private String[] toContext(List<Similarity<PageChunk>> matchingSegments) {
        return matchingSegments.stream()
            .map(Similarity::data)
            .map(PageChunk::toString)
            .toArray(String[]::new);
    }

    public List<Similarity<PageChunk>> search(String prompt, Project project) {
        return chunkRepo.findBySimilarity(prompt, project);
    }
}
