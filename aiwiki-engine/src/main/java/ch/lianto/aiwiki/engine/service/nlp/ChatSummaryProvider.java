package ch.lianto.aiwiki.engine.service.nlp;

import ch.lianto.aiwiki.engine.entity.Chat;

public interface ChatSummaryProvider {
    String summarizeLatestQuestion(Chat chat);
}
