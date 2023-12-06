package ch.lianto.aiwiki.engine.service.nlp;

public interface EmbeddingProvider {
    double[] generateEmbedding(String text);
}
