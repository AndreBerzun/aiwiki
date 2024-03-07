package ch.lianto.aiwiki.engine.infrastructure.nlp.openai;

import ch.lianto.aiwiki.engine.entity.Chat;
import ch.lianto.aiwiki.engine.infrastructure.nlp.AbstractChatSummaryProvider;
import ch.lianto.aiwiki.engine.infrastructure.nlp.PromptTemplates;
import ch.lianto.aiwiki.engine.policy.nlp.ChatClient;
import ch.lianto.aiwiki.engine.policy.nlp.ChatSummaryProvider;
import ch.lianto.openai.client.api.ChatApi;
import ch.lianto.openai.client.config.OpenAIClientProperties;
import ch.lianto.openai.client.model.ChatCompletionRequestMessage;
import ch.lianto.openai.client.model.ChatCompletionRequestMessage.RoleEnum;
import ch.lianto.openai.client.model.CreateChatCompletionRequest;
import ch.lianto.openai.client.model.CreateChatCompletionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Profile("openai-generation")
@Primary
@Component
public class ChatGPTClient extends AbstractChatSummaryProvider implements ChatClient, ChatSummaryProvider {
    private static final String CLOSING_CHUNK = "[DONE]";
    private final ChatApi chatApi;
    private final ObjectMapper objectMapper;
    private final OpenAIClientProperties properties;

    public ChatGPTClient(ChatApi chatApi, OpenAIClientProperties properties) {
        this.chatApi = chatApi;
        this.objectMapper = chatApi.getApiClient().getObjectMapper();
        this.properties = properties;
    }

    @Override
    public String generateResponse(String prompt, String... context) {
        if (prompt.isEmpty()) throw new IllegalArgumentException("Cannot answer to empty prompt!");

        CreateChatCompletionRequest request = createRAGStyleRequest(prompt, context, false);
        CreateChatCompletionResponse response = chatApi.createChatCompletion(request).block();
        return getContentFromApiResponse(response);
    }

    private CreateChatCompletionRequest createRAGStyleRequest(String message, String[] context, boolean stream) {
        CreateChatCompletionRequest request = new CreateChatCompletionRequest();
        request.setModel(properties.getGenerationModel());
        request.setStream(stream);
        request.addMessagesItem(createMessage(PromptTemplates.rag(context), RoleEnum.SYSTEM));
        request.addMessagesItem(createMessage(message, RoleEnum.USER));
        return request;
    }

    @Override
    public Flux<String> generateResponseChunks(String prompt, String... context) {
        if (prompt.isEmpty()) throw new IllegalArgumentException("Cannot answer to empty prompt!");

        CreateChatCompletionRequest request = createRAGStyleRequest(prompt, context, true);
        Flux<CreateChatCompletionResponse> responseChunks = fetchResponseChunks(request);
        return responseChunks.mapNotNull(this::getContentFromApiResponseChunks);
    }

    private Flux<CreateChatCompletionResponse> fetchResponseChunks(CreateChatCompletionRequest request) {
        return chatApi.createChatCompletionWithResponseSpec(request)
            .bodyToFlux(new ParameterizedTypeReference<ServerSentEvent<String>>() {
            })
            .mapNotNull(this::parseServerSentEvent);
    }

    private CreateChatCompletionResponse parseServerSentEvent(ServerSentEvent<String> event) {
        try {
            return CLOSING_CHUNK.equals(event.data())
                ? null
                : objectMapper.readValue(event.data(), CreateChatCompletionResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String getContentFromApiResponseChunks(CreateChatCompletionResponse response) {
        return response.getChoices().get(0).getDelta().getContent();
    }

    @Override
    protected String fetchSummaryFromApi(Chat chat) {
        CreateChatCompletionRequest request = createSummaryRequest(chat);
        CreateChatCompletionResponse response = chatApi.createChatCompletion(request).block();
        return getContentFromApiResponse(response);
    }

    private CreateChatCompletionRequest createSummaryRequest(Chat chat) {
        CreateChatCompletionRequest request = new CreateChatCompletionRequest();
        request.setModel(properties.getGenerationModel());
        request.addMessagesItem(createMessage(PromptTemplates.summary(chat), RoleEnum.USER));
        return request;
    }

    private ChatCompletionRequestMessage createMessage(String message, RoleEnum role) {
        ChatCompletionRequestMessage requestMessage = new ChatCompletionRequestMessage();
        requestMessage.setRole(role);
        requestMessage.setContent(message);
        return requestMessage;
    }

    private String getContentFromApiResponse(CreateChatCompletionResponse response) {
        return response.getChoices().get(0).getMessage().getContent();
    }
}
