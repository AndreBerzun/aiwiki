package ch.lianto.openai.client.config;

import ch.lianto.openai.client.ApiClient;
import ch.lianto.openai.client.api.ChatApi;
import ch.lianto.openai.client.api.EmbeddingsApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("ch.lianto.openai")
public class OpenAIClientConfig {

    @Bean
    public ApiClient openaiApiClient(OpenAIClientProperties properties) {
        ApiClient client = new ApiClient();
        client.setBearerToken(properties.getApiKey());
        return client;
    }

    @Bean
    public ChatApi chatApi(ApiClient openaiApiClient) {
        return new ChatApi(openaiApiClient);
    }

    @Bean
    public EmbeddingsApi embeddingsApi(ApiClient openaiApiClient) {
        return new EmbeddingsApi(openaiApiClient);
    }
}
