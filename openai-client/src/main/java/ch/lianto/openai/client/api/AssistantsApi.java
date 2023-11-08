package ch.lianto.openai.client.api;

import ch.lianto.openai.client.ApiClient;

import ch.lianto.openai.client.model.AssistantFileObject;
import ch.lianto.openai.client.model.AssistantObject;
import ch.lianto.openai.client.model.CreateAssistantFileRequest;
import ch.lianto.openai.client.model.CreateAssistantRequest;
import ch.lianto.openai.client.model.CreateMessageRequest;
import ch.lianto.openai.client.model.CreateRunRequest;
import ch.lianto.openai.client.model.CreateThreadAndRunRequest;
import ch.lianto.openai.client.model.CreateThreadRequest;
import ch.lianto.openai.client.model.DeleteAssistantFileResponse;
import ch.lianto.openai.client.model.DeleteAssistantResponse;
import ch.lianto.openai.client.model.DeleteThreadResponse;
import ch.lianto.openai.client.model.ListAssistantFilesResponse;
import ch.lianto.openai.client.model.ListAssistantsResponse;
import ch.lianto.openai.client.model.ListMessageFilesResponse;
import ch.lianto.openai.client.model.ListMessagesResponse;
import ch.lianto.openai.client.model.ListRunStepsResponse;
import ch.lianto.openai.client.model.ListRunsResponse;
import ch.lianto.openai.client.model.MessageFileObject;
import ch.lianto.openai.client.model.MessageObject;
import ch.lianto.openai.client.model.ModifyMessageRequest;
import ch.lianto.openai.client.model.ModifyRunRequest;
import ch.lianto.openai.client.model.ModifyThreadRequest;
import ch.lianto.openai.client.model.RunObject;
import ch.lianto.openai.client.model.RunStepObject;
import ch.lianto.openai.client.model.SubmitToolOutputsRunRequest;
import ch.lianto.openai.client.model.ThreadObject;

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
public class AssistantsApi {
    private ApiClient apiClient;

    public AssistantsApi() {
        this(new ApiClient());
    }

    public AssistantsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Cancels a run that is &#x60;in_progress&#x60;.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the thread to which this run belongs. (required)
     * @param runId The ID of the run to cancel. (required)
     * @return RunObject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RunObject cancelRun(String threadId, String runId) throws RestClientException {
        return cancelRunWithHttpInfo(threadId, runId).getBody();
    }

