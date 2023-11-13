package ch.lianto.aiwiki.engine.infrastructure.nlp;

import ch.lianto.aiwiki.engine.service.page.EmbeddingProvider;
import ch.lianto.openai.client.api.EmbeddingsApi;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class OpenAiEmbeddingProvider implements EmbeddingProvider {
    private final EmbeddingsApi openaiEmbeddings;

    public OpenAiEmbeddingProvider(EmbeddingsApi openaiEmbeddings) {
        this.openaiEmbeddings = openaiEmbeddings;
    }

    @Override
    public double[] generateEmbedding(String text) {
        return new double[0];
    }
}
