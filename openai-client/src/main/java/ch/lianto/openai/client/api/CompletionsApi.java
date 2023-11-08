package ch.lianto.openai.client.api;

import ch.lianto.openai.client.ApiClient;

import ch.lianto.openai.client.model.CreateCompletionRequest;
import ch.lianto.openai.client.model.CreateCompletionResponse;

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

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-08T23:05:52.228713843+01:00[Europe/Zurich]")
public class CompletionsApi {
    private ApiClient apiClient;

    public CompletionsApi() {
        this(new ApiClient());
    }

    public CompletionsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Creates a completion for the provided prompt and parameters.
     * 
     * <p><b>200</b> - OK
     * @param createCompletionRequest  (required)
     * @return CreateCompletionResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CreateCompletionResponse createCompletion(CreateCompletionRequest createCompletionRequest) throws RestClientException {
        return createCompletionWithHttpInfo(createCompletionRequest).getBody();
    }

    /**
     * Creates a completion for the provided prompt and parameters.
     * 
     * <p><b>200</b> - OK
     * @param createCompletionRequest  (required)
     * @return ResponseEntity&lt;CreateCompletionResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CreateCompletionResponse> createCompletionWithHttpInfo(CreateCompletionRequest createCompletionRequest) throws RestClientException {
        Object localVarPostBody = createCompletionRequest;
        
        // verify the required parameter 'createCompletionRequest' is set
        if (createCompletionRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'createCompletionRequest' when calling createCompletion");
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

        ParameterizedTypeReference<CreateCompletionResponse> localReturnType = new ParameterizedTypeReference<CreateCompletionResponse>() {};
        return apiClient.invokeAPI("/completions", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
}
