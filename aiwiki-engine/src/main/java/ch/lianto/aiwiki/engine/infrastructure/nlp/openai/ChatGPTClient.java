package ch.lianto.aiwiki.engine.infrastructure.nlp.openai;

import ch.lianto.aiwiki.engine.policy.nlp.ChatClient;
import ch.lianto.aiwiki.engine.policy.nlp.ChatRequest;
import ch.lianto.openai.client.api.ChatApi;
import ch.lianto.openai.client.config.OpenAIClientProperties;
import ch.lianto.openai.client.model.ChatCompletionRequestMessage;
import ch.lianto.openai.client.model.ChatCompletionRequestMessage.RoleEnum;
import ch.lianto.openai.client.model.CreateChatCompletionRequest;
import ch.lianto.openai.client.model.CreateChatCompletionRequestResponseFormat;
import ch.lianto.openai.client.model.CreateChatCompletionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import static ch.lianto.openai.client.model.CreateChatCompletionRequestResponseFormat.TypeEnum.JSON_OBJECT;
import static org.apache.commons.lang3.StringUtils.abbreviate;

@Profile("openai-generation")
@Component
public class ChatGPTClient implements ChatClient {
    private static final String CLOSING_CHUNK = "[DONE]";
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ChatApi chatApi;
    private final ObjectMapper objectMapper;
    private final OpenAIClientProperties properties;

    public ChatGPTClient(ChatApi chatApi, OpenAIClientProperties properties) {
        this.chatApi = chatApi;
        this.objectMapper = chatApi.getApiClient().getObjectMapper();
        this.properties = properties;
    }

    @Override
    public String generateResponse(ChatRequest request) {
        log.info("Creating completion request for prompt <{}>", abbreviate(request.getUserPrompt(), 30));
        CreateChatCompletionRequest apiRequest = createApiRequest(request, false);
        CreateChatCompletionResponse apiResponse = chatApi.createChatCompletion(apiRequest).block();
        log.info("Received completion response for <{}>", abbreviate(request.getUserPrompt(), 30));
        log.info("Tokens used: <{}> Total, <{}> Input, <{}> Output", apiResponse.getUsage().getTotalTokens(), apiResponse.getUsage().getPromptTokens(), apiResponse.getUsage().getCompletionTokens());
        return getContentFromApiResponse(apiResponse);
    }

    @Override
    public Flux<String> generateResponseChunks(ChatRequest request) {
        CreateChatCompletionRequest apiRequest = createApiRequest(request, true);
        Flux<CreateChatCompletionResponse> responseChunks = fetchResponseChunks(apiRequest);
        return responseChunks.mapNotNull(this::getContentFromApiResponseChunks);
    }

    private CreateChatCompletionRequest createApiRequest(ChatRequest request, boolean stream) {
        CreateChatCompletionRequest apiRequest = new CreateChatCompletionRequest();
        apiRequest.setModel(properties.getGenerationModel());
        apiRequest.setStream(stream);
        if (request.isJsonMode())
            apiRequest.setResponseFormat(new CreateChatCompletionRequestResponseFormat().type(JSON_OBJECT));
        if (request.getSystemPrompt() != null)
            apiRequest.addMessagesItem(createMessage(request.getSystemPrompt(), RoleEnum.SYSTEM));
        apiRequest.addMessagesItem(createMessage(request.getUserPrompt(), RoleEnum.USER));
        return apiRequest;
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
