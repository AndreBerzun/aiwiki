package ch.lianto.openai.client.api;

import ch.lianto.openai.client.ApiClient;

import ch.lianto.openai.client.model.CreateChatCompletionRequest;
import ch.lianto.openai.client.model.CreateChatCompletionResponse;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-13T00:24:13.926778838+01:00[Europe/Zurich]")
public class ChatApi {
    private ApiClient apiClient;

    public ChatApi() {
        this(new ApiClient());
    }

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
     * @param createChatCompletionRequest  (required)
     * @return CreateChatCompletionResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CreateChatCompletionResponse createChatCompletion(CreateChatCompletionRequest createChatCompletionRequest) throws RestClientException {
        return createChatCompletionWithHttpInfo(createChatCompletionRequest).getBody();
    }

    /**
     * Creates a model response for the given chat conversation.
     * 
     * <p><b>200</b> - OK
     * @param createChatCompletionRequest  (required)
     * @return ResponseEntity&lt;CreateChatCompletionResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CreateChatCompletionResponse> createChatCompletionWithHttpInfo(CreateChatCompletionRequest createChatCompletionRequest) throws RestClientException {
        Object localVarPostBody = createChatCompletionRequest;
        
        // verify the required parameter 'createChatCompletionRequest' is set
        if (createChatCompletionRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'createChatCompletionRequest' when calling createChatCompletion");
        }
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };

        ParameterizedTypeReference<CreateChatCompletionResponse> localReturnType = new ParameterizedTypeReference<CreateChatCompletionResponse>() {};
        return apiClient.invokeAPI("/chat/completions", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
}
