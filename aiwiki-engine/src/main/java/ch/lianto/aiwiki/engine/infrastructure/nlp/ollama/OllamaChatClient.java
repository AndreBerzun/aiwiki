package ch.lianto.aiwiki.engine.infrastructure.nlp.ollama;

import ch.lianto.aiwiki.engine.policy.nlp.ChatClient;
import ch.lianto.aiwiki.engine.policy.nlp.ChatRequest;
import ch.lianto.ollama.client.api.CompletionsApi;
import ch.lianto.ollama.client.config.OllamaClientProperties;
import ch.lianto.ollama.client.model.GenerateCompletionRequest;
import ch.lianto.ollama.client.model.GenerateCompletionResponse;
import ch.lianto.ollama.client.model.ResponseFormat;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Profile("ollama-generation")
@Component
public class OllamaChatClient implements ChatClient {
    private final CompletionsApi api;
    private final OllamaClientProperties properties;

    public OllamaChatClient(CompletionsApi api, OllamaClientProperties properties) {
        this.api = api;
        this.properties = properties;
    }

    @Override
    public String generateResponse(ChatRequest request) {
        GenerateCompletionRequest apiRequest = createCompletionRequest(request, false);
        GenerateCompletionResponse response = api.generateCompletion(apiRequest).block();
        return response.getResponse();
    }

    @Override
    public Flux<String> generateResponseChunks(ChatRequest request) {
        GenerateCompletionRequest apiRequest = createCompletionRequest(request, true);

        return api.generateCompletionWithResponseSpec(apiRequest)
            .bodyToFlux(GenerateCompletionResponse.class)
            .mapNotNull(GenerateCompletionResponse::getResponse);
    }

    private GenerateCompletionRequest createCompletionRequest(ChatRequest request, boolean stream) {
        var apiRequest = new GenerateCompletionRequest();
        apiRequest.setModel(properties.getGenerationModel());
        apiRequest.setStream(stream);
        if (request.isJsonMode())
            apiRequest.setFormat(ResponseFormat.JSON);
        if (request.getSystemPrompt() != null)
            apiRequest.setSystem(request.getSystemPrompt());
        apiRequest.setPrompt(request.getUserPrompt());
        return apiRequest;
    }
}
