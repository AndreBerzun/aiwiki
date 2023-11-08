package ch.lianto.openai.client.api;

import ch.lianto.openai.client.ApiClient;

import ch.lianto.openai.client.model.CreateImageEditRequestModel;
import ch.lianto.openai.client.model.CreateImageRequest;
import java.io.File;
import ch.lianto.openai.client.model.ImagesResponse;

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
public class ImagesApi {
    private ApiClient apiClient;

    public ImagesApi() {
        this(new ApiClient());
    }

    public ImagesApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Creates an image given a prompt.
     * 
     * <p><b>200</b> - OK
     * @param createImageRequest  (required)
     * @return ImagesResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ImagesResponse createImage(CreateImageRequest createImageRequest) throws RestClientException {
        return createImageWithHttpInfo(createImageRequest).getBody();
    }

    /**
     * Creates an image given a prompt.
     * 
     * <p><b>200</b> - OK
     * @param createImageRequest  (required)
     * @return ResponseEntity&lt;ImagesResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ImagesResponse> createImageWithHttpInfo(CreateImageRequest createImageRequest) throws RestClientException {
        Object localVarPostBody = createImageRequest;
        
        // verify the required parameter 'createImageRequest' is set
        if (createImageRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'createImageRequest' when calling createImage");
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

        ParameterizedTypeReference<ImagesResponse> localReturnType = new ParameterizedTypeReference<ImagesResponse>() {};
        return apiClient.invokeAPI("/images/generations", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Creates an edited or extended image given an original image and a prompt.
     * 
     * <p><b>200</b> - OK
     * @param image The image to edit. Must be a valid PNG file, less than 4MB, and square. If mask is not provided, image must have transparency, which will be used as the mask. (required)
     * @param prompt A text description of the desired image(s). The maximum length is 1000 characters. (required)
     * @param mask An additional image whose fully transparent areas (e.g. where alpha is zero) indicate where &#x60;image&#x60; should be edited. Must be a valid PNG file, less than 4MB, and have the same dimensions as &#x60;image&#x60;. (optional)
     * @param model  (optional, default to dall-e-2)
     * @param n The number of images to generate. Must be between 1 and 10. (optional, default to 1)
     * @param size The size of the generated images. Must be one of &#x60;256x256&#x60;, &#x60;512x512&#x60;, or &#x60;1024x1024&#x60;. (optional, default to 1024x1024)
     * @param responseFormat The format in which the generated images are returned. Must be one of &#x60;url&#x60; or &#x60;b64_json&#x60;. (optional, default to url)
     * @param user A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse. [Learn more](/docs/guides/safety-best-practices/end-user-ids).  (optional)
     * @return ImagesResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ImagesResponse createImageEdit(File image, String prompt, File mask, CreateImageEditRequestModel model, Integer n, String size, String responseFormat, String user) throws RestClientException {
        return createImageEditWithHttpInfo(image, prompt, mask, model, n, size, responseFormat, user).getBody();
    }

    /**
     * Creates an edited or extended image given an original image and a prompt.
     * 
     * <p><b>200</b> - OK
     * @param image The image to edit. Must be a valid PNG file, less than 4MB, and square. If mask is not provided, image must have transparency, which will be used as the mask. (required)
     * @param prompt A text description of the desired image(s). The maximum length is 1000 characters. (required)
     * @param mask An additional image whose fully transparent areas (e.g. where alpha is zero) indicate where &#x60;image&#x60; should be edited. Must be a valid PNG file, less than 4MB, and have the same dimensions as &#x60;image&#x60;. (optional)
     * @param model  (optional, default to dall-e-2)
     * @param n The number of images to generate. Must be between 1 and 10. (optional, default to 1)
     * @param size The size of the generated images. Must be one of &#x60;256x256&#x60;, &#x60;512x512&#x60;, or &#x60;1024x1024&#x60;. (optional, default to 1024x1024)
     * @param responseFormat The format in which the generated images are returned. Must be one of &#x60;url&#x60; or &#x60;b64_json&#x60;. (optional, default to url)
     * @param user A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse. [Learn more](/docs/guides/safety-best-practices/end-user-ids).  (optional)
     * @return ResponseEntity&lt;ImagesResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ImagesResponse> createImageEditWithHttpInfo(File image, String prompt, File mask, CreateImageEditRequestModel model, Integer n, String size, String responseFormat, String user) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'image' is set
        if (image == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'image' when calling createImageEdit");
        }
        
        // verify the required parameter 'prompt' is set
        if (prompt == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'prompt' when calling createImageEdit");
        }
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        if (image != null)
            localVarFormParams.add("image", new FileSystemResource(image));
        if (prompt != null)
            localVarFormParams.add("prompt", prompt);
        if (mask != null)
            localVarFormParams.add("mask", new FileSystemResource(mask));
        if (model != null)
            localVarFormParams.add("model", model);
        if (n != null)
            localVarFormParams.add("n", n);
        if (size != null)
            localVarFormParams.add("size", size);
        if (responseFormat != null)
            localVarFormParams.add("response_format", responseFormat);
        if (user != null)
            localVarFormParams.add("user", user);

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "multipart/form-data"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };

        ParameterizedTypeReference<ImagesResponse> localReturnType = new ParameterizedTypeReference<ImagesResponse>() {};
        return apiClient.invokeAPI("/images/edits", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Creates a variation of a given image.
     * 
     * <p><b>200</b> - OK
     * @param image The image to use as the basis for the variation(s). Must be a valid PNG file, less than 4MB, and square. (required)
     * @param model  (optional, default to dall-e-2)
     * @param n The number of images to generate. Must be between 1 and 10. For &#x60;dall-e-3&#x60;, only &#x60;n&#x3D;1&#x60; is supported. (optional, default to 1)
     * @param responseFormat The format in which the generated images are returned. Must be one of &#x60;url&#x60; or &#x60;b64_json&#x60;. (optional, default to url)
     * @param size The size of the generated images. Must be one of &#x60;256x256&#x60;, &#x60;512x512&#x60;, or &#x60;1024x1024&#x60;. (optional, default to 1024x1024)
     * @param user A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse. [Learn more](/docs/guides/safety-best-practices/end-user-ids).  (optional)
     * @return ImagesResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ImagesResponse createImageVariation(File image, CreateImageEditRequestModel model, Integer n, String responseFormat, String size, String user) throws RestClientException {
        return createImageVariationWithHttpInfo(image, model, n, responseFormat, size, user).getBody();
    }

    /**
     * Creates a variation of a given image.
     * 
     * <p><b>200</b> - OK
     * @param image The image to use as the basis for the variation(s). Must be a valid PNG file, less than 4MB, and square. (required)
     * @param model  (optional, default to dall-e-2)
     * @param n The number of images to generate. Must be between 1 and 10. For &#x60;dall-e-3&#x60;, only &#x60;n&#x3D;1&#x60; is supported. (optional, default to 1)
     * @param responseFormat The format in which the generated images are returned. Must be one of &#x60;url&#x60; or &#x60;b64_json&#x60;. (optional, default to url)
     * @param size The size of the generated images. Must be one of &#x60;256x256&#x60;, &#x60;512x512&#x60;, or &#x60;1024x1024&#x60;. (optional, default to 1024x1024)
     * @param user A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse. [Learn more](/docs/guides/safety-best-practices/end-user-ids).  (optional)
     * @return ResponseEntity&lt;ImagesResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ImagesResponse> createImageVariationWithHttpInfo(File image, CreateImageEditRequestModel model, Integer n, String responseFormat, String size, String user) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'image' is set
        if (image == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'image' when calling createImageVariation");
        }
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        if (image != null)
            localVarFormParams.add("image", new FileSystemResource(image));
        if (model != null)
            localVarFormParams.add("model", model);
        if (n != null)
            localVarFormParams.add("n", n);
        if (responseFormat != null)
            localVarFormParams.add("response_format", responseFormat);
        if (size != null)
            localVarFormParams.add("size", size);
        if (user != null)
            localVarFormParams.add("user", user);

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "multipart/form-data"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };

        ParameterizedTypeReference<ImagesResponse> localReturnType = new ParameterizedTypeReference<ImagesResponse>() {};
        return apiClient.invokeAPI("/images/variations", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
}
