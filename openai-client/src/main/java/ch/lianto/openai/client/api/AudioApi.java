package ch.lianto.openai.client.api;

import ch.lianto.openai.client.ApiClient;

import java.math.BigDecimal;
import ch.lianto.openai.client.model.CreateSpeechRequest;
import ch.lianto.openai.client.model.CreateTranscriptionRequestModel;
import ch.lianto.openai.client.model.CreateTranscriptionResponse;
import ch.lianto.openai.client.model.CreateTranslationResponse;
import java.io.File;

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
public class AudioApi {
    private ApiClient apiClient;

    public AudioApi() {
        this(new ApiClient());
    }

    public AudioApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Generates audio from the input text.
     * 
     * <p><b>200</b> - OK
     * @param createSpeechRequest  (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File createSpeech(CreateSpeechRequest createSpeechRequest) throws RestClientException {
        return createSpeechWithHttpInfo(createSpeechRequest).getBody();
    }

    /**
     * Generates audio from the input text.
     * 
     * <p><b>200</b> - OK
     * @param createSpeechRequest  (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> createSpeechWithHttpInfo(CreateSpeechRequest createSpeechRequest) throws RestClientException {
        Object localVarPostBody = createSpeechRequest;
        
        // verify the required parameter 'createSpeechRequest' is set
        if (createSpeechRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'createSpeechRequest' when calling createSpeech");
        }
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/octet-stream"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };

        ParameterizedTypeReference<File> localReturnType = new ParameterizedTypeReference<File>() {};
        return apiClient.invokeAPI("/audio/speech", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Transcribes audio into the input language.
     * 
     * <p><b>200</b> - OK
     * @param _file The audio file object (not file name) to transcribe, in one of these formats: flac, mp3, mp4, mpeg, mpga, m4a, ogg, wav, or webm.  (required)
     * @param model  (required)
     * @param language The language of the input audio. Supplying the input language in [ISO-639-1](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes) format will improve accuracy and latency.  (optional)
     * @param prompt An optional text to guide the model&#39;s style or continue a previous audio segment. The [prompt](/docs/guides/speech-to-text/prompting) should match the audio language.  (optional)
     * @param responseFormat The format of the transcript output, in one of these options: &#x60;json&#x60;, &#x60;text&#x60;, &#x60;srt&#x60;, &#x60;verbose_json&#x60;, or &#x60;vtt&#x60;.  (optional, default to json)
     * @param temperature The sampling temperature, between 0 and 1. Higher values like 0.8 will make the output more random, while lower values like 0.2 will make it more focused and deterministic. If set to 0, the model will use [log probability](https://en.wikipedia.org/wiki/Log_probability) to automatically increase the temperature until certain thresholds are hit.  (optional, default to 0)
     * @return CreateTranscriptionResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CreateTranscriptionResponse createTranscription(File _file, CreateTranscriptionRequestModel model, String language, String prompt, String responseFormat, BigDecimal temperature) throws RestClientException {
        return createTranscriptionWithHttpInfo(_file, model, language, prompt, responseFormat, temperature).getBody();
    }

    /**
     * Transcribes audio into the input language.
     * 
     * <p><b>200</b> - OK
     * @param _file The audio file object (not file name) to transcribe, in one of these formats: flac, mp3, mp4, mpeg, mpga, m4a, ogg, wav, or webm.  (required)
     * @param model  (required)
     * @param language The language of the input audio. Supplying the input language in [ISO-639-1](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes) format will improve accuracy and latency.  (optional)
     * @param prompt An optional text to guide the model&#39;s style or continue a previous audio segment. The [prompt](/docs/guides/speech-to-text/prompting) should match the audio language.  (optional)
     * @param responseFormat The format of the transcript output, in one of these options: &#x60;json&#x60;, &#x60;text&#x60;, &#x60;srt&#x60;, &#x60;verbose_json&#x60;, or &#x60;vtt&#x60;.  (optional, default to json)
     * @param temperature The sampling temperature, between 0 and 1. Higher values like 0.8 will make the output more random, while lower values like 0.2 will make it more focused and deterministic. If set to 0, the model will use [log probability](https://en.wikipedia.org/wiki/Log_probability) to automatically increase the temperature until certain thresholds are hit.  (optional, default to 0)
     * @return ResponseEntity&lt;CreateTranscriptionResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CreateTranscriptionResponse> createTranscriptionWithHttpInfo(File _file, CreateTranscriptionRequestModel model, String language, String prompt, String responseFormat, BigDecimal temperature) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter '_file' is set
        if (_file == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter '_file' when calling createTranscription");
        }
        
        // verify the required parameter 'model' is set
        if (model == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'model' when calling createTranscription");
        }
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        if (_file != null)
            localVarFormParams.add("file", new FileSystemResource(_file));
        if (model != null)
            localVarFormParams.add("model", model);
        if (language != null)
            localVarFormParams.add("language", language);
        if (prompt != null)
            localVarFormParams.add("prompt", prompt);
        if (responseFormat != null)
            localVarFormParams.add("response_format", responseFormat);
        if (temperature != null)
            localVarFormParams.add("temperature", temperature);

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "multipart/form-data"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };

        ParameterizedTypeReference<CreateTranscriptionResponse> localReturnType = new ParameterizedTypeReference<CreateTranscriptionResponse>() {};
        return apiClient.invokeAPI("/audio/transcriptions", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Translates audio into English.
     * 
     * <p><b>200</b> - OK
     * @param _file The audio file object (not file name) translate, in one of these formats: flac, mp3, mp4, mpeg, mpga, m4a, ogg, wav, or webm.  (required)
     * @param model  (required)
     * @param prompt An optional text to guide the model&#39;s style or continue a previous audio segment. The [prompt](/docs/guides/speech-to-text/prompting) should be in English.  (optional)
     * @param responseFormat The format of the transcript output, in one of these options: &#x60;json&#x60;, &#x60;text&#x60;, &#x60;srt&#x60;, &#x60;verbose_json&#x60;, or &#x60;vtt&#x60;.  (optional, default to json)
     * @param temperature The sampling temperature, between 0 and 1. Higher values like 0.8 will make the output more random, while lower values like 0.2 will make it more focused and deterministic. If set to 0, the model will use [log probability](https://en.wikipedia.org/wiki/Log_probability) to automatically increase the temperature until certain thresholds are hit.  (optional, default to 0)
     * @return CreateTranslationResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CreateTranslationResponse createTranslation(File _file, CreateTranscriptionRequestModel model, String prompt, String responseFormat, BigDecimal temperature) throws RestClientException {
        return createTranslationWithHttpInfo(_file, model, prompt, responseFormat, temperature).getBody();
    }

    /**
     * Translates audio into English.
     * 
     * <p><b>200</b> - OK
     * @param _file The audio file object (not file name) translate, in one of these formats: flac, mp3, mp4, mpeg, mpga, m4a, ogg, wav, or webm.  (required)
     * @param model  (required)
     * @param prompt An optional text to guide the model&#39;s style or continue a previous audio segment. The [prompt](/docs/guides/speech-to-text/prompting) should be in English.  (optional)
     * @param responseFormat The format of the transcript output, in one of these options: &#x60;json&#x60;, &#x60;text&#x60;, &#x60;srt&#x60;, &#x60;verbose_json&#x60;, or &#x60;vtt&#x60;.  (optional, default to json)
     * @param temperature The sampling temperature, between 0 and 1. Higher values like 0.8 will make the output more random, while lower values like 0.2 will make it more focused and deterministic. If set to 0, the model will use [log probability](https://en.wikipedia.org/wiki/Log_probability) to automatically increase the temperature until certain thresholds are hit.  (optional, default to 0)
     * @return ResponseEntity&lt;CreateTranslationResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CreateTranslationResponse> createTranslationWithHttpInfo(File _file, CreateTranscriptionRequestModel model, String prompt, String responseFormat, BigDecimal temperature) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter '_file' is set
        if (_file == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter '_file' when calling createTranslation");
        }
        
        // verify the required parameter 'model' is set
        if (model == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'model' when calling createTranslation");
        }
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        if (_file != null)
            localVarFormParams.add("file", new FileSystemResource(_file));
        if (model != null)
            localVarFormParams.add("model", model);
        if (prompt != null)
            localVarFormParams.add("prompt", prompt);
        if (responseFormat != null)
            localVarFormParams.add("response_format", responseFormat);
        if (temperature != null)
            localVarFormParams.add("temperature", temperature);

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "multipart/form-data"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };

        ParameterizedTypeReference<CreateTranslationResponse> localReturnType = new ParameterizedTypeReference<CreateTranslationResponse>() {};
        return apiClient.invokeAPI("/audio/translations", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
}
