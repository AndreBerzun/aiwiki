package ch.lianto.ollama.client.api;

import ch.lianto.ollama.client.ApiClient;

import ch.lianto.ollama.client.model.CopyModelRequest;
import ch.lianto.ollama.client.model.CreateModelRequest;
import ch.lianto.ollama.client.model.CreateModelResponse;
import ch.lianto.ollama.client.model.DeleteModelRequest;
import java.io.File;
import ch.lianto.ollama.client.model.ModelInfo;
import ch.lianto.ollama.client.model.ModelInfoRequest;
import ch.lianto.ollama.client.model.ModelsResponse;
import ch.lianto.ollama.client.model.PullModelRequest;
import ch.lianto.ollama.client.model.PullModelResponse;
import ch.lianto.ollama.client.model.PushModelRequest;
import ch.lianto.ollama.client.model.PushModelResponse;

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
public class ModelsApi {
    private ApiClient apiClient;

    public ModelsApi() {
        this(new ApiClient());
    }

    @Autowired
    public ModelsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Check to see if a blob exists on the Ollama server which is useful when creating models.
     * 
     * <p><b>200</b> - Blob exists on the server
     * <p><b>404</b> - Blob was not found
     * @param name the SHA256 digest of the blob
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec checkBlobRequestCreation(String name) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new WebClientResponseException("Missing the required parameter 'name' when calling checkBlob", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        
        final String[] localVarAccepts = { };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/blobs/{digest}", HttpMethod.HEAD, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Check to see if a blob exists on the Ollama server which is useful when creating models.
     * 
     * <p><b>200</b> - Blob exists on the server
     * <p><b>404</b> - Blob was not found
     * @param name the SHA256 digest of the blob
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> checkBlob(String name) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return checkBlobRequestCreation(name).bodyToMono(localVarReturnType);
    }

