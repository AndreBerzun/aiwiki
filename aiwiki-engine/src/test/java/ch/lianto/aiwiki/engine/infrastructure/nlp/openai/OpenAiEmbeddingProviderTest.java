package ch.lianto.aiwiki.engine.infrastructure.nlp.openai;

import ch.lianto.aiwiki.engine.infrastructure.nlp.AbstractEmbeddingProviderTest;
import ch.lianto.openai.client.config.OpenAIClientConfig;
import ch.lianto.openai.client.config.OpenAIClientProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

@Disabled
public class OpenAiEmbeddingProviderTest extends AbstractEmbeddingProviderTest {
    private static final int ADA_EMBEDDING_OUTPUT_DIMENSION = 1536;

    @BeforeEach
    void setUp() {
        OpenAIClientProperties properties = new OpenAIClientProperties()
            .setApiKey(System.getenv("OPENAI_API_KEY"));
        OpenAIClientConfig config = new OpenAIClientConfig();

        embeddingProvider = new OpenAiEmbeddingProvider(config.openaiEmbeddingsApi(config.openaiApiClient(properties, config.objectMapper())), properties);
        outputDimensions = ADA_EMBEDDING_OUTPUT_DIMENSION;
    }
}
