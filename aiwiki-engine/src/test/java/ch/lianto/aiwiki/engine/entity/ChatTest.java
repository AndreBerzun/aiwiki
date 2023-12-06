package ch.lianto.aiwiki.engine.entity;

import ch.lianto.aiwiki.engine.utils.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        Message reply = chat.getLatestAnswer();

        assertThat(reply.role()).isEqualTo(Message.Role.ASSISTANT);
        assertThat(reply.text()).isEqualTo(data.chats.basicReply);
    }

    @Test
    void returnLatestReplyFromBiggerChat() {
        Chat chat = data.chats.basic
            .question("More questions")
            .answer("More answers");

        Message reply = chat.getLatestAnswer();

        assertThat(chat.getMessages()).hasSize(4);
        assertThat(reply.role()).isEqualTo(Message.Role.ASSISTANT);
        assertThat(reply.text()).isEqualTo("More answers");
    }
}

