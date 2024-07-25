package ch.lianto.aiwiki.engine.infrastructure.nlp.ollama;

import ch.lianto.aiwiki.engine.infrastructure.nlp.AbstractEmbeddingProviderTest;
import ch.lianto.ollama.client.config.OllamaClientConfig;
import ch.lianto.ollama.client.config.OllamaClientProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import java.util.Map;

import static ch.lianto.aiwiki.engine.policy.nlp.EmbeddingProvider.EmbeddingType.DOCUMENT;
import static ch.lianto.aiwiki.engine.policy.nlp.EmbeddingProvider.EmbeddingType.QUERY;

@Disabled
public class OllamaEmbeddingProviderTest extends AbstractEmbeddingProviderTest {
    private static final String EMBEDDING_MODEL = "nomic-embed-text";
    private static final int EMBEDDING_DIMENSIONS = 768;

    @BeforeEach
    void setUp() {
        var properties = new OllamaClientProperties()
            .setOllamaUrl("http://localhost:11434")
            .setEmbeddingModel(EMBEDDING_MODEL)
            .setPromptPrefixes(Map.of(
                QUERY.name(), "search_query:",
                DOCUMENT.name(), "search_document:"
            ));
        var config = new OllamaClientConfig();

        embeddingProvider = new OllamaEmbeddingProvider(config.ollamaEmbeddingsApi(config.ollamaApiClient(properties, config.objectMapper())), properties);
        outputDimensions = EMBEDDING_DIMENSIONS;
    }
}
