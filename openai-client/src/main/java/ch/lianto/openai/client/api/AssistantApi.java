package ch.lianto.openai.client.api;

import ch.lianto.openai.client.ApiClient;

import ch.lianto.openai.client.model.AssistantObject;
import ch.lianto.openai.client.model.ModifyAssistantRequest;

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
public class AssistantApi {
    private ApiClient apiClient;

    public AssistantApi() {
        this(new ApiClient());
    }

    public AssistantApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Modifies an assistant.
     * 
     * <p><b>200</b> - OK
     * @param assistantId The ID of the assistant to modify. (required)
     * @param modifyAssistantRequest  (required)
     * @return AssistantObject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AssistantObject modifyAssistant(String assistantId, ModifyAssistantRequest modifyAssistantRequest) throws RestClientException {
        return modifyAssistantWithHttpInfo(assistantId, modifyAssistantRequest).getBody();
    }

    /**
     * Modifies an assistant.
     * 
     * <p><b>200</b> - OK
     * @param assistantId The ID of the assistant to modify. (required)
     * @param modifyAssistantRequest  (required)
     * @return ResponseEntity&lt;AssistantObject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AssistantObject> modifyAssistantWithHttpInfo(String assistantId, ModifyAssistantRequest modifyAssistantRequest) throws RestClientException {
        Object localVarPostBody = modifyAssistantRequest;
        
        // verify the required parameter 'assistantId' is set
        if (assistantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'assistantId' when calling modifyAssistant");
        }
        
        // verify the required parameter 'modifyAssistantRequest' is set
        if (modifyAssistantRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'modifyAssistantRequest' when calling modifyAssistant");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("assistant_id", assistantId);

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

        ParameterizedTypeReference<AssistantObject> localReturnType = new ParameterizedTypeReference<AssistantObject>() {};
        return apiClient.invokeAPI("/assistants/{assistant_id}", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
}
