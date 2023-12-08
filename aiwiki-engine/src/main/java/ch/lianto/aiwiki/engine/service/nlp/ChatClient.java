package ch.lianto.aiwiki.engine.service.nlp;

import reactor.core.publisher.Flux;

public interface ChatClient {
    String generateResponse(String prompt, String... context);

    Flux<String> generateResponseChunks(String prompt, String... context);
}
