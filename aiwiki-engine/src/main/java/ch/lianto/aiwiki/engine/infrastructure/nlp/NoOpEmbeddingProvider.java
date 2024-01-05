package ch.lianto.aiwiki.engine.infrastructure.nlp;

import ch.lianto.aiwiki.engine.policy.nlp.EmbeddingProvider;
import org.springframework.stereotype.Component;

@Component
public class NoOpEmbeddingProvider implements EmbeddingProvider {

    @Override
    public double[] generateEmbedding(String text) {
        return new double[]{1.0, 1.0, 1.0};
    }
}
