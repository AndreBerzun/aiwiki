package ch.lianto.openai.client.api;

import ch.lianto.openai.client.ApiClient;

import ch.lianto.openai.client.model.DeleteModelResponse;
import ch.lianto.openai.client.model.ListModelsResponse;
import ch.lianto.openai.client.model.Model;

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
public class ModelsApi {
    private ApiClient apiClient;

    public ModelsApi() {
        this(new ApiClient());
    }

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
     * Delete a fine-tuned model. You must have the Owner role in your organization to delete a model.
     * 
     * <p><b>200</b> - OK
     * @param model The model to delete (required)
     * @return DeleteModelResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DeleteModelResponse deleteModel(String model) throws RestClientException {
        return deleteModelWithHttpInfo(model).getBody();
    }

    /**
     * Delete a fine-tuned model. You must have the Owner role in your organization to delete a model.
     * 
     * <p><b>200</b> - OK
     * @param model The model to delete (required)
     * @return ResponseEntity&lt;DeleteModelResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DeleteModelResponse> deleteModelWithHttpInfo(String model) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'model' is set
        if (model == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'model' when calling deleteModel");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("model", model);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };

        ParameterizedTypeReference<DeleteModelResponse> localReturnType = new ParameterizedTypeReference<DeleteModelResponse>() {};
        return apiClient.invokeAPI("/models/{model}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Lists the currently available models, and provides basic information about each one such as the owner and availability.
     * 
     * <p><b>200</b> - OK
     * @return ListModelsResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ListModelsResponse listModels() throws RestClientException {
        return listModelsWithHttpInfo().getBody();
    }

    /**
     * Lists the currently available models, and provides basic information about each one such as the owner and availability.
     * 
     * <p><b>200</b> - OK
     * @return ResponseEntity&lt;ListModelsResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ListModelsResponse> listModelsWithHttpInfo() throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };

        ParameterizedTypeReference<ListModelsResponse> localReturnType = new ParameterizedTypeReference<ListModelsResponse>() {};
        return apiClient.invokeAPI("/models", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves a model instance, providing basic information about the model such as the owner and permissioning.
     * 
     * <p><b>200</b> - OK
     * @param model The ID of the model to use for this request (required)
     * @return Model
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Model retrieveModel(String model) throws RestClientException {
        return retrieveModelWithHttpInfo(model).getBody();
    }

    /**
     * Retrieves a model instance, providing basic information about the model such as the owner and permissioning.
     * 
     * <p><b>200</b> - OK
     * @param model The ID of the model to use for this request (required)
     * @return ResponseEntity&lt;Model&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Model> retrieveModelWithHttpInfo(String model) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'model' is set
        if (model == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'model' when calling retrieveModel");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("model", model);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };

        ParameterizedTypeReference<Model> localReturnType = new ParameterizedTypeReference<Model>() {};
        return apiClient.invokeAPI("/models/{model}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
}
