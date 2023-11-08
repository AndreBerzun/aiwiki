/*
 * OpenAI API
 * The OpenAI REST API. Please see https://platform.openai.com/docs/api-reference for more details.
 *
 * The version of the OpenAPI document: 2.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package ch.lianto.openai.client.api;

import ch.lianto.openai.client.model.DeleteFileResponse;
import java.io.File;
import ch.lianto.openai.client.model.ListFilesResponse;
import ch.lianto.openai.client.model.OpenAIFile;
import org.junit.Test;
import org.junit.Ignore;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for FilesApi
 */
@Ignore
public class FilesApiTest {

    private final FilesApi api = new FilesApi();

    
    /**
     * Upload a file that can be used across various endpoints/features. The size of all the files uploaded by one organization can be up to 100 GB.  The size of individual files for can be a maximum of 512MB. See the [Assistants Tools guide](/docs/assistants/tools) to learn more about the types of files supported. The Fine-tuning API only supports &#x60;.jsonl&#x60; files.  Please [contact us](https://help.openai.com/) if you need to increase these storage limits. 
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createFileTest() {
        File _file = null;
        String purpose = null;
        OpenAIFile response = api.createFile(_file, purpose);

        // TODO: test validations
    }
    
    /**
     * Delete a file.
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteFileTest() {
        String fileId = null;
        DeleteFileResponse response = api.deleteFile(fileId);

        // TODO: test validations
    }
    
    /**
     * Returns the contents of the specified file.
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void downloadFileTest() {
        String fileId = null;
        String response = api.downloadFile(fileId);

        // TODO: test validations
    }
    
    /**
     * Returns a list of files that belong to the user&#39;s organization.
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void listFilesTest() {
        String purpose = null;
        ListFilesResponse response = api.listFiles(purpose);

        // TODO: test validations
    }
    
    /**
     * Returns information about a specific file.
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void retrieveFileTest() {
        String fileId = null;
        OpenAIFile response = api.retrieveFile(fileId);

        // TODO: test validations
    }
    
}
