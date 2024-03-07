package ch.lianto.aiwiki.engine.infrastructure.nlp.ollama;

import ch.lianto.aiwiki.engine.entity.Chat;
import ch.lianto.aiwiki.engine.infrastructure.nlp.AbstractChatSummaryProvider;
import ch.lianto.aiwiki.engine.infrastructure.nlp.PromptTemplates;
import ch.lianto.aiwiki.engine.policy.nlp.ChatClient;
import ch.lianto.aiwiki.engine.policy.nlp.ChatSummaryProvider;
import ch.lianto.ollama.client.api.CompletionsApi;
import ch.lianto.ollama.client.config.OllamaClientProperties;
import ch.lianto.ollama.client.model.GenerateCompletionRequest;
import ch.lianto.ollama.client.model.GenerateCompletionResponse;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Profile("ollama-generation")
@Primary
@Component
public class OllamaChatClient extends AbstractChatSummaryProvider implements ChatClient, ChatSummaryProvider {
    private final CompletionsApi api;
    private final OllamaClientProperties properties;

    public OllamaChatClient(CompletionsApi api, OllamaClientProperties properties) {
        this.api = api;
        this.properties = properties;
    }

    @Override
    public String generateResponse(String prompt, String... context) {
        if (prompt.isEmpty()) throw new IllegalArgumentException("Cannot answer to empty prompt!");

        GenerateCompletionRequest request = createCompletionRequest(prompt, context, false);
        GenerateCompletionResponse response = api.generateCompletion(request).block();
        return response.getResponse();
    }

    @Override
    public Flux<String> generateResponseChunks(String prompt, String... context) {
        if (prompt.isEmpty()) throw new IllegalArgumentException("Cannot answer to empty prompt!");

        GenerateCompletionRequest request = createCompletionRequest(prompt, context, true);

        return api.generateCompletionWithResponseSpec(request)
            .bodyToFlux(GenerateCompletionResponse.class)
            .mapNotNull(GenerateCompletionResponse::getResponse);
    }

    private GenerateCompletionRequest createCompletionRequest(String prompt, String[] context, boolean stream) {
        var request = new GenerateCompletionRequest();
        request.setModel(properties.getGenerationModel());
        request.setStream(stream);
        request.setSystem(PromptTemplates.rag(context));
        request.setPrompt(prompt);
        return request;
    }

    @Override
    protected String fetchSummaryFromApi(Chat chat) {
        GenerateCompletionRequest request = createSummaryRequest(chat);
        GenerateCompletionResponse response = api.generateCompletion(request).block();
        return response.getResponse();
    }

    private GenerateCompletionRequest createSummaryRequest(Chat chat) {
        GenerateCompletionRequest request = new GenerateCompletionRequest();
        request.setModel(properties.getGenerationModel());
        request.setStream(false);
        request.setPrompt(PromptTemplates.summary(chat));
        return request;
    }
}
