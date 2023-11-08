package ch.lianto.openai.client.api;

import ch.lianto.openai.client.ApiClient;

import ch.lianto.openai.client.model.DeleteFileResponse;
import java.io.File;
import ch.lianto.openai.client.model.ListFilesResponse;
import ch.lianto.openai.client.model.OpenAIFile;

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
public class FilesApi {
    private ApiClient apiClient;

    public FilesApi() {
        this(new ApiClient());
    }

    public FilesApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Upload a file that can be used across various endpoints/features. The size of all the files uploaded by one organization can be up to 100 GB.  The size of individual files for can be a maximum of 512MB. See the [Assistants Tools guide](/docs/assistants/tools) to learn more about the types of files supported. The Fine-tuning API only supports &#x60;.jsonl&#x60; files.  Please [contact us](https://help.openai.com/) if you need to increase these storage limits. 
     * 
     * <p><b>200</b> - OK
     * @param _file The File object (not file name) to be uploaded.  (required)
     * @param purpose The intended purpose of the uploaded file.  Use \\\&quot;fine-tune\\\&quot; for [Fine-tuning](/docs/api-reference/fine-tuning) and \\\&quot;assistants\\\&quot; for [Assistants](/docs/api-reference/assistants) and [Messages](/docs/api-reference/messages). This allows us to validate the format of the uploaded file is correct for fine-tuning.  (required)
     * @return OpenAIFile
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public OpenAIFile createFile(File _file, String purpose) throws RestClientException {
        return createFileWithHttpInfo(_file, purpose).getBody();
    }

    /**
     * Upload a file that can be used across various endpoints/features. The size of all the files uploaded by one organization can be up to 100 GB.  The size of individual files for can be a maximum of 512MB. See the [Assistants Tools guide](/docs/assistants/tools) to learn more about the types of files supported. The Fine-tuning API only supports &#x60;.jsonl&#x60; files.  Please [contact us](https://help.openai.com/) if you need to increase these storage limits. 
     * 
     * <p><b>200</b> - OK
     * @param _file The File object (not file name) to be uploaded.  (required)
     * @param purpose The intended purpose of the uploaded file.  Use \\\&quot;fine-tune\\\&quot; for [Fine-tuning](/docs/api-reference/fine-tuning) and \\\&quot;assistants\\\&quot; for [Assistants](/docs/api-reference/assistants) and [Messages](/docs/api-reference/messages). This allows us to validate the format of the uploaded file is correct for fine-tuning.  (required)
     * @return ResponseEntity&lt;OpenAIFile&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<OpenAIFile> createFileWithHttpInfo(File _file, String purpose) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter '_file' is set
        if (_file == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter '_file' when calling createFile");
        }
        
        // verify the required parameter 'purpose' is set
        if (purpose == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'purpose' when calling createFile");
        }
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        if (_file != null)
            localVarFormParams.add("file", new FileSystemResource(_file));
        if (purpose != null)
            localVarFormParams.add("purpose", purpose);

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "multipart/form-data"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };

        ParameterizedTypeReference<OpenAIFile> localReturnType = new ParameterizedTypeReference<OpenAIFile>() {};
        return apiClient.invokeAPI("/files", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Delete a file.
     * 
     * <p><b>200</b> - OK
     * @param fileId The ID of the file to use for this request. (required)
     * @return DeleteFileResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DeleteFileResponse deleteFile(String fileId) throws RestClientException {
        return deleteFileWithHttpInfo(fileId).getBody();
    }

    /**
     * Delete a file.
     * 
     * <p><b>200</b> - OK
     * @param fileId The ID of the file to use for this request. (required)
     * @return ResponseEntity&lt;DeleteFileResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DeleteFileResponse> deleteFileWithHttpInfo(String fileId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'fileId' is set
        if (fileId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'fileId' when calling deleteFile");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
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

        ParameterizedTypeReference<DeleteFileResponse> localReturnType = new ParameterizedTypeReference<DeleteFileResponse>() {};
        return apiClient.invokeAPI("/files/{file_id}", HttpMethod.DELETE, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Returns the contents of the specified file.
     * 
     * <p><b>200</b> - OK
     * @param fileId The ID of the file to use for this request. (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String downloadFile(String fileId) throws RestClientException {
        return downloadFileWithHttpInfo(fileId).getBody();
    }

    /**
     * Returns the contents of the specified file.
     * 
     * <p><b>200</b> - OK
     * @param fileId The ID of the file to use for this request. (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> downloadFileWithHttpInfo(String fileId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'fileId' is set
        if (fileId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'fileId' when calling downloadFile");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
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

        ParameterizedTypeReference<String> localReturnType = new ParameterizedTypeReference<String>() {};
        return apiClient.invokeAPI("/files/{file_id}/content", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Returns a list of files that belong to the user&#39;s organization.
     * 
     * <p><b>200</b> - OK
     * @param purpose Only return files with the given purpose. (optional)
     * @return ListFilesResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ListFilesResponse listFiles(String purpose) throws RestClientException {
        return listFilesWithHttpInfo(purpose).getBody();
    }

    /**
     * Returns a list of files that belong to the user&#39;s organization.
     * 
     * <p><b>200</b> - OK
     * @param purpose Only return files with the given purpose. (optional)
     * @return ResponseEntity&lt;ListFilesResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ListFilesResponse> listFilesWithHttpInfo(String purpose) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "purpose", purpose));


        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };

        ParameterizedTypeReference<ListFilesResponse> localReturnType = new ParameterizedTypeReference<ListFilesResponse>() {};
        return apiClient.invokeAPI("/files", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Returns information about a specific file.
     * 
     * <p><b>200</b> - OK
     * @param fileId The ID of the file to use for this request. (required)
     * @return OpenAIFile
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public OpenAIFile retrieveFile(String fileId) throws RestClientException {
        return retrieveFileWithHttpInfo(fileId).getBody();
    }

    /**
     * Returns information about a specific file.
     * 
     * <p><b>200</b> - OK
     * @param fileId The ID of the file to use for this request. (required)
     * @return ResponseEntity&lt;OpenAIFile&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<OpenAIFile> retrieveFileWithHttpInfo(String fileId) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'fileId' is set
        if (fileId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'fileId' when calling retrieveFile");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
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

        ParameterizedTypeReference<OpenAIFile> localReturnType = new ParameterizedTypeReference<OpenAIFile>() {};
        return apiClient.invokeAPI("/files/{file_id}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
}
