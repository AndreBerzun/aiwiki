package ch.lianto.ollama.client.api;

import ch.lianto.ollama.client.ApiClient;

import ch.lianto.ollama.client.model.GenerateEmbeddingRequest;
import ch.lianto.ollama.client.model.GenerateEmbeddingResponse;

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
     * Generate embeddings from a model.
     * 
     * <p><b>200</b> - Successful operation.
     * @param generateEmbeddingRequest The generateEmbeddingRequest parameter
     * @return GenerateEmbeddingResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec generateEmbeddingRequestCreation(GenerateEmbeddingRequest generateEmbeddingRequest) throws WebClientResponseException {
        Object postBody = generateEmbeddingRequest;
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

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<GenerateEmbeddingResponse> localVarReturnType = new ParameterizedTypeReference<GenerateEmbeddingResponse>() {};
        return apiClient.invokeAPI("/embeddings", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Generate embeddings from a model.
     * 
     * <p><b>200</b> - Successful operation.
     * @param generateEmbeddingRequest The generateEmbeddingRequest parameter
     * @return GenerateEmbeddingResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<GenerateEmbeddingResponse> generateEmbedding(GenerateEmbeddingRequest generateEmbeddingRequest) throws WebClientResponseException {
        ParameterizedTypeReference<GenerateEmbeddingResponse> localVarReturnType = new ParameterizedTypeReference<GenerateEmbeddingResponse>() {};
        return generateEmbeddingRequestCreation(generateEmbeddingRequest).bodyToMono(localVarReturnType);
    }

    /**
     * Generate embeddings from a model.
     * 
     * <p><b>200</b> - Successful operation.
     * @param generateEmbeddingRequest The generateEmbeddingRequest parameter
     * @return ResponseEntity&lt;GenerateEmbeddingResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<GenerateEmbeddingResponse>> generateEmbeddingWithHttpInfo(GenerateEmbeddingRequest generateEmbeddingRequest) throws WebClientResponseException {
        ParameterizedTypeReference<GenerateEmbeddingResponse> localVarReturnType = new ParameterizedTypeReference<GenerateEmbeddingResponse>() {};
        return generateEmbeddingRequestCreation(generateEmbeddingRequest).toEntity(localVarReturnType);
    }

    /**
     * Generate embeddings from a model.
     * 
     * <p><b>200</b> - Successful operation.
     * @param generateEmbeddingRequest The generateEmbeddingRequest parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec generateEmbeddingWithResponseSpec(GenerateEmbeddingRequest generateEmbeddingRequest) throws WebClientResponseException {
        return generateEmbeddingRequestCreation(generateEmbeddingRequest);
    }
}
