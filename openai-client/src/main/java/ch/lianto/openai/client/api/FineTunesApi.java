package ch.lianto.openai.client.api;

import ch.lianto.openai.client.ApiClient;

import ch.lianto.openai.client.model.CreateFineTuneRequest;
import ch.lianto.openai.client.model.FineTune;
import ch.lianto.openai.client.model.ListFineTuneEventsResponse;
import ch.lianto.openai.client.model.ListFineTunesResponse;

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
public class FineTunesApi {
    private ApiClient apiClient;

    public FineTunesApi() {
        this(new ApiClient());
    }

    public FineTunesApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Immediately cancel a fine-tune job. 
     * 
     * <p><b>200</b> - OK
     * @param fineTuneId The ID of the fine-tune job to cancel  (required)
     * @return FineTune
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public FineTune cancelFineTune(String fineTuneId) throws RestClientException {
        return cancelFineTuneWithHttpInfo(fineTuneId).getBody();
    }

    /**
     * Immediately cancel a fine-tune job. 
     * 
     * <p><b>200</b> - OK
     * @param fineTuneId The ID of the fine-tune job to cancel  (required)
     * @return ResponseEntity&lt;FineTune&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<FineTune> cancelFineTuneWithHttpInfo(String fineTuneId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'fineTuneId' is set
        if (fineTuneId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'fineTuneId' when calling cancelFineTune");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("fine_tune_id", fineTuneId);

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

        ParameterizedTypeReference<FineTune> localReturnType = new ParameterizedTypeReference<FineTune>() {};
        return apiClient.invokeAPI("/fine-tunes/{fine_tune_id}/cancel", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Creates a job that fine-tunes a specified model from a given dataset.  Response includes details of the enqueued job including job status and the name of the fine-tuned models once complete.  [Learn more about fine-tuning](/docs/guides/legacy-fine-tuning) 
     * 
     * <p><b>200</b> - OK
     * @param createFineTuneRequest  (required)
     * @return FineTune
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public FineTune createFineTune(CreateFineTuneRequest createFineTuneRequest) throws RestClientException {
        return createFineTuneWithHttpInfo(createFineTuneRequest).getBody();
    }

    /**
     * Creates a job that fine-tunes a specified model from a given dataset.  Response includes details of the enqueued job including job status and the name of the fine-tuned models once complete.  [Learn more about fine-tuning](/docs/guides/legacy-fine-tuning) 
     * 
     * <p><b>200</b> - OK
     * @param createFineTuneRequest  (required)
     * @return ResponseEntity&lt;FineTune&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<FineTune> createFineTuneWithHttpInfo(CreateFineTuneRequest createFineTuneRequest) throws RestClientException {
        Object localVarPostBody = createFineTuneRequest;
        
        // verify the required parameter 'createFineTuneRequest' is set
        if (createFineTuneRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'createFineTuneRequest' when calling createFineTune");
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

        ParameterizedTypeReference<FineTune> localReturnType = new ParameterizedTypeReference<FineTune>() {};
        return apiClient.invokeAPI("/fine-tunes", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get fine-grained status updates for a fine-tune job. 
     * 
     * <p><b>200</b> - OK
     * @param fineTuneId The ID of the fine-tune job to get events for.  (required)
     * @param stream Whether to stream events for the fine-tune job. If set to true, events will be sent as data-only [server-sent events](https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events/Using_server-sent_events#Event_stream_format) as they become available. The stream will terminate with a &#x60;data: [DONE]&#x60; message when the job is finished (succeeded, cancelled, or failed).  If set to false, only events generated so far will be returned.  (optional, default to false)
     * @return ListFineTuneEventsResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ListFineTuneEventsResponse listFineTuneEvents(String fineTuneId, Boolean stream) throws RestClientException {
        return listFineTuneEventsWithHttpInfo(fineTuneId, stream).getBody();
    }

    /**
     * Get fine-grained status updates for a fine-tune job. 
     * 
     * <p><b>200</b> - OK
     * @param fineTuneId The ID of the fine-tune job to get events for.  (required)
     * @param stream Whether to stream events for the fine-tune job. If set to true, events will be sent as data-only [server-sent events](https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events/Using_server-sent_events#Event_stream_format) as they become available. The stream will terminate with a &#x60;data: [DONE]&#x60; message when the job is finished (succeeded, cancelled, or failed).  If set to false, only events generated so far will be returned.  (optional, default to false)
     * @return ResponseEntity&lt;ListFineTuneEventsResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<ListFineTuneEventsResponse> listFineTuneEventsWithHttpInfo(String fineTuneId, Boolean stream) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'fineTuneId' is set
        if (fineTuneId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'fineTuneId' when calling listFineTuneEvents");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("fine_tune_id", fineTuneId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "stream", stream));


        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };

        ParameterizedTypeReference<ListFineTuneEventsResponse> localReturnType = new ParameterizedTypeReference<ListFineTuneEventsResponse>() {};
        return apiClient.invokeAPI("/fine-tunes/{fine_tune_id}/events", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * List your organization&#39;s fine-tuning jobs 
     * 
     * <p><b>200</b> - OK
     * @return ListFineTunesResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ListFineTunesResponse listFineTunes() throws RestClientException {
        return listFineTunesWithHttpInfo().getBody();
    }

    /**
     * List your organization&#39;s fine-tuning jobs 
     * 
     * <p><b>200</b> - OK
     * @return ResponseEntity&lt;ListFineTunesResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<ListFineTunesResponse> listFineTunesWithHttpInfo() throws RestClientException {
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

        ParameterizedTypeReference<ListFineTunesResponse> localReturnType = new ParameterizedTypeReference<ListFineTunesResponse>() {};
        return apiClient.invokeAPI("/fine-tunes", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Gets info about the fine-tune job.  [Learn more about fine-tuning](/docs/guides/legacy-fine-tuning) 
     * 
     * <p><b>200</b> - OK
     * @param fineTuneId The ID of the fine-tune job  (required)
     * @return FineTune
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public FineTune retrieveFineTune(String fineTuneId) throws RestClientException {
        return retrieveFineTuneWithHttpInfo(fineTuneId).getBody();
    }

    /**
     * Gets info about the fine-tune job.  [Learn more about fine-tuning](/docs/guides/legacy-fine-tuning) 
     * 
     * <p><b>200</b> - OK
     * @param fineTuneId The ID of the fine-tune job  (required)
     * @return ResponseEntity&lt;FineTune&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     * @deprecated
     */
    @Deprecated
    public ResponseEntity<FineTune> retrieveFineTuneWithHttpInfo(String fineTuneId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'fineTuneId' is set
        if (fineTuneId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'fineTuneId' when calling retrieveFineTune");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("fine_tune_id", fineTuneId);

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

        ParameterizedTypeReference<FineTune> localReturnType = new ParameterizedTypeReference<FineTune>() {};
        return apiClient.invokeAPI("/fine-tunes/{fine_tune_id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
}