    /**
     * Check to see if a blob exists on the Ollama server which is useful when creating models.
     * 
     * <p><b>200</b> - Blob exists on the server
     * <p><b>404</b> - Blob was not found
     * @param name the SHA256 digest of the blob
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> checkBlobWithHttpInfo(String name) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return checkBlobRequestCreation(name).toEntity(localVarReturnType);
    }

    /**
     * Check to see if a blob exists on the Ollama server which is useful when creating models.
     * 
     * <p><b>200</b> - Blob exists on the server
     * <p><b>404</b> - Blob was not found
     * @param name the SHA256 digest of the blob
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec checkBlobWithResponseSpec(String name) throws WebClientResponseException {
        return checkBlobRequestCreation(name);
    }
    /**
     * Creates a model with another name from an existing model.
     * 
     * <p><b>200</b> - Successful operation.
     * @param copyModelRequest The copyModelRequest parameter
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec copyModelRequestCreation(CopyModelRequest copyModelRequest) throws WebClientResponseException {
        Object postBody = copyModelRequest;
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
        };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/copy", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Creates a model with another name from an existing model.
     * 
     * <p><b>200</b> - Successful operation.
     * @param copyModelRequest The copyModelRequest parameter
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> copyModel(CopyModelRequest copyModelRequest) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return copyModelRequestCreation(copyModelRequest).bodyToMono(localVarReturnType);
    }

    /**
     * Creates a model with another name from an existing model.
     * 
     * <p><b>200</b> - Successful operation.
     * @param copyModelRequest The copyModelRequest parameter
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> copyModelWithHttpInfo(CopyModelRequest copyModelRequest) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return copyModelRequestCreation(copyModelRequest).toEntity(localVarReturnType);
    }

    /**
     * Creates a model with another name from an existing model.
     * 
     * <p><b>200</b> - Successful operation.
     * @param copyModelRequest The copyModelRequest parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec copyModelWithResponseSpec(CopyModelRequest copyModelRequest) throws WebClientResponseException {
        return copyModelRequestCreation(copyModelRequest);
    }
    /**
     * Create a blob from a file. Returns the server file path.
     * 
     * <p><b>201</b> - Blob was successfully created
     * @param name the SHA256 digest of the blob
     * @param body The body parameter
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createBlobRequestCreation(String name, File body) throws WebClientResponseException {
        Object postBody = body;
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new WebClientResponseException("Missing the required parameter 'name' when calling createBlob", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        
        final String[] localVarAccepts = { };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/octet-stream"
        };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/blobs/{digest}", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create a blob from a file. Returns the server file path.
     * 
     * <p><b>201</b> - Blob was successfully created
     * @param name the SHA256 digest of the blob
     * @param body The body parameter
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> createBlob(String name, File body) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return createBlobRequestCreation(name, body).bodyToMono(localVarReturnType);
    }

    /**
     * Create a blob from a file. Returns the server file path.
     * 
     * <p><b>201</b> - Blob was successfully created
     * @param name the SHA256 digest of the blob
     * @param body The body parameter
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> createBlobWithHttpInfo(String name, File body) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return createBlobRequestCreation(name, body).toEntity(localVarReturnType);
    }

    /**
     * Create a blob from a file. Returns the server file path.
     * 
     * <p><b>201</b> - Blob was successfully created
     * @param name the SHA256 digest of the blob
     * @param body The body parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createBlobWithResponseSpec(String name, File body) throws WebClientResponseException {
        return createBlobRequestCreation(name, body);
    }
    /**
     * Create a model from a Modelfile.
     * It is recommended to set &#x60;modelfile&#x60; to the content of the Modelfile rather than just set &#x60;path&#x60;. This is a requirement for remote create. Remote model creation should also create any file blobs, fields such as &#x60;FROM&#x60; and &#x60;ADAPTER&#x60;, explicitly with the server using Create a Blob and the value to the path indicated in the response.
     * <p><b>200</b> - Successful operation.
     * @param createModelRequest Create a new model from a Modelfile.
     * @return CreateModelResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createModelRequestCreation(CreateModelRequest createModelRequest) throws WebClientResponseException {
        Object postBody = createModelRequest;
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

        ParameterizedTypeReference<CreateModelResponse> localVarReturnType = new ParameterizedTypeReference<CreateModelResponse>() {};
        return apiClient.invokeAPI("/create", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create a model from a Modelfile.
     * It is recommended to set &#x60;modelfile&#x60; to the content of the Modelfile rather than just set &#x60;path&#x60;. This is a requirement for remote create. Remote model creation should also create any file blobs, fields such as &#x60;FROM&#x60; and &#x60;ADAPTER&#x60;, explicitly with the server using Create a Blob and the value to the path indicated in the response.
     * <p><b>200</b> - Successful operation.
     * @param createModelRequest Create a new model from a Modelfile.
     * @return CreateModelResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<CreateModelResponse> createModel(CreateModelRequest createModelRequest) throws WebClientResponseException {
        ParameterizedTypeReference<CreateModelResponse> localVarReturnType = new ParameterizedTypeReference<CreateModelResponse>() {};
        return createModelRequestCreation(createModelRequest).bodyToMono(localVarReturnType);
    }

    /**
     * Create a model from a Modelfile.
     * It is recommended to set &#x60;modelfile&#x60; to the content of the Modelfile rather than just set &#x60;path&#x60;. This is a requirement for remote create. Remote model creation should also create any file blobs, fields such as &#x60;FROM&#x60; and &#x60;ADAPTER&#x60;, explicitly with the server using Create a Blob and the value to the path indicated in the response.
     * <p><b>200</b> - Successful operation.
     * @param createModelRequest Create a new model from a Modelfile.
     * @return ResponseEntity&lt;CreateModelResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<CreateModelResponse>> createModelWithHttpInfo(CreateModelRequest createModelRequest) throws WebClientResponseException {
        ParameterizedTypeReference<CreateModelResponse> localVarReturnType = new ParameterizedTypeReference<CreateModelResponse>() {};
        return createModelRequestCreation(createModelRequest).toEntity(localVarReturnType);
    }

    /**
     * Create a model from a Modelfile.
     * It is recommended to set &#x60;modelfile&#x60; to the content of the Modelfile rather than just set &#x60;path&#x60;. This is a requirement for remote create. Remote model creation should also create any file blobs, fields such as &#x60;FROM&#x60; and &#x60;ADAPTER&#x60;, explicitly with the server using Create a Blob and the value to the path indicated in the response.
     * <p><b>200</b> - Successful operation.
     * @param createModelRequest Create a new model from a Modelfile.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createModelWithResponseSpec(CreateModelRequest createModelRequest) throws WebClientResponseException {
        return createModelRequestCreation(createModelRequest);
    }
    /**
     * Delete a model and its data.
     * 
     * <p><b>200</b> - Successful operation.
     * @param deleteModelRequest The deleteModelRequest parameter
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteModelRequestCreation(DeleteModelRequest deleteModelRequest) throws WebClientResponseException {
        Object postBody = deleteModelRequest;
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
        };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/delete", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete a model and its data.
     * 
     * <p><b>200</b> - Successful operation.
     * @param deleteModelRequest The deleteModelRequest parameter
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteModel(DeleteModelRequest deleteModelRequest) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteModelRequestCreation(deleteModelRequest).bodyToMono(localVarReturnType);
    }

    /**
     * Delete a model and its data.
     * 
     * <p><b>200</b> - Successful operation.
     * @param deleteModelRequest The deleteModelRequest parameter
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteModelWithHttpInfo(DeleteModelRequest deleteModelRequest) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteModelRequestCreation(deleteModelRequest).toEntity(localVarReturnType);
    }

    /**
     * Delete a model and its data.
     * 
     * <p><b>200</b> - Successful operation.
     * @param deleteModelRequest The deleteModelRequest parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteModelWithResponseSpec(DeleteModelRequest deleteModelRequest) throws WebClientResponseException {
        return deleteModelRequestCreation(deleteModelRequest);
    }
    /**
     * List models that are available locally.
     * 
     * <p><b>200</b> - Successful operation.
     * @return ModelsResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listModelsRequestCreation() throws WebClientResponseException {
        Object postBody = null;
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
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<ModelsResponse> localVarReturnType = new ParameterizedTypeReference<ModelsResponse>() {};
        return apiClient.invokeAPI("/tags", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List models that are available locally.
     * 
     * <p><b>200</b> - Successful operation.
     * @return ModelsResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ModelsResponse> listModels() throws WebClientResponseException {
        ParameterizedTypeReference<ModelsResponse> localVarReturnType = new ParameterizedTypeReference<ModelsResponse>() {};
        return listModelsRequestCreation().bodyToMono(localVarReturnType);
    }

    /**
     * List models that are available locally.
     * 
     * <p><b>200</b> - Successful operation.
     * @return ResponseEntity&lt;ModelsResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<ModelsResponse>> listModelsWithHttpInfo() throws WebClientResponseException {
        ParameterizedTypeReference<ModelsResponse> localVarReturnType = new ParameterizedTypeReference<ModelsResponse>() {};
        return listModelsRequestCreation().toEntity(localVarReturnType);
    }

    /**
     * List models that are available locally.
     * 
     * <p><b>200</b> - Successful operation.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listModelsWithResponseSpec() throws WebClientResponseException {
        return listModelsRequestCreation();
    }
    /**
     * Download a model from the ollama library.
     * Cancelled pulls are resumed from where they left off, and multiple calls will share the same download progress.
     * <p><b>200</b> - Successful operation.
     * @param pullModelRequest The pullModelRequest parameter
     * @return PullModelResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec pullModelRequestCreation(PullModelRequest pullModelRequest) throws WebClientResponseException {
        Object postBody = pullModelRequest;
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

        ParameterizedTypeReference<PullModelResponse> localVarReturnType = new ParameterizedTypeReference<PullModelResponse>() {};
        return apiClient.invokeAPI("/pull", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Download a model from the ollama library.
     * Cancelled pulls are resumed from where they left off, and multiple calls will share the same download progress.
     * <p><b>200</b> - Successful operation.
     * @param pullModelRequest The pullModelRequest parameter
     * @return PullModelResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PullModelResponse> pullModel(PullModelRequest pullModelRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PullModelResponse> localVarReturnType = new ParameterizedTypeReference<PullModelResponse>() {};
        return pullModelRequestCreation(pullModelRequest).bodyToMono(localVarReturnType);
    }

    /**
     * Download a model from the ollama library.
     * Cancelled pulls are resumed from where they left off, and multiple calls will share the same download progress.
     * <p><b>200</b> - Successful operation.
     * @param pullModelRequest The pullModelRequest parameter
     * @return ResponseEntity&lt;PullModelResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PullModelResponse>> pullModelWithHttpInfo(PullModelRequest pullModelRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PullModelResponse> localVarReturnType = new ParameterizedTypeReference<PullModelResponse>() {};
        return pullModelRequestCreation(pullModelRequest).toEntity(localVarReturnType);
    }

    /**
     * Download a model from the ollama library.
     * Cancelled pulls are resumed from where they left off, and multiple calls will share the same download progress.
     * <p><b>200</b> - Successful operation.
     * @param pullModelRequest The pullModelRequest parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec pullModelWithResponseSpec(PullModelRequest pullModelRequest) throws WebClientResponseException {
        return pullModelRequestCreation(pullModelRequest);
    }
    /**
     * Upload a model to a model library.
     * Requires registering for ollama.ai and adding a public key first.
     * <p><b>200</b> - Successful operation.
     * @param pushModelRequest The pushModelRequest parameter
     * @return PushModelResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec pushModelRequestCreation(PushModelRequest pushModelRequest) throws WebClientResponseException {
        Object postBody = pushModelRequest;
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

        ParameterizedTypeReference<PushModelResponse> localVarReturnType = new ParameterizedTypeReference<PushModelResponse>() {};
        return apiClient.invokeAPI("/push", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Upload a model to a model library.
     * Requires registering for ollama.ai and adding a public key first.
     * <p><b>200</b> - Successful operation.
     * @param pushModelRequest The pushModelRequest parameter
     * @return PushModelResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PushModelResponse> pushModel(PushModelRequest pushModelRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PushModelResponse> localVarReturnType = new ParameterizedTypeReference<PushModelResponse>() {};
        return pushModelRequestCreation(pushModelRequest).bodyToMono(localVarReturnType);
    }

    /**
     * Upload a model to a model library.
     * Requires registering for ollama.ai and adding a public key first.
     * <p><b>200</b> - Successful operation.
     * @param pushModelRequest The pushModelRequest parameter
     * @return ResponseEntity&lt;PushModelResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PushModelResponse>> pushModelWithHttpInfo(PushModelRequest pushModelRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PushModelResponse> localVarReturnType = new ParameterizedTypeReference<PushModelResponse>() {};
        return pushModelRequestCreation(pushModelRequest).toEntity(localVarReturnType);
    }

    /**
     * Upload a model to a model library.
     * Requires registering for ollama.ai and adding a public key first.
     * <p><b>200</b> - Successful operation.
     * @param pushModelRequest The pushModelRequest parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec pushModelWithResponseSpec(PushModelRequest pushModelRequest) throws WebClientResponseException {
        return pushModelRequestCreation(pushModelRequest);
    }
    /**
     * Show details about a model including modelfile, template, parameters, license, and system prompt.
     * 
     * <p><b>200</b> - Successful operation.
     * @param modelInfoRequest The modelInfoRequest parameter
     * @return ModelInfo
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec showModelInfoRequestCreation(ModelInfoRequest modelInfoRequest) throws WebClientResponseException {
        Object postBody = modelInfoRequest;
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

        ParameterizedTypeReference<ModelInfo> localVarReturnType = new ParameterizedTypeReference<ModelInfo>() {};
        return apiClient.invokeAPI("/show", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Show details about a model including modelfile, template, parameters, license, and system prompt.
     * 
     * <p><b>200</b> - Successful operation.
     * @param modelInfoRequest The modelInfoRequest parameter
     * @return ModelInfo
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ModelInfo> showModelInfo(ModelInfoRequest modelInfoRequest) throws WebClientResponseException {
        ParameterizedTypeReference<ModelInfo> localVarReturnType = new ParameterizedTypeReference<ModelInfo>() {};
        return showModelInfoRequestCreation(modelInfoRequest).bodyToMono(localVarReturnType);
    }

    /**
     * Show details about a model including modelfile, template, parameters, license, and system prompt.
     * 
     * <p><b>200</b> - Successful operation.
     * @param modelInfoRequest The modelInfoRequest parameter
     * @return ResponseEntity&lt;ModelInfo&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<ModelInfo>> showModelInfoWithHttpInfo(ModelInfoRequest modelInfoRequest) throws WebClientResponseException {
        ParameterizedTypeReference<ModelInfo> localVarReturnType = new ParameterizedTypeReference<ModelInfo>() {};
        return showModelInfoRequestCreation(modelInfoRequest).toEntity(localVarReturnType);
    }

    /**
     * Show details about a model including modelfile, template, parameters, license, and system prompt.
     * 
     * <p><b>200</b> - Successful operation.
     * @param modelInfoRequest The modelInfoRequest parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec showModelInfoWithResponseSpec(ModelInfoRequest modelInfoRequest) throws WebClientResponseException {
        return showModelInfoRequestCreation(modelInfoRequest);
    }
}
