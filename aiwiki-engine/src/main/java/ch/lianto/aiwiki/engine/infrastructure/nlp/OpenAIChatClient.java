package ch.lianto.aiwiki.engine.infrastructure.nlp;

import ch.lianto.aiwiki.engine.service.assistant.ChatClient;
import ch.lianto.openai.client.api.ChatApi;
import ch.lianto.openai.client.model.ChatCompletionRequestMessage;
import ch.lianto.openai.client.model.ChatCompletionRequestMessage.RoleEnum;
import ch.lianto.openai.client.model.CreateChatCompletionRequest;
import ch.lianto.openai.client.model.CreateChatCompletionRequestModel;
import ch.lianto.openai.client.model.CreateChatCompletionResponse;
import org.springframework.stereotype.Component;

@Component
public class OpenAIChatClient implements ChatClient {
    private static final String SYSTEM_MESSAGE = "Answer questions accurately and briefly.";
    private static final String ADDITIONAL_CONTEXT = "Use the following information from the knowledge base to answer the user's question:";
    private final ChatApi chatApi;

    public OpenAIChatClient(ChatApi chatApi) {
        this.chatApi = chatApi;
    }

    @Override
    public String message(String message, String... context) {
        if (message.isEmpty()) return "";

        CreateChatCompletionRequest request = createRequest(message, context);
        CreateChatCompletionResponse response = chatApi.createChatCompletion(request);
        return response.getChoices().get(0).getMessage().getContent();
    }

    private CreateChatCompletionRequest createRequest(String message, String[] context) {
        CreateChatCompletionRequest request = new CreateChatCompletionRequest();
        request.setModel(CreateChatCompletionRequestModel._3_5_TURBO);
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
            return SYSTEM_MESSAGE + "\n\n" + ADDITIONAL_CONTEXT + "\n\n" + String.join("\n-----\n", context);
    }
}
