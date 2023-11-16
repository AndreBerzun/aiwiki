package ch.lianto.openai.client.api;

import ch.lianto.openai.client.ApiClient;

import ch.lianto.openai.client.model.CreateEmbeddingRequest;
import ch.lianto.openai.client.model.CreateEmbeddingResponse;

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

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-16T00:40:37.572831696+01:00[Europe/Zurich]")
public class EmbeddingsApi {
    private ApiClient apiClient;

    public EmbeddingsApi() {
        this(new ApiClient());
    }

    public EmbeddingsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Creates an embedding vector representing the input text.
     * 
     * <p><b>200</b> - OK
     * @param createEmbeddingRequest  (required)
     * @return CreateEmbeddingResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CreateEmbeddingResponse createEmbedding(CreateEmbeddingRequest createEmbeddingRequest) throws RestClientException {
        return createEmbeddingWithHttpInfo(createEmbeddingRequest).getBody();
    }

    /**
     * Creates an embedding vector representing the input text.
     * 
     * <p><b>200</b> - OK
     * @param createEmbeddingRequest  (required)
     * @return ResponseEntity&lt;CreateEmbeddingResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CreateEmbeddingResponse> createEmbeddingWithHttpInfo(CreateEmbeddingRequest createEmbeddingRequest) throws RestClientException {
        Object localVarPostBody = createEmbeddingRequest;
        
        // verify the required parameter 'createEmbeddingRequest' is set
        if (createEmbeddingRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'createEmbeddingRequest' when calling createEmbedding");
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

        ParameterizedTypeReference<CreateEmbeddingResponse> localReturnType = new ParameterizedTypeReference<CreateEmbeddingResponse>() {};
        return apiClient.invokeAPI("/embeddings", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
}
