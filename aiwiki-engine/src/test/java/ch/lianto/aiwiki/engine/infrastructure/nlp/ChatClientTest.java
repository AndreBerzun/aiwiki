package ch.lianto.aiwiki.engine.infrastructure.nlp;

import ch.lianto.aiwiki.engine.service.nlp.ChatClient;
import ch.lianto.aiwiki.engine.utils.TestData;
import ch.lianto.openai.client.config.OpenAIClientConfig;
import ch.lianto.openai.client.config.OpenAIClientProperties;
import ch.lianto.openai.client.model.CreateChatCompletionRequestModel;
import ch.lianto.openai.client.model.CreateEmbeddingRequestModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@Disabled
public class ChatClientTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private TestData data;
    private ChatClient chatClient;

    @BeforeEach
    void setUp() {
        data = new TestData();
        OpenAIClientProperties properties = new OpenAIClientProperties()
            .setApiKey(System.getenv("OPENAI_API_KEY"))
            .setChatModel(CreateChatCompletionRequestModel._3_5_TURBO)
            .setEmbeddingModel(CreateEmbeddingRequestModel.TEXT_EMBEDDING_ADA_002);
        OpenAIClientConfig config = new OpenAIClientConfig();

        chatClient = new ChatGPTClient(config.chatApi(config.openaiApiClient(properties, config.objectMapper())), properties);
    }

    @Test
    void throwWhenEmptyPrompt() {
        String prompt = "";

        try {
            String response = chatClient.generateResponse(prompt);
            fail("Should have thrown on empty prompt");
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    void answerWhenAskedGeneralQuestionWithoutContext() {
        String message = data.prompts.generalKnowledgePrompt;

        String response = chatClient.generateResponse(message);

        logger.info("Chat Response: {}", response);
        assertThat(response).isNotEmpty();
    }

    @Test
    void answerWhenAskedGeneralQuestionWithRandomContext() {
        String message = data.prompts.generalKnowledgePrompt;

        String response = chatClient.generateResponse(message, data.prompts.domainSpecificContext.toArray(String[]::new));

        logger.info("Chat Response: {}", response);
        assertThat(response).isNotEmpty();
    }

    @Test
    void professionallyDeclineWhenAskedDomainSpecificQuestionWithoutContext() {
        String message = data.prompts.domainSpecificPrompt;

        String response = chatClient.generateResponse(message);

        logger.info("Chat Response: {}", response);
        assertThat(response).isNotEmpty();
    }

    @Test
    void answerDomainSpecificQuestionWhenSuppliedContext() {
        String message = data.prompts.domainSpecificPrompt;

        String response = chatClient.generateResponse(message, data.prompts.domainSpecificContext.toArray(String[]::new));

        logger.info("Chat Response: {}", response);
        assertThat(response).isNotEmpty();
    }

    @Test
    void chunkDomainSpecificAnswer() throws Exception {
        String message = data.prompts.domainSpecificPrompt;

        Flux<String> response = chatClient.generateResponseChunks(message, data.prompts.domainSpecificContext.toArray(String[]::new));

        String joinedChunks = response.log().toStream().reduce((result, next) -> result + next).get();
        assertThat(joinedChunks).isNotEmpty();
    }
}
