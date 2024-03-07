package ch.lianto.ollama.client.config;

import ch.lianto.ollama.client.ApiClient;
import ch.lianto.ollama.client.api.ChatApi;
import ch.lianto.ollama.client.api.CompletionsApi;
import ch.lianto.ollama.client.api.EmbeddingsApi;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import static ch.lianto.ollama.client.ApiClient.buildWebClientBuilder;

@Configuration
@ComponentScan("ch.lianto.ollama")
public class OllamaClientConfig {

    @Bean
    public ApiClient ollamaApiClient(OllamaClientProperties properties, ObjectMapper objectMapper) {
        var apiClient = new ApiClient(
            buildWebClientBuilder()
                .codecs(configurer -> configurer.customCodecs().register(new Jackson2JsonDecoder(objectMapper, MediaType.APPLICATION_NDJSON)))
                .build()
        );
        return apiClient.setBasePath(properties.getOllamaUrl() + "/api");
    }

    public ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder
            .json()
            .serializationInclusion(JsonInclude.Include.NON_EMPTY)
            .build();
    }

    @Bean
    public ChatApi ollamaChatApi(ApiClient apiClient) {
        return new ChatApi(apiClient);
    }

    @Bean
    public CompletionsApi ollamaCompletionsApi(ApiClient apiClient) {
        return new CompletionsApi(apiClient);
    }

    @Bean
    public EmbeddingsApi ollamaEmbeddingsApi(ApiClient apiClient) {
        return new EmbeddingsApi(apiClient);
    }
}
