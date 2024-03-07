package ch.lianto.aiwiki.engine.infrastructure.nlp.ollama;

import ch.lianto.aiwiki.engine.policy.nlp.EmbeddingProvider;
import ch.lianto.ollama.client.api.EmbeddingsApi;
import ch.lianto.ollama.client.config.OllamaClientProperties;
import ch.lianto.ollama.client.model.GenerateEmbeddingRequest;
import ch.lianto.ollama.client.model.GenerateEmbeddingResponse;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("ollama-embeddings")
@Primary
@Component
public class OllamaEmbeddingProvider implements EmbeddingProvider {
    private final EmbeddingsApi api;
    private final OllamaClientProperties properties;

    public OllamaEmbeddingProvider(EmbeddingsApi api, OllamaClientProperties properties) {
        this.api = api;
        this.properties = properties;
    }

    @Override
    public double[] generateEmbedding(String text, EmbeddingType type) {
        GenerateEmbeddingRequest embeddingRequest = createEmbeddingRequest(text, type);
        GenerateEmbeddingResponse embeddingResponse = api.generateEmbedding(embeddingRequest).block();
        return extractEmbeddingFromResponse(embeddingResponse);
    }

    private GenerateEmbeddingRequest createEmbeddingRequest(String text, EmbeddingType type) {
        var embeddingRequest = new GenerateEmbeddingRequest();
        embeddingRequest.setModel(properties.getEmbeddingModel());
        if (properties.getPromptPrefixes().containsKey(type.name()))
            embeddingRequest.setPrompt(properties.getPromptPrefixes().get(type.name()) + " " + text);
        else
            embeddingRequest.setPrompt(text);
        return embeddingRequest;
    }

    private double[] extractEmbeddingFromResponse(GenerateEmbeddingResponse embeddingResponse) {
        return embeddingResponse
            .getEmbedding()
            .stream()
            .mapToDouble(Double::doubleValue)
            .toArray();
    }
}
