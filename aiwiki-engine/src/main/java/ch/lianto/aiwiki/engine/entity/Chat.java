package ch.lianto.aiwiki.engine.entity;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    private List<Message> messages = new ArrayList<>();

    public List<Message> getMessages() {
        return messages;
    }

    public Chat question(String question) {
        boolean alreadyAskedPrompt = !messages.isEmpty() && messages.getLast().role() == Message.Role.USER;
        if (alreadyAskedPrompt) messages.removeLast();
        messages.add(new Message(question, Message.Role.USER));
        return this;
    }

    public Chat answer(String answer) {
        messages.add(new Message(answer, Message.Role.ASSISTANT));
        return this;
    }

    public Message getLatestAnswer() {
        for (int i = messages.size() - 1; i >= 0; i--) {
            Message message = messages.get(i);
            if (message.role() == Message.Role.ASSISTANT) return message;
        }

        throw new IllegalStateException("No assistant replies yet");
    }

    @Override
    public String toString() {
        return messages.stream()
            .map(m -> String.format("%s: %s", m.role(), m.text()))
            .reduce((result, message) -> result + "\n" + message)
            .orElse("");
    }
}
