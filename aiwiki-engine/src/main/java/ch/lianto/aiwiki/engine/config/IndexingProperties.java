package ch.lianto.aiwiki.engine.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app.indexing")
@Component
public class IndexingProperties {
    private Chunking chunking = Chunking.MAX_TOKEN;
    private boolean enableAdditionalEmbeddings = false;

    public Chunking getChunking() {
        return chunking;
    }

    public IndexingProperties setChunking(Chunking chunking) {
        this.chunking = chunking;
        return this;
    }

    public boolean isEnableAdditionalEmbeddings() {
        return enableAdditionalEmbeddings;
    }

    public IndexingProperties setEnableAdditionalEmbeddings(boolean enableAdditionalEmbeddings) {
        this.enableAdditionalEmbeddings = enableAdditionalEmbeddings;
        return this;
    }

    public enum Chunking {
        MAX_TOKEN, PARAGRAPH
    }

    public static class ChunkingConstants {
        public static final String MAX_TOKEN = "MAX_TOKEN";
        public static final String PARAGRAPH = "PARAGRAPH";
    }
}