package ch.lianto.aiwiki.engine.entity;

import reactor.core.Exceptions;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    private List<Message> messages = new ArrayList<>();
    private StringBuilder latestMessageBuilder = new StringBuilder();
    private Flux<String> latestMessage;

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

    public Chat answer(Flux<String> answer) {
        latestMessageBuilder = new StringBuilder();
        latestMessage = answer;
        latestMessage.subscribe(
            latestMessageBuilder::append,
            Exceptions::errorCallbackNotImplemented,
            () -> answer(latestMessageBuilder.toString())
        );
        return this;
    }

    public String getLatestAnswer() {
        for (int i = messages.size() - 1; i >= 0; i--) {
            Message message = messages.get(i);
            if (message.role() == Message.Role.ASSISTANT) return message.text();
        }

        throw new IllegalStateException("No assistant replies yet");
    }

    public Flux<String> getLatestAnswerChunks() {
        return latestMessage;
    }

    @Override
    public String toString() {
        return messages.stream()
            .map(m -> String.format("%s: %s", m.role(), m.text()))
            .reduce((result, message) -> result + "\n" + message)
            .orElse("");
    }
}
