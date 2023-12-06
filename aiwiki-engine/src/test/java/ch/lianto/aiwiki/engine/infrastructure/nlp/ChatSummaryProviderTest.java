package ch.lianto.aiwiki.engine.infrastructure.nlp;

import ch.lianto.aiwiki.engine.entity.Chat;
import ch.lianto.aiwiki.engine.service.nlp.ChatSummaryProvider;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@Disabled
public class ChatSummaryProviderTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private TestData data;
    private ChatSummaryProvider summaryProvider;

    @BeforeEach
    void setUp() {
        data = new TestData();
        OpenAIClientProperties properties = new OpenAIClientProperties()
            .setApiKey(System.getenv("OPENAI_API_KEY"))
            .setChatModel(CreateChatCompletionRequestModel._3_5_TURBO)
            .setEmbeddingModel(CreateEmbeddingRequestModel.TEXT_EMBEDDING_ADA_002);
        OpenAIClientConfig config = new OpenAIClientConfig();

        summaryProvider = new ChatGPTClient(config.chatApi(config.openaiApiClient(properties)), properties);
    }

    @Test
    void throwWhenSummarizingEmptyChat() {
        Chat chat = new Chat();

        try {
            summaryProvider.summarizeLatestQuestion(chat);
            fail("Should have thrown exception on empty chat");
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    void returnInitialQuestionWhenNoOtherContext() {
        String expectedPrompt = "What is the Lianto software system?";
        Chat chat = new Chat().question(expectedPrompt);

        String actualPrompt = summaryProvider.summarizeLatestQuestion(chat);

        assertThat(actualPrompt).isEqualTo(expectedPrompt);
    }

    @Test
    void throwWhenAllQuestionsAlreadyAnswered() {
        Chat chat = new Chat()
            .question("Who worked on the Lianto software?")
            .answer("Andre")
            .question("And what was the name inspired by?")
            .answer("A book");

        try {
            summaryProvider.summarizeLatestQuestion(chat);
            fail("Should have thrown exception because there was no question to summarize, everything already answered");
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    void summarizeWhenProvidedValidChat() {
        Chat chat = data.chats.big;

        String result = summaryProvider.summarizeLatestQuestion(chat);

        logger.info("Created summary: {}", result);
        assertThat(result).isNotEmpty();
    }
}
