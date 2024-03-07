package ch.lianto.aiwiki.engine.policy.nlp;

public interface EmbeddingProvider {
    double[] generateEmbedding(String text, EmbeddingType type);

    enum EmbeddingType {
        SEARCH_QUERY, SEARCH_DOCUMENT, CLASSIFICATION, CLUSTER
    }
}
