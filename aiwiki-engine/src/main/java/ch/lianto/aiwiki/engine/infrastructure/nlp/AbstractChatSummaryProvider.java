package ch.lianto.aiwiki.engine.infrastructure.nlp;

import ch.lianto.aiwiki.engine.entity.Chat;
import ch.lianto.aiwiki.engine.entity.Message;
import ch.lianto.aiwiki.engine.policy.nlp.ChatSummaryProvider;

public abstract class AbstractChatSummaryProvider implements ChatSummaryProvider {

    @Override
    public String summarizeLatestQuestion(Chat chat) {
        validateChat(chat);

        if (containsSinglePrompt(chat)) return chat.getMessages().get(0).text();
        else return fetchSummaryFromApi(chat);
    }

    private void validateChat(Chat chat) {
        if (chat.getMessages().isEmpty()) throw new IllegalArgumentException("Cannot summarize empty chat");

        long questionsCount = chat.getMessages().stream().filter(m -> m.role() == Message.Role.USER).count();
        long answersCount = chat.getMessages().stream().filter(m -> m.role() == Message.Role.ASSISTANT).count();
        if (answersCount >= questionsCount)
            throw new IllegalArgumentException("Cannot summarize chat when all questions are already answered");
    }

    private boolean containsSinglePrompt(Chat chat) {
        return chat.getMessages().stream().filter(m -> m.role() == Message.Role.USER).count() == 1;
    }

    protected abstract String fetchSummaryFromApi(Chat chat);
}
