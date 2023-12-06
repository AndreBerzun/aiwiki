package ch.lianto.aiwiki.engine.infrastructure.nlp;

import ch.lianto.aiwiki.engine.entity.Chat;
import ch.lianto.aiwiki.engine.entity.Message;
import ch.lianto.aiwiki.engine.service.nlp.ChatClient;
import ch.lianto.aiwiki.engine.service.nlp.ChatSummaryProvider;
import ch.lianto.openai.client.api.ChatApi;
import ch.lianto.openai.client.config.OpenAIClientProperties;
import ch.lianto.openai.client.model.ChatCompletionRequestMessage;
import ch.lianto.openai.client.model.ChatCompletionRequestMessage.RoleEnum;
import ch.lianto.openai.client.model.CreateChatCompletionRequest;
import ch.lianto.openai.client.model.CreateChatCompletionResponse;
import org.springframework.stereotype.Component;

@Component
public class ChatGPTClient implements ChatClient, ChatSummaryProvider {
    private static final String RAG_TEMPLATE = """
        You're an assistant that helps users answer questions related to their private wikis.
        Be brief in your responses. Answer ONLY with the facts listed in the sources below.
        If there isn't enough information to answer the question, say you don't know. Ask clarifying questions though, if needed.
        Each source contains a page name surrounded by brackets followed by the actual source text.
                
        Sources:
        %s
        """;
    private static final String SUMMARY_TEMPLATE = """
        Given the following chat between a user and a software assistant, briefly rephrase the users last question so that it contains the context of the entire chat:
        %s
        """;

    private final ChatApi chatApi;
    private final OpenAIClientProperties properties;

    public ChatGPTClient(ChatApi chatApi, OpenAIClientProperties properties) {
        this.chatApi = chatApi;
        this.properties = properties;
    }

    @Override
    public String generateResponse(String prompt, String... context) {
        if (prompt.isEmpty()) return "";

        CreateChatCompletionRequest request = createRAGStyleRequest(prompt, context);
        CreateChatCompletionResponse response = chatApi.createChatCompletion(request);
        return getMessageFromApiResponse(response);
    }

    private CreateChatCompletionRequest createRAGStyleRequest(String message, String[] context) {
        CreateChatCompletionRequest request = new CreateChatCompletionRequest();
        request.setModel(properties.getChatModel());
        request.addMessagesItem(createMessage(constructRAGContext(context), RoleEnum.SYSTEM));
        request.addMessagesItem(createMessage(message, RoleEnum.USER));
        return request;
    }

    private String constructRAGContext(String[] context) {
        return String.format(
            RAG_TEMPLATE,
            context.length == 0 ? "None" : String.join("\n", context)
        );
    }

    @Override
    public String summarizeLatestQuestion(Chat chat) {
        validateChat(chat);

        if (containsSinglePrompt(chat)) return chat.getMessages().get(0).text();
        else return fetchSummaryFromApi(chat);
    }

    private void validateChat(Chat chat) {
        if (chat.getMessages().isEmpty())
            throw new IllegalArgumentException("Cannot summarize empty chat");

        long questionsCount = chat.getMessages().stream().filter(m -> m.role() == Message.Role.USER).count();
        long answersCount = chat.getMessages().stream().filter(m -> m.role() == Message.Role.ASSISTANT).count();
        if (answersCount >= questionsCount)
            throw new IllegalArgumentException("Cannot summarize chat when all questions are already answered");
    }

    private boolean containsSinglePrompt(Chat chat) {
        return chat.getMessages().stream().filter(m -> m.role() == Message.Role.USER).count() == 1;
    }

    private String fetchSummaryFromApi(Chat chat) {
        CreateChatCompletionRequest request = createSummaryRequest(chat);
        CreateChatCompletionResponse response = chatApi.createChatCompletion(request);
        return getMessageFromApiResponse(response);
    }

    private CreateChatCompletionRequest createSummaryRequest(Chat chat) {
        CreateChatCompletionRequest request = new CreateChatCompletionRequest();
        request.setModel(properties.getChatModel());
        request.addMessagesItem(createMessage(String.format(SUMMARY_TEMPLATE, chat.toString()), RoleEnum.USER));
        return request;
    }

    private ChatCompletionRequestMessage createMessage(String message, RoleEnum role) {
        ChatCompletionRequestMessage requestMessage = new ChatCompletionRequestMessage();
        requestMessage.setRole(role);
        requestMessage.setContent(message);
        return requestMessage;
    }

    private String getMessageFromApiResponse(CreateChatCompletionResponse response) {
        return response.getChoices().get(0).getMessage().getContent();
    }
}
