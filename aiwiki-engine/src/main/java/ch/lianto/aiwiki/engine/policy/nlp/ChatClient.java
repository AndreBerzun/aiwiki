package ch.lianto.aiwiki.engine.policy.nlp;

import reactor.core.publisher.Flux;

public interface ChatClient {
    String generateResponse(ChatRequest request);

    Flux<String> generateResponseChunks(ChatRequest request);
}
