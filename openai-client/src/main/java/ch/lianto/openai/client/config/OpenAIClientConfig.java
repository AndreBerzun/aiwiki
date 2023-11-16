package ch.lianto.openai.client.config;

import ch.lianto.openai.client.ApiClient;
import ch.lianto.openai.client.api.ChatApi;
import ch.lianto.openai.client.api.EmbeddingsApi;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("ch.lianto.openai")
public class OpenAIClientConfig {

    @Bean
    public ApiClient openaiApiClient(OpenAIClientProperties properties) {
        ApiClient client = new ApiClient() {
            @Override
            protected RestTemplate buildRestTemplate() {
                RestTemplate restTemplate = super.buildRestTemplate();

                for (int i = 0; i < restTemplate.getMessageConverters().size(); i++) {
                    final HttpMessageConverter<?> httpMessageConverter = restTemplate.getMessageConverters().get(i);
                    if (httpMessageConverter instanceof MappingJackson2HttpMessageConverter) {
                        restTemplate.getMessageConverters().set(i, customJacksonConverter());
                    }
                }

                return restTemplate;
            }
        };
        client.setBearerToken(properties.getApiKey());
        return client;
    }

    @Bean
    public MappingJackson2HttpMessageConverter customJacksonConverter() {
        return new MappingJackson2HttpMessageConverter(
            Jackson2ObjectMapperBuilder
                .json()
                .serializationInclusion(JsonInclude.Include.NON_EMPTY)
                .build()
        );
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
