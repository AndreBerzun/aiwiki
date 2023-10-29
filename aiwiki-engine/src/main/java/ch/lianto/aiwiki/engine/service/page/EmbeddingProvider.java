package ch.lianto.aiwiki.engine.service.page;

public interface EmbeddingProvider {
    double[] generateEmbedding(String text);
}
