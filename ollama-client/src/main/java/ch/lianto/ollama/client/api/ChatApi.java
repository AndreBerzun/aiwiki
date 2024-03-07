package ch.lianto.ollama.client.api;

import ch.lianto.ollama.client.ApiClient;

import ch.lianto.ollama.client.model.GenerateChatCompletionRequest;
import ch.lianto.ollama.client.model.GenerateChatCompletionResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-03-06T00:48:30.515751098+01:00[Europe/Zurich]")
public class ChatApi {
    private ApiClient apiClient;

    public ChatApi() {
        this(new ApiClient());
    }

    @Autowired
    public ChatApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Generate the next message in a chat with a provided model.
     * This is a streaming endpoint, so there will be a series of responses. The final response object will include statistics and additional data from the request.
     * <p><b>200</b> - Successful operation.
     * @param generateChatCompletionRequest The generateChatCompletionRequest parameter
     * @return GenerateChatCompletionResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec generateChatCompletionRequestCreation(GenerateChatCompletionRequest generateChatCompletionRequest) throws WebClientResponseException {
        Object postBody = generateChatCompletionRequest;
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/x-ndjson"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
        };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<GenerateChatCompletionResponse> localVarReturnType = new ParameterizedTypeReference<GenerateChatCompletionResponse>() {};
        return apiClient.invokeAPI("/chat", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Generate the next message in a chat with a provided model.
     * This is a streaming endpoint, so there will be a series of responses. The final response object will include statistics and additional data from the request.
     * <p><b>200</b> - Successful operation.
     * @param generateChatCompletionRequest The generateChatCompletionRequest parameter
     * @return GenerateChatCompletionResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<GenerateChatCompletionResponse> generateChatCompletion(GenerateChatCompletionRequest generateChatCompletionRequest) throws WebClientResponseException {
        ParameterizedTypeReference<GenerateChatCompletionResponse> localVarReturnType = new ParameterizedTypeReference<GenerateChatCompletionResponse>() {};
        return generateChatCompletionRequestCreation(generateChatCompletionRequest).bodyToMono(localVarReturnType);
    }

    /**
     * Generate the next message in a chat with a provided model.
     * This is a streaming endpoint, so there will be a series of responses. The final response object will include statistics and additional data from the request.
     * <p><b>200</b> - Successful operation.
     * @param generateChatCompletionRequest The generateChatCompletionRequest parameter
     * @return ResponseEntity&lt;GenerateChatCompletionResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<GenerateChatCompletionResponse>> generateChatCompletionWithHttpInfo(GenerateChatCompletionRequest generateChatCompletionRequest) throws WebClientResponseException {
        ParameterizedTypeReference<GenerateChatCompletionResponse> localVarReturnType = new ParameterizedTypeReference<GenerateChatCompletionResponse>() {};
        return generateChatCompletionRequestCreation(generateChatCompletionRequest).toEntity(localVarReturnType);
    }

    /**
     * Generate the next message in a chat with a provided model.
     * This is a streaming endpoint, so there will be a series of responses. The final response object will include statistics and additional data from the request.
     * <p><b>200</b> - Successful operation.
     * @param generateChatCompletionRequest The generateChatCompletionRequest parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec generateChatCompletionWithResponseSpec(GenerateChatCompletionRequest generateChatCompletionRequest) throws WebClientResponseException {
        return generateChatCompletionRequestCreation(generateChatCompletionRequest);
    }
}
