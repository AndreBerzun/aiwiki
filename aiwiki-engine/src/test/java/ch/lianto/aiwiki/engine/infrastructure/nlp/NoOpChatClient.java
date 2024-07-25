package ch.lianto.aiwiki.engine.infrastructure.nlp;

import ch.lianto.aiwiki.engine.policy.nlp.ChatClient;
import ch.lianto.aiwiki.engine.policy.nlp.ChatRequest;
import reactor.core.publisher.Flux;

public class NoOpChatClient implements ChatClient {
    @Override
    public String generateResponse(ChatRequest request) {
        return request.isJsonMode()
            ? "[]"
            : "";
    }

    @Override
    public Flux<String> generateResponseChunks(ChatRequest request) {
        return Flux.just(generateResponse(request));
    }
}
