package ch.lianto.aiwiki.engine.infrastructure.nlp;

import ch.lianto.aiwiki.engine.service.assistant.ChatClient;
import ch.lianto.openai.client.api.ChatApi;
import ch.lianto.openai.client.config.OpenAIClientProperties;
import ch.lianto.openai.client.model.ChatCompletionRequestMessage;
import ch.lianto.openai.client.model.ChatCompletionRequestMessage.RoleEnum;
import ch.lianto.openai.client.model.CreateChatCompletionRequest;
import ch.lianto.openai.client.model.CreateChatCompletionResponse;
import org.springframework.stereotype.Component;

@Component
public class OpenAIChatClient implements ChatClient {
    private static final String SYSTEM_MESSAGE = """
        Your an assistant that helps users answer questions related to their private wikis.
        Be brief in your responses. Answer ONLY with the facts listed in the sources below.
        If there isn't enough information to answer the question, say you don't know. Ask clarifying questions though, if needed.
        Each source contains a page name surrounded by brackets followed by the actual source text.""";
    private static final String ADDITIONAL_CONTEXT = "Sources:";
    private final ChatApi chatApi;
    private final OpenAIClientProperties properties;

    public OpenAIChatClient(ChatApi chatApi, OpenAIClientProperties properties) {
        this.chatApi = chatApi;
        this.properties = properties;
    }

    @Override
    public String generateResponse(String prompt, String... context) {
        if (prompt.isEmpty()) return "";

        CreateChatCompletionRequest request = createRequest(prompt, context);
        CreateChatCompletionResponse response = chatApi.createChatCompletion(request);
        return response.getChoices().get(0).getMessage().getContent();
    }

    private CreateChatCompletionRequest createRequest(String message, String[] context) {
        CreateChatCompletionRequest request = new CreateChatCompletionRequest();
        request.setModel(properties.getChatModel());
        request.addMessagesItem(createMessage(constructContext(context), RoleEnum.SYSTEM));
        request.addMessagesItem(createMessage(message, RoleEnum.USER));
        return request;
    }

    private ChatCompletionRequestMessage createMessage(String message, RoleEnum role) {
        ChatCompletionRequestMessage requestMessage = new ChatCompletionRequestMessage();
        requestMessage.setRole(role);
        requestMessage.setContent(message);
        return requestMessage;
    }

    private String constructContext(String[] context) {
        if (context.length == 0)
            return SYSTEM_MESSAGE;
        else
            return SYSTEM_MESSAGE + "\n\n" + ADDITIONAL_CONTEXT + "\n" + String.join("\n", context);
    }
}
