package ch.lianto.openai.client.api;

import ch.lianto.openai.client.ApiClient;

import ch.lianto.openai.client.model.CreateModerationRequest;
import ch.lianto.openai.client.model.CreateModerationResponse;

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
public class ModerationsApi {
    private ApiClient apiClient;

    public ModerationsApi() {
        this(new ApiClient());
    }

    public ModerationsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Classifies if text violates OpenAI&#39;s Content Policy
     * 
     * <p><b>200</b> - OK
     * @param createModerationRequest  (required)
     * @return CreateModerationResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CreateModerationResponse createModeration(CreateModerationRequest createModerationRequest) throws RestClientException {
        return createModerationWithHttpInfo(createModerationRequest).getBody();
    }

    /**
     * Classifies if text violates OpenAI&#39;s Content Policy
     * 
     * <p><b>200</b> - OK
     * @param createModerationRequest  (required)
     * @return ResponseEntity&lt;CreateModerationResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CreateModerationResponse> createModerationWithHttpInfo(CreateModerationRequest createModerationRequest) throws RestClientException {
        Object localVarPostBody = createModerationRequest;
        
        // verify the required parameter 'createModerationRequest' is set
        if (createModerationRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'createModerationRequest' when calling createModeration");
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

        ParameterizedTypeReference<CreateModerationResponse> localReturnType = new ParameterizedTypeReference<CreateModerationResponse>() {};
        return apiClient.invokeAPI("/moderations", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
}
