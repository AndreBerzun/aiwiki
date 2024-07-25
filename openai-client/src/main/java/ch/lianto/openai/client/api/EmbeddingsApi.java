package ch.lianto.openai.client.api;

import ch.lianto.openai.client.ApiClient;

import ch.lianto.openai.client.model.CreateEmbeddingRequest;
import ch.lianto.openai.client.model.CreateEmbeddingResponse;

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

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-05-23T22:31:58.269298200+02:00[Europe/Zurich]", comments = "Generator version: 7.6.0")
public class EmbeddingsApi {
    private ApiClient apiClient;

    public EmbeddingsApi() {
        this(new ApiClient());
    }

    @Autowired
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
     * @param createEmbeddingRequest The createEmbeddingRequest parameter
     * @return CreateEmbeddingResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createEmbeddingRequestCreation(CreateEmbeddingRequest createEmbeddingRequest) throws WebClientResponseException {
        Object postBody = createEmbeddingRequest;
        // verify the required parameter 'createEmbeddingRequest' is set
        if (createEmbeddingRequest == null) {
            throw new WebClientResponseException("Missing the required parameter 'createEmbeddingRequest' when calling createEmbedding", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<CreateEmbeddingResponse> localVarReturnType = new ParameterizedTypeReference<CreateEmbeddingResponse>() {};
        return apiClient.invokeAPI("/embeddings", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Creates an embedding vector representing the input text.
     * 
     * <p><b>200</b> - OK
     * @param createEmbeddingRequest The createEmbeddingRequest parameter
     * @return CreateEmbeddingResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<CreateEmbeddingResponse> createEmbedding(CreateEmbeddingRequest createEmbeddingRequest) throws WebClientResponseException {
        ParameterizedTypeReference<CreateEmbeddingResponse> localVarReturnType = new ParameterizedTypeReference<CreateEmbeddingResponse>() {};
        return createEmbeddingRequestCreation(createEmbeddingRequest).bodyToMono(localVarReturnType);
    }

    /**
     * Creates an embedding vector representing the input text.
     * 
     * <p><b>200</b> - OK
     * @param createEmbeddingRequest The createEmbeddingRequest parameter
     * @return ResponseEntity&lt;CreateEmbeddingResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<CreateEmbeddingResponse>> createEmbeddingWithHttpInfo(CreateEmbeddingRequest createEmbeddingRequest) throws WebClientResponseException {
        ParameterizedTypeReference<CreateEmbeddingResponse> localVarReturnType = new ParameterizedTypeReference<CreateEmbeddingResponse>() {};
        return createEmbeddingRequestCreation(createEmbeddingRequest).toEntity(localVarReturnType);
    }

    /**
     * Creates an embedding vector representing the input text.
     * 
     * <p><b>200</b> - OK
     * @param createEmbeddingRequest The createEmbeddingRequest parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createEmbeddingWithResponseSpec(CreateEmbeddingRequest createEmbeddingRequest) throws WebClientResponseException {
        return createEmbeddingRequestCreation(createEmbeddingRequest);
    }
}
