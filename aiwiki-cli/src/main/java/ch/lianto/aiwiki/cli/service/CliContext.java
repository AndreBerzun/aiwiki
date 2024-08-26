package ch.lianto.aiwiki.cli.service;

import ch.lianto.aiwiki.engine.entity.Chat;
import ch.lianto.aiwiki.engine.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class CliContext {
    private Chat assistantChat = new Chat();
    private boolean assistantMode = false;

    public void clearChat() {
        assistantChat = new Chat();
    }

    public Chat getAssistantChat() {
        return assistantChat;
    }

    public boolean isAssistantMode() {
        return assistantMode;
    }

    public void setAssistantMode(boolean assistantMode) {
        this.assistantMode = assistantMode;
    }
}
