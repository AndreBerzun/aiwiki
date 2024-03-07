package ch.lianto.aiwiki.engine.infrastructure.nlp.openai;

import ch.lianto.aiwiki.engine.infrastructure.nlp.AbstractChatSummaryProviderTest;
import ch.lianto.aiwiki.engine.utils.TestData;
import ch.lianto.openai.client.config.OpenAIClientConfig;
import ch.lianto.openai.client.config.OpenAIClientProperties;
import ch.lianto.openai.client.model.CreateChatCompletionRequestModel;
import ch.lianto.openai.client.model.CreateEmbeddingRequestModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

@Disabled
public class ChatGPTSummaryProviderTest extends AbstractChatSummaryProviderTest {

    @BeforeEach
    void setUp() {
        data = new TestData();
        OpenAIClientProperties properties = new OpenAIClientProperties()
            .setApiKey(System.getenv("OPENAI_API_KEY"))
            .setGenerationModel(CreateChatCompletionRequestModel._3_5_TURBO)
            .setEmbeddingModel(CreateEmbeddingRequestModel.TEXT_EMBEDDING_ADA_002);
        OpenAIClientConfig config = new OpenAIClientConfig();

        summaryProvider = new ChatGPTClient(config.openaiChatApi(config.openaiApiClient(properties, config.objectMapper())), properties);
    }
}
