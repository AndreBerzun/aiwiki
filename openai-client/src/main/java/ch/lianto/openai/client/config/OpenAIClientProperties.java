package ch.lianto.openai.client.config;

import ch.lianto.openai.client.model.CreateChatCompletionRequestModel;
import ch.lianto.openai.client.model.CreateEmbeddingRequestModel;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app.openai")
@Component
public class OpenAIClientProperties {
    private String apiKey;
    private CreateChatCompletionRequestModel generationModel;
    private CreateEmbeddingRequestModel embeddingModel = CreateEmbeddingRequestModel.ADA_002;

    public String getApiKey() {
        return apiKey;
    }

    public OpenAIClientProperties setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public CreateChatCompletionRequestModel getGenerationModel() {
        return generationModel;
    }

    public OpenAIClientProperties setGenerationModel(CreateChatCompletionRequestModel generationModel) {
        this.generationModel = generationModel;
        return this;
    }

    public CreateEmbeddingRequestModel getEmbeddingModel() {
        return embeddingModel;
    }

    public OpenAIClientProperties setEmbeddingModel(CreateEmbeddingRequestModel embeddingModel) {
        this.embeddingModel = embeddingModel;
        return this;
    }
}
