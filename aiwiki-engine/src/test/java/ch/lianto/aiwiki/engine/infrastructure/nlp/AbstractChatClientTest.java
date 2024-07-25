package ch.lianto.aiwiki.engine.infrastructure.nlp;

import ch.lianto.aiwiki.engine.entity.Chat;
import ch.lianto.aiwiki.engine.policy.nlp.ChatClient;
import ch.lianto.aiwiki.engine.policy.nlp.ChatRequest;
import ch.lianto.aiwiki.engine.utils.TestData;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public abstract class AbstractChatClientTest {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected TestData data;
    protected ChatClient chatClient;

    @Test
    void throwWhenEmptyPrompt() {
        String prompt = "";

        try {
            String response = chatClient.generateResponse(ChatRequest.plain(prompt));
            fail("Should have thrown on empty prompt");
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    void answerWhenAskedGeneralQuestionWithoutContext() {
        String message = data.prompts.generalKnowledgePrompt;

        String response = chatClient.generateResponse(ChatRequest.rag(message));

        logger.info("Chat Response: {}", response);
        assertThat(response).isNotEmpty();
    }

    @Test
    void answerWhenAskedGeneralQuestionWithRandomContext() {
        String message = data.prompts.generalKnowledgePrompt;

        String response = chatClient.generateResponse(
            ChatRequest.rag(message, data.prompts.domainSpecificContext.toArray(String[]::new))
        );

        logger.info("Chat Response: {}", response);
        assertThat(response).isNotEmpty();
    }

    @Test
    void professionallyDeclineWhenAskedDomainSpecificQuestionWithoutContext() {
        String message = data.prompts.domainSpecificPrompt;

        String response = chatClient.generateResponse(ChatRequest.rag(message));

        logger.info("Chat Response: {}", response);
        assertThat(response).isNotEmpty();
    }

    @Test
    void answerDomainSpecificQuestionWhenSuppliedContext() {
        String message = data.prompts.domainSpecificPrompt;

        String response = chatClient.generateResponse(
            ChatRequest.rag(message, data.prompts.domainSpecificContext.toArray(String[]::new))
        );

        logger.info("Chat Response: {}", response);
        assertThat(response).isNotEmpty();
    }

    @Test
    void chunkGeneralAnswer() throws Exception {
        String message = data.prompts.generalKnowledgePrompt;

        Flux<String> response = chatClient.generateResponseChunks(ChatRequest.plain(message));

        String joinedChunks = response.log().toStream().reduce((result, next) -> result + next).get();
        assertThat(joinedChunks).isNotEmpty();
    }

    @Test
    void chunkDomainSpecificAnswer() throws Exception {
        String message = data.prompts.domainSpecificPrompt;

        Flux<String> response = chatClient.generateResponseChunks(
            ChatRequest.rag(message, data.prompts.domainSpecificContext.toArray(String[]::new))
        );

        String joinedChunks = response.log().toStream().reduce((result, next) -> result + next).get();
        assertThat(joinedChunks).isNotEmpty();
    }

    @Test
    void throwWhenSummarizingEmptyChat() {
        Chat chat = new Chat();

        try {
            chatClient.generateResponse(ChatRequest.summary(chat));
            fail("Should have thrown exception on empty chat");
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    void throwWhenAllQuestionsAlreadyAnswered() {
        Chat chat = new Chat()
            .question("Who worked on the Lianto software?")
            .answer("Andre")
            .question("And what was the name inspired by?")
            .answer("A book");

        try {
            chatClient.generateResponse(ChatRequest.summary(chat));
            fail("Should have thrown exception because there was no question to summarize, everything already answered");
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    void summarizeWhenProvidedValidChat() {
        Chat chat = data.chats.big;

        String result = chatClient.generateResponse(ChatRequest.summary(chat));

        logger.info("Created summary: {}", result);
        assertThat(result).isNotEmpty();
    }
}
