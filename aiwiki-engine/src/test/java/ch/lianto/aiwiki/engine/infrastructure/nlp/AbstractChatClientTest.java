package ch.lianto.aiwiki.engine.infrastructure.nlp;

import ch.lianto.aiwiki.engine.policy.nlp.ChatClient;
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
    void chunkGeneralAnswer() throws Exception {
        String message = data.prompts.generalKnowledgePrompt;

        Flux<String> response = chatClient.generateResponseChunks(message);

        String joinedChunks = response.log().toStream().reduce((result, next) -> result + next).get();
        assertThat(joinedChunks).isNotEmpty();
    }

    @Test
    void chunkDomainSpecificAnswer() throws Exception {
        String message = data.prompts.domainSpecificPrompt;

        Flux<String> response = chatClient.generateResponseChunks(message, data.prompts.domainSpecificContext.toArray(String[]::new));

        String joinedChunks = response.log().toStream().reduce((result, next) -> result + next).get();
        assertThat(joinedChunks).isNotEmpty();
    }
}
