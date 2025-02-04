package ch.lianto.openai.client.config;

import ch.lianto.openai.client.ApiClient;
import ch.lianto.openai.client.api.ChatApi;
import ch.lianto.openai.client.api.EmbeddingsApi;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.logging.LogLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

import java.time.Duration;

@Configuration
@ComponentScan("ch.lianto.openai")
public class OpenAIClientConfig {

    @Bean
    public ApiClient openaiApiClient(OpenAIClientProperties properties, ObjectMapper objectMapper) {
        ApiClient client = new ApiClient(
            ApiClient.buildWebClientBuilder(objectMapper)
                .clientConnector(new ReactorClientHttpConnector(
                    HttpClient.create()
//                        .wiretap("reactor.netty.http.client.HttpClient", LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL)
                        .responseTimeout(Duration.ofSeconds(20))
                ))
                .build(),
            objectMapper,
            ApiClient.createDefaultDateFormat()
        );
        client.setBearerToken(properties.getApiKey());
        return client;
    }

    public ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder
            .json()
            .serializationInclusion(JsonInclude.Include.NON_EMPTY)
            .build();
    }

    @Bean
    public ChatApi openaiChatApi(ApiClient openaiApiClient) {
        return new ChatApi(openaiApiClient);
    }

    @Bean
    public EmbeddingsApi openaiEmbeddingsApi(ApiClient openaiApiClient) {
        return new EmbeddingsApi(openaiApiClient);
    }
}
