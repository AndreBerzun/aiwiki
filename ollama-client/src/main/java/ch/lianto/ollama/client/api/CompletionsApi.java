package ch.lianto.ollama.client.api;

import ch.lianto.ollama.client.ApiClient;

import ch.lianto.ollama.client.model.GenerateCompletionRequest;
import ch.lianto.ollama.client.model.GenerateCompletionResponse;

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
public class CompletionsApi {
    private ApiClient apiClient;

    public CompletionsApi() {
        this(new ApiClient());
    }

    @Autowired
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
     * Generate a response for a given prompt with a provided model.
     * The final response object will include statistics and additional data from the request.
     * <p><b>200</b> - Successful operation.
     * @param generateCompletionRequest The generateCompletionRequest parameter
     * @return GenerateCompletionResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec generateCompletionRequestCreation(GenerateCompletionRequest generateCompletionRequest) throws WebClientResponseException {
        Object postBody = generateCompletionRequest;
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

        ParameterizedTypeReference<GenerateCompletionResponse> localVarReturnType = new ParameterizedTypeReference<GenerateCompletionResponse>() {};
        return apiClient.invokeAPI("/generate", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Generate a response for a given prompt with a provided model.
     * The final response object will include statistics and additional data from the request.
     * <p><b>200</b> - Successful operation.
     * @param generateCompletionRequest The generateCompletionRequest parameter
     * @return GenerateCompletionResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<GenerateCompletionResponse> generateCompletion(GenerateCompletionRequest generateCompletionRequest) throws WebClientResponseException {
        ParameterizedTypeReference<GenerateCompletionResponse> localVarReturnType = new ParameterizedTypeReference<GenerateCompletionResponse>() {};
        return generateCompletionRequestCreation(generateCompletionRequest).bodyToMono(localVarReturnType);
    }

    /**
     * Generate a response for a given prompt with a provided model.
     * The final response object will include statistics and additional data from the request.
     * <p><b>200</b> - Successful operation.
     * @param generateCompletionRequest The generateCompletionRequest parameter
     * @return ResponseEntity&lt;GenerateCompletionResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<GenerateCompletionResponse>> generateCompletionWithHttpInfo(GenerateCompletionRequest generateCompletionRequest) throws WebClientResponseException {
        ParameterizedTypeReference<GenerateCompletionResponse> localVarReturnType = new ParameterizedTypeReference<GenerateCompletionResponse>() {};
        return generateCompletionRequestCreation(generateCompletionRequest).toEntity(localVarReturnType);
    }

    /**
     * Generate a response for a given prompt with a provided model.
     * The final response object will include statistics and additional data from the request.
     * <p><b>200</b> - Successful operation.
     * @param generateCompletionRequest The generateCompletionRequest parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec generateCompletionWithResponseSpec(GenerateCompletionRequest generateCompletionRequest) throws WebClientResponseException {
        return generateCompletionRequestCreation(generateCompletionRequest);
    }
}
