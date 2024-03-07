package ch.lianto.aiwiki.engine.infrastructure.nlp.ollama;

import ch.lianto.aiwiki.engine.infrastructure.nlp.AbstractChatClientTest;
import ch.lianto.aiwiki.engine.utils.TestData;
import ch.lianto.ollama.client.config.OllamaClientConfig;
import ch.lianto.ollama.client.config.OllamaClientProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

@Disabled
public class OllamaChatClientTest extends AbstractChatClientTest {

    @BeforeEach
    void setUp() {
        data = new TestData();
        var properties = new OllamaClientProperties()
            .setOllamaUrl("http://localhost:11434")
            .setGenerationModel("gemma:2b");
        var config = new OllamaClientConfig();

        chatClient = new OllamaChatClient(config.ollamaCompletionsApi(config.ollamaApiClient(properties, config.objectMapper())), properties);
    }
}
