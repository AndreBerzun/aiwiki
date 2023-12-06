package ch.lianto.aiwiki.engine.service.nlp;

public interface ChatClient {
    String generateResponse(String prompt, String... context);
}
