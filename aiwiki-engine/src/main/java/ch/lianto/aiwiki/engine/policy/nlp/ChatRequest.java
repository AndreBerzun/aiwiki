package ch.lianto.aiwiki.engine.policy.nlp;

import ch.lianto.aiwiki.engine.entity.Chat;
import ch.lianto.aiwiki.engine.entity.Message;
import ch.lianto.aiwiki.engine.infrastructure.nlp.PromptTemplates;

public class ChatRequest {
    private String systemPrompt;
    private String userPrompt;
    private boolean jsonMode;

    private ChatRequest() {
    }

    public ChatRequest(String userPrompt, String systemPrompt, boolean jsonMode) {
        this.userPrompt = userPrompt;
        this.systemPrompt = systemPrompt;
        this.jsonMode = jsonMode;
    }

    public static ChatRequest plain(String prompt) {
        if (prompt.isEmpty()) throw new IllegalArgumentException("Cannot answer to empty prompt!");

        return new ChatRequest(prompt, null, false);
    }

    public static ChatRequest rag(String prompt, String... context) {
        if (prompt.isEmpty()) throw new IllegalArgumentException("Cannot answer to empty prompt!");

        return new ChatRequest(prompt, PromptTemplates.rag(context), false);
    }

    public static ChatRequest summary(Chat chat) {
        validateChat(chat);
        return new ChatRequest(PromptTemplates.summary(chat), null, false);
    }

    private static void validateChat(Chat chat) {
        if (chat.getMessages().isEmpty()) throw new IllegalArgumentException("Cannot summarize empty chat");

        long questionsCount = chat.getMessages().stream().filter(m -> m.role() == Message.Role.USER).count();
        long answersCount = chat.getMessages().stream().filter(m -> m.role() == Message.Role.ASSISTANT).count();
        if (answersCount >= questionsCount)
            throw new IllegalArgumentException("Cannot summarize chat when all questions are already answered");
    }

    public static ChatRequest polarize(String text) {
        return new ChatRequest(PromptTemplates.polarize(text), PromptTemplates.POLARIZE_SYSTEM_MESSAGE, true);
    }

    public String getSystemPrompt() {
        return systemPrompt;
    }

    public String getUserPrompt() {
        return userPrompt;
    }

    public boolean isJsonMode() {
        return jsonMode;
    }
}
