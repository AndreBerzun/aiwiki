package ch.lianto.aiwiki.engine.infrastructure.nlp.openai;

import ch.lianto.aiwiki.engine.infrastructure.nlp.EmbeddingException;
import ch.lianto.aiwiki.engine.policy.nlp.EmbeddingProvider;
import ch.lianto.openai.client.api.EmbeddingsApi;
import ch.lianto.openai.client.config.OpenAIClientProperties;
import ch.lianto.openai.client.model.CreateEmbeddingRequest;
import ch.lianto.openai.client.model.CreateEmbeddingResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static org.apache.commons.lang3.StringUtils.abbreviate;

@Profile("openai-embeddings")
@Component
public class OpenAiEmbeddingProvider implements EmbeddingProvider {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final EmbeddingsApi openaiEmbeddings;
    private final OpenAIClientProperties properties;

    public OpenAiEmbeddingProvider(EmbeddingsApi openaiEmbeddings, OpenAIClientProperties properties) {
        this.openaiEmbeddings = openaiEmbeddings;
        this.properties = properties;
    }

    @Override
    public double[] generateEmbedding(String text, EmbeddingType type) {
        if (text.isEmpty()) throw new IllegalArgumentException("Cannot generate embedding for empty text");

        log.info("Creating embedding request for text <{}>", abbreviate(text, 30));
        CreateEmbeddingRequest request = createRequest(text);
        CreateEmbeddingResponse response = openaiEmbeddings.createEmbedding(request).block();
        log.info("Received embedding response for text <{}>", abbreviate(text, 30));
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
