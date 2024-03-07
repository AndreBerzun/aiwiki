package ch.lianto.aiwiki.engine.infrastructure.nlp.ollama;

import ch.lianto.aiwiki.engine.infrastructure.nlp.AbstractChatSummaryProviderTest;
import ch.lianto.aiwiki.engine.utils.TestData;
import ch.lianto.ollama.client.config.OllamaClientConfig;
import ch.lianto.ollama.client.config.OllamaClientProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

@Disabled
public class OllamaSummaryProviderTest extends AbstractChatSummaryProviderTest {

    @BeforeEach
    void setUp() {
        data = new TestData();
        var properties = new OllamaClientProperties()
            .setOllamaUrl("http://localhost:11434")
            .setGenerationModel("gemma:2b");
        var config = new OllamaClientConfig();

        summaryProvider = new OllamaChatClient(config.ollamaCompletionsApi(config.ollamaApiClient(properties, config.objectMapper())), properties);
    }
}
