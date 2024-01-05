package ch.lianto.aiwiki.engine.policy.nlp;

public interface EmbeddingProvider {
    double[] generateEmbedding(String text);
}
