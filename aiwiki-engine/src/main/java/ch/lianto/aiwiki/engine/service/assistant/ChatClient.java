package ch.lianto.aiwiki.engine.service.assistant;

public interface ChatClient {
    String message(String message, String... context);
}