    /**
     * Cancels a run that is &#x60;in_progress&#x60;.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the thread to which this run belongs. (required)
     * @param runId The ID of the run to cancel. (required)
     * @return ResponseEntity&lt;RunObject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RunObject> cancelRunWithHttpInfo(String threadId, String runId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'threadId' is set
        if (threadId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'threadId' when calling cancelRun");
        }
        
        // verify the required parameter 'runId' is set
        if (runId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'runId' when calling cancelRun");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("thread_id", threadId);
        uriVariables.put("run_id", runId);

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

        ParameterizedTypeReference<RunObject> localReturnType = new ParameterizedTypeReference<RunObject>() {};
        return apiClient.invokeAPI("/threads/{thread_id}/runs/{run_id}/cancel", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create an assistant with a model and instructions.
     * 
     * <p><b>200</b> - OK
     * @param createAssistantRequest  (required)
     * @return AssistantObject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AssistantObject createAssistant(CreateAssistantRequest createAssistantRequest) throws RestClientException {
        return createAssistantWithHttpInfo(createAssistantRequest).getBody();
    }

    /**
     * Create an assistant with a model and instructions.
     * 
     * <p><b>200</b> - OK
     * @param createAssistantRequest  (required)
     * @return ResponseEntity&lt;AssistantObject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AssistantObject> createAssistantWithHttpInfo(CreateAssistantRequest createAssistantRequest) throws RestClientException {
        Object localVarPostBody = createAssistantRequest;
        
        // verify the required parameter 'createAssistantRequest' is set
        if (createAssistantRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'createAssistantRequest' when calling createAssistant");
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

        ParameterizedTypeReference<AssistantObject> localReturnType = new ParameterizedTypeReference<AssistantObject>() {};
        return apiClient.invokeAPI("/assistants", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create an assistant file by attaching a [File](/docs/api-reference/files) to an [assistant](/docs/api-reference/assistants).
     * 
     * <p><b>200</b> - OK
     * @param assistantId The ID of the assistant for which to create a File.  (required)
     * @param createAssistantFileRequest  (required)
     * @return AssistantFileObject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AssistantFileObject createAssistantFile(String assistantId, CreateAssistantFileRequest createAssistantFileRequest) throws RestClientException {
        return createAssistantFileWithHttpInfo(assistantId, createAssistantFileRequest).getBody();
    }

    /**
     * Create an assistant file by attaching a [File](/docs/api-reference/files) to an [assistant](/docs/api-reference/assistants).
     * 
     * <p><b>200</b> - OK
     * @param assistantId The ID of the assistant for which to create a File.  (required)
     * @param createAssistantFileRequest  (required)
     * @return ResponseEntity&lt;AssistantFileObject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AssistantFileObject> createAssistantFileWithHttpInfo(String assistantId, CreateAssistantFileRequest createAssistantFileRequest) throws RestClientException {
        Object localVarPostBody = createAssistantFileRequest;
        
        // verify the required parameter 'assistantId' is set
        if (assistantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'assistantId' when calling createAssistantFile");
        }
        
        // verify the required parameter 'createAssistantFileRequest' is set
        if (createAssistantFileRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'createAssistantFileRequest' when calling createAssistantFile");
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

        ParameterizedTypeReference<AssistantFileObject> localReturnType = new ParameterizedTypeReference<AssistantFileObject>() {};
        return apiClient.invokeAPI("/assistants/{assistant_id}/files", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create a message.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the [thread](/docs/api-reference/threads) to create a message for. (required)
     * @param createMessageRequest  (required)
     * @return MessageObject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public MessageObject createMessage(String threadId, CreateMessageRequest createMessageRequest) throws RestClientException {
        return createMessageWithHttpInfo(threadId, createMessageRequest).getBody();
    }

    /**
     * Create a message.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the [thread](/docs/api-reference/threads) to create a message for. (required)
     * @param createMessageRequest  (required)
     * @return ResponseEntity&lt;MessageObject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<MessageObject> createMessageWithHttpInfo(String threadId, CreateMessageRequest createMessageRequest) throws RestClientException {
        Object localVarPostBody = createMessageRequest;
        
        // verify the required parameter 'threadId' is set
        if (threadId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'threadId' when calling createMessage");
        }
        
        // verify the required parameter 'createMessageRequest' is set
        if (createMessageRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'createMessageRequest' when calling createMessage");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("thread_id", threadId);

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

        ParameterizedTypeReference<MessageObject> localReturnType = new ParameterizedTypeReference<MessageObject>() {};
        return apiClient.invokeAPI("/threads/{thread_id}/messages", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create a run.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the thread to run. (required)
     * @param createRunRequest  (required)
     * @return RunObject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RunObject createRun(String threadId, CreateRunRequest createRunRequest) throws RestClientException {
        return createRunWithHttpInfo(threadId, createRunRequest).getBody();
    }

    /**
     * Create a run.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the thread to run. (required)
     * @param createRunRequest  (required)
     * @return ResponseEntity&lt;RunObject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RunObject> createRunWithHttpInfo(String threadId, CreateRunRequest createRunRequest) throws RestClientException {
        Object localVarPostBody = createRunRequest;
        
        // verify the required parameter 'threadId' is set
        if (threadId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'threadId' when calling createRun");
        }
        
        // verify the required parameter 'createRunRequest' is set
        if (createRunRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'createRunRequest' when calling createRun");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("thread_id", threadId);

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

        ParameterizedTypeReference<RunObject> localReturnType = new ParameterizedTypeReference<RunObject>() {};
        return apiClient.invokeAPI("/threads/{thread_id}/runs", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create a thread.
     * 
     * <p><b>200</b> - OK
     * @param createThreadRequest  (required)
     * @return ThreadObject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ThreadObject createThread(CreateThreadRequest createThreadRequest) throws RestClientException {
        return createThreadWithHttpInfo(createThreadRequest).getBody();
    }

    /**
     * Create a thread.
     * 
     * <p><b>200</b> - OK
     * @param createThreadRequest  (required)
     * @return ResponseEntity&lt;ThreadObject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ThreadObject> createThreadWithHttpInfo(CreateThreadRequest createThreadRequest) throws RestClientException {
        Object localVarPostBody = createThreadRequest;
        
        // verify the required parameter 'createThreadRequest' is set
        if (createThreadRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'createThreadRequest' when calling createThread");
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

        ParameterizedTypeReference<ThreadObject> localReturnType = new ParameterizedTypeReference<ThreadObject>() {};
        return apiClient.invokeAPI("/threads", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Create a thread and run it in one request.
     * 
     * <p><b>200</b> - OK
     * @param createThreadAndRunRequest  (required)
     * @return RunObject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RunObject createThreadAndRun(CreateThreadAndRunRequest createThreadAndRunRequest) throws RestClientException {
        return createThreadAndRunWithHttpInfo(createThreadAndRunRequest).getBody();
    }

    /**
     * Create a thread and run it in one request.
     * 
     * <p><b>200</b> - OK
     * @param createThreadAndRunRequest  (required)
     * @return ResponseEntity&lt;RunObject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RunObject> createThreadAndRunWithHttpInfo(CreateThreadAndRunRequest createThreadAndRunRequest) throws RestClientException {
        Object localVarPostBody = createThreadAndRunRequest;
        
        // verify the required parameter 'createThreadAndRunRequest' is set
        if (createThreadAndRunRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'createThreadAndRunRequest' when calling createThreadAndRun");
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

        ParameterizedTypeReference<RunObject> localReturnType = new ParameterizedTypeReference<RunObject>() {};
        return apiClient.invokeAPI("/threads/runs", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete an assistant.
     * 
     * <p><b>200</b> - OK
     * @param assistantId The ID of the assistant to delete. (required)
     * @return DeleteAssistantResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DeleteAssistantResponse deleteAssistant(String assistantId) throws RestClientException {
        return deleteAssistantWithHttpInfo(assistantId).getBody();
    }

    /**
     * Delete an assistant.
     * 
     * <p><b>200</b> - OK
     * @param assistantId The ID of the assistant to delete. (required)
     * @return ResponseEntity&lt;DeleteAssistantResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DeleteAssistantResponse> deleteAssistantWithHttpInfo(String assistantId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'assistantId' is set
        if (assistantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'assistantId' when calling deleteAssistant");
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
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };

        ParameterizedTypeReference<DeleteAssistantResponse> localReturnType = new ParameterizedTypeReference<DeleteAssistantResponse>() {};
        return apiClient.invokeAPI("/assistants/{assistant_id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete an assistant file.
     * 
     * <p><b>200</b> - OK
     * @param assistantId The ID of the assistant that the file belongs to. (required)
     * @param fileId The ID of the file to delete. (required)
     * @return DeleteAssistantFileResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DeleteAssistantFileResponse deleteAssistantFile(String assistantId, String fileId) throws RestClientException {
        return deleteAssistantFileWithHttpInfo(assistantId, fileId).getBody();
    }

    /**
     * Delete an assistant file.
     * 
     * <p><b>200</b> - OK
     * @param assistantId The ID of the assistant that the file belongs to. (required)
     * @param fileId The ID of the file to delete. (required)
     * @return ResponseEntity&lt;DeleteAssistantFileResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DeleteAssistantFileResponse> deleteAssistantFileWithHttpInfo(String assistantId, String fileId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'assistantId' is set
        if (assistantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'assistantId' when calling deleteAssistantFile");
        }
        
        // verify the required parameter 'fileId' is set
        if (fileId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'fileId' when calling deleteAssistantFile");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("assistant_id", assistantId);
        uriVariables.put("file_id", fileId);

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

        ParameterizedTypeReference<DeleteAssistantFileResponse> localReturnType = new ParameterizedTypeReference<DeleteAssistantFileResponse>() {};
        return apiClient.invokeAPI("/assistants/{assistant_id}/files/{file_id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete a thread.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the thread to delete. (required)
     * @return DeleteThreadResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DeleteThreadResponse deleteThread(String threadId) throws RestClientException {
        return deleteThreadWithHttpInfo(threadId).getBody();
    }

    /**
     * Delete a thread.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the thread to delete. (required)
     * @return ResponseEntity&lt;DeleteThreadResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DeleteThreadResponse> deleteThreadWithHttpInfo(String threadId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'threadId' is set
        if (threadId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'threadId' when calling deleteThread");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("thread_id", threadId);

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

        ParameterizedTypeReference<DeleteThreadResponse> localReturnType = new ParameterizedTypeReference<DeleteThreadResponse>() {};
        return apiClient.invokeAPI("/threads/{thread_id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves an assistant.
     * 
     * <p><b>200</b> - OK
     * @param assistantId The ID of the assistant to retrieve. (required)
     * @return AssistantObject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AssistantObject getAssistant(String assistantId) throws RestClientException {
        return getAssistantWithHttpInfo(assistantId).getBody();
    }

    /**
     * Retrieves an assistant.
     * 
     * <p><b>200</b> - OK
     * @param assistantId The ID of the assistant to retrieve. (required)
     * @return ResponseEntity&lt;AssistantObject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AssistantObject> getAssistantWithHttpInfo(String assistantId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'assistantId' is set
        if (assistantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'assistantId' when calling getAssistant");
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
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };

        ParameterizedTypeReference<AssistantObject> localReturnType = new ParameterizedTypeReference<AssistantObject>() {};
        return apiClient.invokeAPI("/assistants/{assistant_id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves an AssistantFile.
     * 
     * <p><b>200</b> - OK
     * @param assistantId The ID of the assistant who the file belongs to. (required)
     * @param fileId The ID of the file we&#39;re getting. (required)
     * @return AssistantFileObject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AssistantFileObject getAssistantFile(String assistantId, String fileId) throws RestClientException {
        return getAssistantFileWithHttpInfo(assistantId, fileId).getBody();
    }

    /**
     * Retrieves an AssistantFile.
     * 
     * <p><b>200</b> - OK
     * @param assistantId The ID of the assistant who the file belongs to. (required)
     * @param fileId The ID of the file we&#39;re getting. (required)
     * @return ResponseEntity&lt;AssistantFileObject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AssistantFileObject> getAssistantFileWithHttpInfo(String assistantId, String fileId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'assistantId' is set
        if (assistantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'assistantId' when calling getAssistantFile");
        }
        
        // verify the required parameter 'fileId' is set
        if (fileId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'fileId' when calling getAssistantFile");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("assistant_id", assistantId);
        uriVariables.put("file_id", fileId);

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

        ParameterizedTypeReference<AssistantFileObject> localReturnType = new ParameterizedTypeReference<AssistantFileObject>() {};
        return apiClient.invokeAPI("/assistants/{assistant_id}/files/{file_id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieve a message.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the [thread](/docs/api-reference/threads) to which this message belongs. (required)
     * @param messageId The ID of the message to retrieve. (required)
     * @return MessageObject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public MessageObject getMessage(String threadId, String messageId) throws RestClientException {
        return getMessageWithHttpInfo(threadId, messageId).getBody();
    }

    /**
     * Retrieve a message.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the [thread](/docs/api-reference/threads) to which this message belongs. (required)
     * @param messageId The ID of the message to retrieve. (required)
     * @return ResponseEntity&lt;MessageObject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<MessageObject> getMessageWithHttpInfo(String threadId, String messageId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'threadId' is set
        if (threadId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'threadId' when calling getMessage");
        }
        
        // verify the required parameter 'messageId' is set
        if (messageId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'messageId' when calling getMessage");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("thread_id", threadId);
        uriVariables.put("message_id", messageId);

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

        ParameterizedTypeReference<MessageObject> localReturnType = new ParameterizedTypeReference<MessageObject>() {};
        return apiClient.invokeAPI("/threads/{thread_id}/messages/{message_id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves a message file.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the thread to which the message and File belong. (required)
     * @param messageId The ID of the message the file belongs to. (required)
     * @param fileId The ID of the file being retrieved. (required)
     * @return MessageFileObject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public MessageFileObject getMessageFile(String threadId, String messageId, String fileId) throws RestClientException {
        return getMessageFileWithHttpInfo(threadId, messageId, fileId).getBody();
    }

    /**
     * Retrieves a message file.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the thread to which the message and File belong. (required)
     * @param messageId The ID of the message the file belongs to. (required)
     * @param fileId The ID of the file being retrieved. (required)
     * @return ResponseEntity&lt;MessageFileObject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<MessageFileObject> getMessageFileWithHttpInfo(String threadId, String messageId, String fileId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'threadId' is set
        if (threadId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'threadId' when calling getMessageFile");
        }
        
        // verify the required parameter 'messageId' is set
        if (messageId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'messageId' when calling getMessageFile");
        }
        
        // verify the required parameter 'fileId' is set
        if (fileId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'fileId' when calling getMessageFile");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("thread_id", threadId);
        uriVariables.put("message_id", messageId);
        uriVariables.put("file_id", fileId);

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

        ParameterizedTypeReference<MessageFileObject> localReturnType = new ParameterizedTypeReference<MessageFileObject>() {};
        return apiClient.invokeAPI("/threads/{thread_id}/messages/{message_id}/files/{file_id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves a run.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the [thread](/docs/api-reference/threads) that was run. (required)
     * @param runId The ID of the run to retrieve. (required)
     * @return RunObject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RunObject getRun(String threadId, String runId) throws RestClientException {
        return getRunWithHttpInfo(threadId, runId).getBody();
    }

    /**
     * Retrieves a run.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the [thread](/docs/api-reference/threads) that was run. (required)
     * @param runId The ID of the run to retrieve. (required)
     * @return ResponseEntity&lt;RunObject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RunObject> getRunWithHttpInfo(String threadId, String runId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'threadId' is set
        if (threadId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'threadId' when calling getRun");
        }
        
        // verify the required parameter 'runId' is set
        if (runId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'runId' when calling getRun");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("thread_id", threadId);
        uriVariables.put("run_id", runId);

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

        ParameterizedTypeReference<RunObject> localReturnType = new ParameterizedTypeReference<RunObject>() {};
        return apiClient.invokeAPI("/threads/{thread_id}/runs/{run_id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves a run step.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the thread to which the run and run step belongs. (required)
     * @param runId The ID of the run to which the run step belongs. (required)
     * @param stepId The ID of the run step to retrieve. (required)
     * @return RunStepObject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RunStepObject getRunStep(String threadId, String runId, String stepId) throws RestClientException {
        return getRunStepWithHttpInfo(threadId, runId, stepId).getBody();
    }

    /**
     * Retrieves a run step.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the thread to which the run and run step belongs. (required)
     * @param runId The ID of the run to which the run step belongs. (required)
     * @param stepId The ID of the run step to retrieve. (required)
     * @return ResponseEntity&lt;RunStepObject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RunStepObject> getRunStepWithHttpInfo(String threadId, String runId, String stepId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'threadId' is set
        if (threadId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'threadId' when calling getRunStep");
        }
        
        // verify the required parameter 'runId' is set
        if (runId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'runId' when calling getRunStep");
        }
        
        // verify the required parameter 'stepId' is set
        if (stepId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'stepId' when calling getRunStep");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("thread_id", threadId);
        uriVariables.put("run_id", runId);
        uriVariables.put("step_id", stepId);

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

        ParameterizedTypeReference<RunStepObject> localReturnType = new ParameterizedTypeReference<RunStepObject>() {};
        return apiClient.invokeAPI("/threads/{thread_id}/runs/{run_id}/steps/{step_id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Retrieves a thread.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the thread to retrieve. (required)
     * @return ThreadObject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ThreadObject getThread(String threadId) throws RestClientException {
        return getThreadWithHttpInfo(threadId).getBody();
    }

    /**
     * Retrieves a thread.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the thread to retrieve. (required)
     * @return ResponseEntity&lt;ThreadObject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ThreadObject> getThreadWithHttpInfo(String threadId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'threadId' is set
        if (threadId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'threadId' when calling getThread");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("thread_id", threadId);

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

        ParameterizedTypeReference<ThreadObject> localReturnType = new ParameterizedTypeReference<ThreadObject>() {};
        return apiClient.invokeAPI("/threads/{thread_id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Returns a list of assistant files.
     * 
     * <p><b>200</b> - OK
     * @param assistantId The ID of the assistant the file belongs to. (required)
     * @param limit A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20.  (optional, default to 20)
     * @param order Sort order by the &#x60;created_at&#x60; timestamp of the objects. &#x60;asc&#x60; for ascending order and &#x60;desc&#x60; for descending order.  (optional, default to desc)
     * @param after A cursor for use in pagination. &#x60;after&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after&#x3D;obj_foo in order to fetch the next page of the list.  (optional)
     * @param before A cursor for use in pagination. &#x60;before&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before&#x3D;obj_foo in order to fetch the previous page of the list.  (optional)
     * @return ListAssistantFilesResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ListAssistantFilesResponse listAssistantFiles(String assistantId, Integer limit, String order, String after, String before) throws RestClientException {
        return listAssistantFilesWithHttpInfo(assistantId, limit, order, after, before).getBody();
    }

    /**
     * Returns a list of assistant files.
     * 
     * <p><b>200</b> - OK
     * @param assistantId The ID of the assistant the file belongs to. (required)
     * @param limit A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20.  (optional, default to 20)
     * @param order Sort order by the &#x60;created_at&#x60; timestamp of the objects. &#x60;asc&#x60; for ascending order and &#x60;desc&#x60; for descending order.  (optional, default to desc)
     * @param after A cursor for use in pagination. &#x60;after&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after&#x3D;obj_foo in order to fetch the next page of the list.  (optional)
     * @param before A cursor for use in pagination. &#x60;before&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before&#x3D;obj_foo in order to fetch the previous page of the list.  (optional)
     * @return ResponseEntity&lt;ListAssistantFilesResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ListAssistantFilesResponse> listAssistantFilesWithHttpInfo(String assistantId, Integer limit, String order, String after, String before) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'assistantId' is set
        if (assistantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'assistantId' when calling listAssistantFiles");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("assistant_id", assistantId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "order", order));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "after", after));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "before", before));


        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };

        ParameterizedTypeReference<ListAssistantFilesResponse> localReturnType = new ParameterizedTypeReference<ListAssistantFilesResponse>() {};
        return apiClient.invokeAPI("/assistants/{assistant_id}/files", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Returns a list of assistants.
     * 
     * <p><b>200</b> - OK
     * @param limit A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20.  (optional, default to 20)
     * @param order Sort order by the &#x60;created_at&#x60; timestamp of the objects. &#x60;asc&#x60; for ascending order and &#x60;desc&#x60; for descending order.  (optional, default to desc)
     * @param after A cursor for use in pagination. &#x60;after&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after&#x3D;obj_foo in order to fetch the next page of the list.  (optional)
     * @param before A cursor for use in pagination. &#x60;before&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before&#x3D;obj_foo in order to fetch the previous page of the list.  (optional)
     * @return ListAssistantsResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ListAssistantsResponse listAssistants(Integer limit, String order, String after, String before) throws RestClientException {
        return listAssistantsWithHttpInfo(limit, order, after, before).getBody();
    }

    /**
     * Returns a list of assistants.
     * 
     * <p><b>200</b> - OK
     * @param limit A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20.  (optional, default to 20)
     * @param order Sort order by the &#x60;created_at&#x60; timestamp of the objects. &#x60;asc&#x60; for ascending order and &#x60;desc&#x60; for descending order.  (optional, default to desc)
     * @param after A cursor for use in pagination. &#x60;after&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after&#x3D;obj_foo in order to fetch the next page of the list.  (optional)
     * @param before A cursor for use in pagination. &#x60;before&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before&#x3D;obj_foo in order to fetch the previous page of the list.  (optional)
     * @return ResponseEntity&lt;ListAssistantsResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ListAssistantsResponse> listAssistantsWithHttpInfo(Integer limit, String order, String after, String before) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "order", order));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "after", after));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "before", before));


        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };

        ParameterizedTypeReference<ListAssistantsResponse> localReturnType = new ParameterizedTypeReference<ListAssistantsResponse>() {};
        return apiClient.invokeAPI("/assistants", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Returns a list of message files.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the thread that the message and files belong to. (required)
     * @param messageId The ID of the message that the files belongs to. (required)
     * @param limit A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20.  (optional, default to 20)
     * @param order Sort order by the &#x60;created_at&#x60; timestamp of the objects. &#x60;asc&#x60; for ascending order and &#x60;desc&#x60; for descending order.  (optional, default to desc)
     * @param after A cursor for use in pagination. &#x60;after&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after&#x3D;obj_foo in order to fetch the next page of the list.  (optional)
     * @param before A cursor for use in pagination. &#x60;before&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before&#x3D;obj_foo in order to fetch the previous page of the list.  (optional)
     * @return ListMessageFilesResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ListMessageFilesResponse listMessageFiles(String threadId, String messageId, Integer limit, String order, String after, String before) throws RestClientException {
        return listMessageFilesWithHttpInfo(threadId, messageId, limit, order, after, before).getBody();
    }

    /**
     * Returns a list of message files.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the thread that the message and files belong to. (required)
     * @param messageId The ID of the message that the files belongs to. (required)
     * @param limit A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20.  (optional, default to 20)
     * @param order Sort order by the &#x60;created_at&#x60; timestamp of the objects. &#x60;asc&#x60; for ascending order and &#x60;desc&#x60; for descending order.  (optional, default to desc)
     * @param after A cursor for use in pagination. &#x60;after&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after&#x3D;obj_foo in order to fetch the next page of the list.  (optional)
     * @param before A cursor for use in pagination. &#x60;before&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before&#x3D;obj_foo in order to fetch the previous page of the list.  (optional)
     * @return ResponseEntity&lt;ListMessageFilesResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ListMessageFilesResponse> listMessageFilesWithHttpInfo(String threadId, String messageId, Integer limit, String order, String after, String before) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'threadId' is set
        if (threadId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'threadId' when calling listMessageFiles");
        }
        
        // verify the required parameter 'messageId' is set
        if (messageId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'messageId' when calling listMessageFiles");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("thread_id", threadId);
        uriVariables.put("message_id", messageId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "order", order));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "after", after));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "before", before));


        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };

        ParameterizedTypeReference<ListMessageFilesResponse> localReturnType = new ParameterizedTypeReference<ListMessageFilesResponse>() {};
        return apiClient.invokeAPI("/threads/{thread_id}/messages/{message_id}/files", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Returns a list of messages for a given thread.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the [thread](/docs/api-reference/threads) the messages belong to. (required)
     * @param limit A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20.  (optional, default to 20)
     * @param order Sort order by the &#x60;created_at&#x60; timestamp of the objects. &#x60;asc&#x60; for ascending order and &#x60;desc&#x60; for descending order.  (optional, default to desc)
     * @param after A cursor for use in pagination. &#x60;after&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after&#x3D;obj_foo in order to fetch the next page of the list.  (optional)
     * @param before A cursor for use in pagination. &#x60;before&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before&#x3D;obj_foo in order to fetch the previous page of the list.  (optional)
     * @return ListMessagesResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ListMessagesResponse listMessages(String threadId, Integer limit, String order, String after, String before) throws RestClientException {
        return listMessagesWithHttpInfo(threadId, limit, order, after, before).getBody();
    }

    /**
     * Returns a list of messages for a given thread.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the [thread](/docs/api-reference/threads) the messages belong to. (required)
     * @param limit A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20.  (optional, default to 20)
     * @param order Sort order by the &#x60;created_at&#x60; timestamp of the objects. &#x60;asc&#x60; for ascending order and &#x60;desc&#x60; for descending order.  (optional, default to desc)
     * @param after A cursor for use in pagination. &#x60;after&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after&#x3D;obj_foo in order to fetch the next page of the list.  (optional)
     * @param before A cursor for use in pagination. &#x60;before&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before&#x3D;obj_foo in order to fetch the previous page of the list.  (optional)
     * @return ResponseEntity&lt;ListMessagesResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ListMessagesResponse> listMessagesWithHttpInfo(String threadId, Integer limit, String order, String after, String before) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'threadId' is set
        if (threadId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'threadId' when calling listMessages");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("thread_id", threadId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "order", order));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "after", after));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "before", before));


        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };

        ParameterizedTypeReference<ListMessagesResponse> localReturnType = new ParameterizedTypeReference<ListMessagesResponse>() {};
        return apiClient.invokeAPI("/threads/{thread_id}/messages", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Returns a list of run steps belonging to a run.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the thread the run and run steps belong to. (required)
     * @param runId The ID of the run the run steps belong to. (required)
     * @param limit A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20.  (optional, default to 20)
     * @param order Sort order by the &#x60;created_at&#x60; timestamp of the objects. &#x60;asc&#x60; for ascending order and &#x60;desc&#x60; for descending order.  (optional, default to desc)
     * @param after A cursor for use in pagination. &#x60;after&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after&#x3D;obj_foo in order to fetch the next page of the list.  (optional)
     * @param before A cursor for use in pagination. &#x60;before&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before&#x3D;obj_foo in order to fetch the previous page of the list.  (optional)
     * @return ListRunStepsResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ListRunStepsResponse listRunSteps(String threadId, String runId, Integer limit, String order, String after, String before) throws RestClientException {
        return listRunStepsWithHttpInfo(threadId, runId, limit, order, after, before).getBody();
    }

    /**
     * Returns a list of run steps belonging to a run.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the thread the run and run steps belong to. (required)
     * @param runId The ID of the run the run steps belong to. (required)
     * @param limit A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20.  (optional, default to 20)
     * @param order Sort order by the &#x60;created_at&#x60; timestamp of the objects. &#x60;asc&#x60; for ascending order and &#x60;desc&#x60; for descending order.  (optional, default to desc)
     * @param after A cursor for use in pagination. &#x60;after&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after&#x3D;obj_foo in order to fetch the next page of the list.  (optional)
     * @param before A cursor for use in pagination. &#x60;before&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before&#x3D;obj_foo in order to fetch the previous page of the list.  (optional)
     * @return ResponseEntity&lt;ListRunStepsResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ListRunStepsResponse> listRunStepsWithHttpInfo(String threadId, String runId, Integer limit, String order, String after, String before) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'threadId' is set
        if (threadId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'threadId' when calling listRunSteps");
        }
        
        // verify the required parameter 'runId' is set
        if (runId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'runId' when calling listRunSteps");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("thread_id", threadId);
        uriVariables.put("run_id", runId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "order", order));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "after", after));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "before", before));


        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };

        ParameterizedTypeReference<ListRunStepsResponse> localReturnType = new ParameterizedTypeReference<ListRunStepsResponse>() {};
        return apiClient.invokeAPI("/threads/{thread_id}/runs/{run_id}/steps", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Returns a list of runs belonging to a thread.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the thread the run belongs to. (required)
     * @param limit A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20.  (optional, default to 20)
     * @param order Sort order by the &#x60;created_at&#x60; timestamp of the objects. &#x60;asc&#x60; for ascending order and &#x60;desc&#x60; for descending order.  (optional, default to desc)
     * @param after A cursor for use in pagination. &#x60;after&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after&#x3D;obj_foo in order to fetch the next page of the list.  (optional)
     * @param before A cursor for use in pagination. &#x60;before&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before&#x3D;obj_foo in order to fetch the previous page of the list.  (optional)
     * @return ListRunsResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ListRunsResponse listRuns(String threadId, Integer limit, String order, String after, String before) throws RestClientException {
        return listRunsWithHttpInfo(threadId, limit, order, after, before).getBody();
    }

    /**
     * Returns a list of runs belonging to a thread.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the thread the run belongs to. (required)
     * @param limit A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20.  (optional, default to 20)
     * @param order Sort order by the &#x60;created_at&#x60; timestamp of the objects. &#x60;asc&#x60; for ascending order and &#x60;desc&#x60; for descending order.  (optional, default to desc)
     * @param after A cursor for use in pagination. &#x60;after&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after&#x3D;obj_foo in order to fetch the next page of the list.  (optional)
     * @param before A cursor for use in pagination. &#x60;before&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before&#x3D;obj_foo in order to fetch the previous page of the list.  (optional)
     * @return ResponseEntity&lt;ListRunsResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ListRunsResponse> listRunsWithHttpInfo(String threadId, Integer limit, String order, String after, String before) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'threadId' is set
        if (threadId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'threadId' when calling listRuns");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("thread_id", threadId);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "order", order));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "after", after));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "before", before));


        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };

        ParameterizedTypeReference<ListRunsResponse> localReturnType = new ParameterizedTypeReference<ListRunsResponse>() {};
        return apiClient.invokeAPI("/threads/{thread_id}/runs", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Modifies a message.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the thread to which this message belongs. (required)
     * @param messageId The ID of the message to modify. (required)
     * @param modifyMessageRequest  (required)
     * @return MessageObject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public MessageObject modifyMessage(String threadId, String messageId, ModifyMessageRequest modifyMessageRequest) throws RestClientException {
        return modifyMessageWithHttpInfo(threadId, messageId, modifyMessageRequest).getBody();
    }

    /**
     * Modifies a message.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the thread to which this message belongs. (required)
     * @param messageId The ID of the message to modify. (required)
     * @param modifyMessageRequest  (required)
     * @return ResponseEntity&lt;MessageObject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<MessageObject> modifyMessageWithHttpInfo(String threadId, String messageId, ModifyMessageRequest modifyMessageRequest) throws RestClientException {
        Object localVarPostBody = modifyMessageRequest;
        
        // verify the required parameter 'threadId' is set
        if (threadId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'threadId' when calling modifyMessage");
        }
        
        // verify the required parameter 'messageId' is set
        if (messageId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'messageId' when calling modifyMessage");
        }
        
        // verify the required parameter 'modifyMessageRequest' is set
        if (modifyMessageRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'modifyMessageRequest' when calling modifyMessage");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("thread_id", threadId);
        uriVariables.put("message_id", messageId);

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

        ParameterizedTypeReference<MessageObject> localReturnType = new ParameterizedTypeReference<MessageObject>() {};
        return apiClient.invokeAPI("/threads/{thread_id}/messages/{message_id}", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Modifies a run.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the [thread](/docs/api-reference/threads) that was run. (required)
     * @param runId The ID of the run to modify. (required)
     * @param modifyRunRequest  (required)
     * @return RunObject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RunObject modifyRun(String threadId, String runId, ModifyRunRequest modifyRunRequest) throws RestClientException {
        return modifyRunWithHttpInfo(threadId, runId, modifyRunRequest).getBody();
    }

    /**
     * Modifies a run.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the [thread](/docs/api-reference/threads) that was run. (required)
     * @param runId The ID of the run to modify. (required)
     * @param modifyRunRequest  (required)
     * @return ResponseEntity&lt;RunObject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RunObject> modifyRunWithHttpInfo(String threadId, String runId, ModifyRunRequest modifyRunRequest) throws RestClientException {
        Object localVarPostBody = modifyRunRequest;
        
        // verify the required parameter 'threadId' is set
        if (threadId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'threadId' when calling modifyRun");
        }
        
        // verify the required parameter 'runId' is set
        if (runId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'runId' when calling modifyRun");
        }
        
        // verify the required parameter 'modifyRunRequest' is set
        if (modifyRunRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'modifyRunRequest' when calling modifyRun");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("thread_id", threadId);
        uriVariables.put("run_id", runId);

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

        ParameterizedTypeReference<RunObject> localReturnType = new ParameterizedTypeReference<RunObject>() {};
        return apiClient.invokeAPI("/threads/{thread_id}/runs/{run_id}", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Modifies a thread.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the thread to modify. Only the &#x60;metadata&#x60; can be modified. (required)
     * @param modifyThreadRequest  (required)
     * @return ThreadObject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ThreadObject modifyThread(String threadId, ModifyThreadRequest modifyThreadRequest) throws RestClientException {
        return modifyThreadWithHttpInfo(threadId, modifyThreadRequest).getBody();
    }

    /**
     * Modifies a thread.
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the thread to modify. Only the &#x60;metadata&#x60; can be modified. (required)
     * @param modifyThreadRequest  (required)
     * @return ResponseEntity&lt;ThreadObject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ThreadObject> modifyThreadWithHttpInfo(String threadId, ModifyThreadRequest modifyThreadRequest) throws RestClientException {
        Object localVarPostBody = modifyThreadRequest;
        
        // verify the required parameter 'threadId' is set
        if (threadId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'threadId' when calling modifyThread");
        }
        
        // verify the required parameter 'modifyThreadRequest' is set
        if (modifyThreadRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'modifyThreadRequest' when calling modifyThread");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("thread_id", threadId);

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

        ParameterizedTypeReference<ThreadObject> localReturnType = new ParameterizedTypeReference<ThreadObject>() {};
        return apiClient.invokeAPI("/threads/{thread_id}", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * When a run has the &#x60;status: \&quot;requires_action\&quot;&#x60; and &#x60;required_action.type&#x60; is &#x60;submit_tool_outputs&#x60;, this endpoint can be used to submit the outputs from the tool calls once they&#39;re all completed. All outputs must be submitted in a single request. 
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the [thread](/docs/api-reference/threads) to which this run belongs. (required)
     * @param runId The ID of the run that requires the tool output submission. (required)
     * @param submitToolOutputsRunRequest  (required)
     * @return RunObject
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public RunObject submitToolOuputsToRun(String threadId, String runId, SubmitToolOutputsRunRequest submitToolOutputsRunRequest) throws RestClientException {
        return submitToolOuputsToRunWithHttpInfo(threadId, runId, submitToolOutputsRunRequest).getBody();
    }

    /**
     * When a run has the &#x60;status: \&quot;requires_action\&quot;&#x60; and &#x60;required_action.type&#x60; is &#x60;submit_tool_outputs&#x60;, this endpoint can be used to submit the outputs from the tool calls once they&#39;re all completed. All outputs must be submitted in a single request. 
     * 
     * <p><b>200</b> - OK
     * @param threadId The ID of the [thread](/docs/api-reference/threads) to which this run belongs. (required)
     * @param runId The ID of the run that requires the tool output submission. (required)
     * @param submitToolOutputsRunRequest  (required)
     * @return ResponseEntity&lt;RunObject&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<RunObject> submitToolOuputsToRunWithHttpInfo(String threadId, String runId, SubmitToolOutputsRunRequest submitToolOutputsRunRequest) throws RestClientException {
        Object localVarPostBody = submitToolOutputsRunRequest;
        
        // verify the required parameter 'threadId' is set
        if (threadId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'threadId' when calling submitToolOuputsToRun");
        }
        
        // verify the required parameter 'runId' is set
        if (runId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'runId' when calling submitToolOuputsToRun");
        }
        
        // verify the required parameter 'submitToolOutputsRunRequest' is set
        if (submitToolOutputsRunRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'submitToolOutputsRunRequest' when calling submitToolOuputsToRun");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("thread_id", threadId);
        uriVariables.put("run_id", runId);

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

        ParameterizedTypeReference<RunObject> localReturnType = new ParameterizedTypeReference<RunObject>() {};
        return apiClient.invokeAPI("/threads/{thread_id}/runs/{run_id}/submit_tool_outputs", HttpMethod.POST, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
}
