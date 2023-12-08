package ch.lianto.openai.client.api;

import ch.lianto.openai.client.ApiClient;

import ch.lianto.openai.client.model.CreateChatCompletionRequest;
import ch.lianto.openai.client.model.CreateChatCompletionResponse;

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

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-12-08T01:46:18.007763393+01:00[Europe/Zurich]")
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
     * Creates a model response for the given chat conversation.
     * 
     * <p><b>200</b> - OK
     * @param createChatCompletionRequest The createChatCompletionRequest parameter
     * @return CreateChatCompletionResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createChatCompletionRequestCreation(CreateChatCompletionRequest createChatCompletionRequest) throws WebClientResponseException {
        Object postBody = createChatCompletionRequest;
        // verify the required parameter 'createChatCompletionRequest' is set
        if (createChatCompletionRequest == null) {
            throw new WebClientResponseException("Missing the required parameter 'createChatCompletionRequest' when calling createChatCompletion", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
        };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };

        ParameterizedTypeReference<CreateChatCompletionResponse> localVarReturnType = new ParameterizedTypeReference<CreateChatCompletionResponse>() {};
        return apiClient.invokeAPI("/chat/completions", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Creates a model response for the given chat conversation.
     * 
     * <p><b>200</b> - OK
     * @param createChatCompletionRequest The createChatCompletionRequest parameter
     * @return CreateChatCompletionResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<CreateChatCompletionResponse> createChatCompletion(CreateChatCompletionRequest createChatCompletionRequest) throws WebClientResponseException {
        ParameterizedTypeReference<CreateChatCompletionResponse> localVarReturnType = new ParameterizedTypeReference<CreateChatCompletionResponse>() {};
        return createChatCompletionRequestCreation(createChatCompletionRequest).bodyToMono(localVarReturnType);
    }

    /**
     * Creates a model response for the given chat conversation.
     * 
     * <p><b>200</b> - OK
     * @param createChatCompletionRequest The createChatCompletionRequest parameter
     * @return ResponseEntity&lt;CreateChatCompletionResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<CreateChatCompletionResponse>> createChatCompletionWithHttpInfo(CreateChatCompletionRequest createChatCompletionRequest) throws WebClientResponseException {
        ParameterizedTypeReference<CreateChatCompletionResponse> localVarReturnType = new ParameterizedTypeReference<CreateChatCompletionResponse>() {};
        return createChatCompletionRequestCreation(createChatCompletionRequest).toEntity(localVarReturnType);
    }

    /**
     * Creates a model response for the given chat conversation.
     * 
     * <p><b>200</b> - OK
     * @param createChatCompletionRequest The createChatCompletionRequest parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createChatCompletionWithResponseSpec(CreateChatCompletionRequest createChatCompletionRequest) throws WebClientResponseException {
        return createChatCompletionRequestCreation(createChatCompletionRequest);
    }
}
