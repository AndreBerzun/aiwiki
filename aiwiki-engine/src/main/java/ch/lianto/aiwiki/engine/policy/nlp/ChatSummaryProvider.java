package ch.lianto.aiwiki.engine.policy.nlp;

import ch.lianto.aiwiki.engine.entity.Chat;

public interface ChatSummaryProvider {
    String summarizeLatestQuestion(Chat chat);
}
