package ch.lianto.aiwiki.engine.infrastructure.nlp.openai;

import ch.lianto.aiwiki.engine.infrastructure.nlp.AbstractChatClientTest;
import ch.lianto.aiwiki.engine.utils.TestData;
import ch.lianto.openai.client.config.OpenAIClientConfig;
import ch.lianto.openai.client.config.OpenAIClientProperties;
import ch.lianto.openai.client.model.CreateChatCompletionRequestModel;
import ch.lianto.openai.client.model.CreateEmbeddingRequestModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@Disabled
public class ChatGPTClientTest extends AbstractChatClientTest {

    @BeforeEach
    void setUp() {
        data = new TestData();
        OpenAIClientProperties properties = new OpenAIClientProperties()
            .setApiKey(System.getenv("OPENAI_API_KEY"))
            .setGenerationModel(CreateChatCompletionRequestModel._3_5_TURBO)
            .setEmbeddingModel(CreateEmbeddingRequestModel.TEXT_EMBEDDING_ADA_002);
        OpenAIClientConfig config = new OpenAIClientConfig();

        chatClient = new ChatGPTClient(config.openaiChatApi(config.openaiApiClient(properties, config.objectMapper())), properties);
    }
}
