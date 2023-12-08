package ch.lianto.aiwiki.engine.infrastructure.nlp;

import ch.lianto.aiwiki.engine.service.nlp.EmbeddingProvider;
import ch.lianto.openai.client.api.EmbeddingsApi;
import ch.lianto.openai.client.config.OpenAIClientProperties;
import ch.lianto.openai.client.model.CreateEmbeddingRequest;
import ch.lianto.openai.client.model.CreateEmbeddingResponse;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Primary
@Component
public class OpenAiEmbeddingProvider implements EmbeddingProvider {
    private final EmbeddingsApi openaiEmbeddings;
    private final OpenAIClientProperties properties;

    public OpenAiEmbeddingProvider(EmbeddingsApi openaiEmbeddings, OpenAIClientProperties properties) {
        this.openaiEmbeddings = openaiEmbeddings;
        this.properties = properties;
    }

    @Override
    public double[] generateEmbedding(String text) {
        CreateEmbeddingRequest request = createRequest(text);
        CreateEmbeddingResponse response = openaiEmbeddings.createEmbedding(request).block();
        validateResponseContainsOnlyOneEmbedding(response);
        return extractEmbedding(response);
    }

    private CreateEmbeddingRequest createRequest(String text) {
        return new CreateEmbeddingRequest()
            .model(properties.getEmbeddingModel())
            .input(text);
    }

    private void validateResponseContainsOnlyOneEmbedding(CreateEmbeddingResponse response) {
        if (response.getData().size() != 1)
            throw new EmbeddingException("Expected <1> embedding object from OpenAI response, received <" + response.getData().size() + ">");
    }

    private double[] extractEmbedding(CreateEmbeddingResponse response) {
        return response.getData()
            .get(0)
            .getEmbedding()
            .stream()
            .mapToDouble(BigDecimal::doubleValue)
            .toArray();
    }
}
