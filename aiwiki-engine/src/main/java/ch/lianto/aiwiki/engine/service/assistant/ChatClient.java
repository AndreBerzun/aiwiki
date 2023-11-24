package ch.lianto.aiwiki.engine.service.assistant;

public interface ChatClient {
    String generateResponse(String prompt, String... context);
}
