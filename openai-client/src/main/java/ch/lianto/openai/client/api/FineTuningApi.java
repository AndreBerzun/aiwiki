package ch.lianto.openai.client.api;

import ch.lianto.openai.client.ApiClient;

import ch.lianto.openai.client.model.CreateFineTuningJobRequest;
import ch.lianto.openai.client.model.FineTuningJob;
import ch.lianto.openai.client.model.ListFineTuningJobEventsResponse;
import ch.lianto.openai.client.model.ListPaginatedFineTuningJobsResponse;

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
public class FineTuningApi {
    private ApiClient apiClient;

    public FineTuningApi() {
        this(new ApiClient());
    }

    public FineTuningApi(ApiClient apiClient) {
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
     * @param fineTuningJobId The ID of the fine-tuning job to cancel.  (required)
     * @return FineTuningJob
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FineTuningJob cancelFineTuningJob(String fineTuningJobId) throws RestClientException {
        return cancelFineTuningJobWithHttpInfo(fineTuningJobId).getBody();
    }

    /**
     * Immediately cancel a fine-tune job. 
     * 
     * <p><b>200</b> - OK
     * @param fineTuningJobId The ID of the fine-tuning job to cancel.  (required)
     * @return ResponseEntity&lt;FineTuningJob&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FineTuningJob> cancelFineTuningJobWithHttpInfo(String fineTuningJobId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'fineTuningJobId' is set
        if (fineTuningJobId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'fineTuningJobId' when calling cancelFineTuningJob");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("fine_tuning_job_id", fineTuningJobId);

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

        ParameterizedTypeReference<FineTuningJob> localReturnType = new ParameterizedTypeReference<FineTuningJob>() {};
        return apiClient.invokeAPI("/fine_tuning/jobs/{fine_tuning_job_id}/cancel", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Creates a job that fine-tunes a specified model from a given dataset.  Response includes details of the enqueued job including job status and the name of the fine-tuned models once complete.  [Learn more about fine-tuning](/docs/guides/fine-tuning) 
     * 
     * <p><b>200</b> - OK
     * @param createFineTuningJobRequest  (required)
     * @return FineTuningJob
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FineTuningJob createFineTuningJob(CreateFineTuningJobRequest createFineTuningJobRequest) throws RestClientException {
        return createFineTuningJobWithHttpInfo(createFineTuningJobRequest).getBody();
    }

    /**
     * Creates a job that fine-tunes a specified model from a given dataset.  Response includes details of the enqueued job including job status and the name of the fine-tuned models once complete.  [Learn more about fine-tuning](/docs/guides/fine-tuning) 
     * 
     * <p><b>200</b> - OK
     * @param createFineTuningJobRequest  (required)
     * @return ResponseEntity&lt;FineTuningJob&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FineTuningJob> createFineTuningJobWithHttpInfo(CreateFineTuningJobRequest createFineTuningJobRequest) throws RestClientException {
        Object localVarPostBody = createFineTuningJobRequest;
        
        // verify the required parameter 'createFineTuningJobRequest' is set
        if (createFineTuningJobRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'createFineTuningJobRequest' when calling createFineTuningJob");
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

        ParameterizedTypeReference<FineTuningJob> localReturnType = new ParameterizedTypeReference<FineTuningJob>() {};
        return apiClient.invokeAPI("/fine_tuning/jobs", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get status updates for a fine-tuning job. 
     * 
     * <p><b>200</b> - OK
     * @param fineTuningJobId The ID of the fine-tuning job to get events for.  (required)
     * @param after Identifier for the last event from the previous pagination request. (optional)
     * @param limit Number of events to retrieve. (optional, default to 20)
     * @return ListFineTuningJobEventsResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ListFineTuningJobEventsResponse listFineTuningEvents(String fineTuningJobId, String after, Integer limit) throws RestClientException {
        return listFineTuningEventsWithHttpInfo(fineTuningJobId, after, limit).getBody();
    }

    /**
     * Get status updates for a fine-tuning job. 
     * 
     * <p><b>200</b> - OK
     * @param fineTuningJobId The ID of the fine-tuning job to get events for.  (required)
     * @param after Identifier for the last event from the previous pagination request. (optional)
     * @param limit Number of events to retrieve. (optional, default to 20)
     * @return ResponseEntity&lt;ListFineTuningJobEventsResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ListFineTuningJobEventsResponse> listFineTuningEventsWithHttpInfo(String fineTuningJobId, String after, Integer limit) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'fineTuningJobId' is set
        if (fineTuningJobId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'fineTuningJobId' when calling listFineTuningEvents");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("fine_tuning_job_id", fineTuningJobId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "after", after));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));


        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };

        ParameterizedTypeReference<ListFineTuningJobEventsResponse> localReturnType = new ParameterizedTypeReference<ListFineTuningJobEventsResponse>() {};
        return apiClient.invokeAPI("/fine_tuning/jobs/{fine_tuning_job_id}/events", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * List your organization&#39;s fine-tuning jobs 
     * 
     * <p><b>200</b> - OK
     * @param after Identifier for the last job from the previous pagination request. (optional)
     * @param limit Number of fine-tuning jobs to retrieve. (optional, default to 20)
     * @return ListPaginatedFineTuningJobsResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ListPaginatedFineTuningJobsResponse listPaginatedFineTuningJobs(String after, Integer limit) throws RestClientException {
        return listPaginatedFineTuningJobsWithHttpInfo(after, limit).getBody();
    }

    /**
     * List your organization&#39;s fine-tuning jobs 
     * 
     * <p><b>200</b> - OK
     * @param after Identifier for the last job from the previous pagination request. (optional)
     * @param limit Number of fine-tuning jobs to retrieve. (optional, default to 20)
     * @return ResponseEntity&lt;ListPaginatedFineTuningJobsResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ListPaginatedFineTuningJobsResponse> listPaginatedFineTuningJobsWithHttpInfo(String after, Integer limit) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "after", after));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));


        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };

        ParameterizedTypeReference<ListPaginatedFineTuningJobsResponse> localReturnType = new ParameterizedTypeReference<ListPaginatedFineTuningJobsResponse>() {};
        return apiClient.invokeAPI("/fine_tuning/jobs", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Get info about a fine-tuning job.  [Learn more about fine-tuning](/docs/guides/fine-tuning) 
     * 
     * <p><b>200</b> - OK
     * @param fineTuningJobId The ID of the fine-tuning job.  (required)
     * @return FineTuningJob
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FineTuningJob retrieveFineTuningJob(String fineTuningJobId) throws RestClientException {
        return retrieveFineTuningJobWithHttpInfo(fineTuningJobId).getBody();
    }

    /**
     * Get info about a fine-tuning job.  [Learn more about fine-tuning](/docs/guides/fine-tuning) 
     * 
     * <p><b>200</b> - OK
     * @param fineTuningJobId The ID of the fine-tuning job.  (required)
     * @return ResponseEntity&lt;FineTuningJob&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FineTuningJob> retrieveFineTuningJobWithHttpInfo(String fineTuningJobId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'fineTuningJobId' is set
        if (fineTuningJobId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'fineTuningJobId' when calling retrieveFineTuningJob");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("fine_tuning_job_id", fineTuningJobId);

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

        ParameterizedTypeReference<FineTuningJob> localReturnType = new ParameterizedTypeReference<FineTuningJob>() {};
        return apiClient.invokeAPI("/fine_tuning/jobs/{fine_tuning_job_id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
}
