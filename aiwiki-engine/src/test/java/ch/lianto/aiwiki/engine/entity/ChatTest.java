package ch.lianto.aiwiki.engine.entity;

import ch.lianto.aiwiki.engine.utils.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class ChatTest {

    private TestData data;

    @BeforeEach
    void setUp() {
        data = new TestData();
    }

    @Test
    void containsSinglePromptWhenPromptingNewChat() {
        Chat chat = new Chat();

        chat.question("Test question");

        assertThat(chat.getMessages()).hasSize(1);
    }

    @Test
    void overridePreviousUnansweredQuestion() {
        String expectedPrompt = "Second Prompt";
        Chat chat = new Chat();

        chat.question("Test prompt");
        chat.question(expectedPrompt);

        assertThat(chat.getMessages()).hasSize(1);
        assertThat(chat.getMessages().get(0).text()).isEqualTo(expectedPrompt);
    }

    @Test
    void throwWhenTryingToGetAnswerOnEmptyChat() {
        Chat chat = new Chat();

        try {
            chat.getLatestAnswer();
            fail("Should have thrown exception on empty chat");
        } catch (IllegalStateException ex) {
        }
    }

    @Test
    void returnLatestReplyFromBasicChat() {
        Chat chat = data.chats.basic;

        String reply = chat.getLatestAnswer();

        assertThat(reply).isEqualTo(data.chats.basicReply);
    }

    @Test
    void returnLatestReplyFromBiggerChat() {
        Chat chat = data.chats.basic
            .question("More questions")
            .answer("More answers");

        String reply = chat.getLatestAnswer();

        assertThat(chat.getMessages()).hasSize(4);
        assertThat(reply).isEqualTo("More answers");
    }

    @Test
    void publishMessageChunks() {
        List<String> chunks = List.of("A", " risk", " analysis", " software");
        Chat chat = data.chats.basic
            .question("What is Lianto?")
            .answer(Flux.just(chunks.toArray(String[]::new)));

        StepVerifier
            .create(chat.getLatestAnswerChunks())
            .expectNextSequence(chunks)
            .expectComplete()
            .verify();
    }

    @Test
    void constructLatestMessageWhenAnsweringWithFlux() {
        List<String> chunks = List.of("A", " risk", " analysis", " software");
        Chat chat = data.chats.basic
            .question("What is Lianto?")
            .answer(Flux.just(chunks.toArray(String[]::new)));

        StepVerifier.create(chat.getLatestAnswerChunks()).expectNextCount(chunks.size()).verifyComplete();

        assertThat(chat.getLatestAnswer()).isEqualTo(String.join("", chunks));
    }
}

