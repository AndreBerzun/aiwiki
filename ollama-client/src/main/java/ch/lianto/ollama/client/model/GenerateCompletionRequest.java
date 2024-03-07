/*
 * Ollama API
 * API Spec for Ollama API. Please see https://github.com/jmorganca/ollama/blob/main/docs/api.md for more details.
 *
 * The version of the OpenAPI document: 0.1.9
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package ch.lianto.ollama.client.model;

import java.util.Objects;
import java.util.Arrays;
import ch.lianto.ollama.client.model.RequestOptions;
import ch.lianto.ollama.client.model.ResponseFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Request class for the generate endpoint.
 */
@JsonPropertyOrder({
  GenerateCompletionRequest.JSON_PROPERTY_MODEL,
  GenerateCompletionRequest.JSON_PROPERTY_PROMPT,
  GenerateCompletionRequest.JSON_PROPERTY_IMAGES,
  GenerateCompletionRequest.JSON_PROPERTY_SYSTEM,
  GenerateCompletionRequest.JSON_PROPERTY_TEMPLATE,
  GenerateCompletionRequest.JSON_PROPERTY_CONTEXT,
  GenerateCompletionRequest.JSON_PROPERTY_OPTIONS,
  GenerateCompletionRequest.JSON_PROPERTY_FORMAT,
  GenerateCompletionRequest.JSON_PROPERTY_RAW,
  GenerateCompletionRequest.JSON_PROPERTY_STREAM
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-03-06T00:48:30.515751098+01:00[Europe/Zurich]")
public class GenerateCompletionRequest {
  public static final String JSON_PROPERTY_MODEL = "model";
  private String model;

  public static final String JSON_PROPERTY_PROMPT = "prompt";
  private String prompt;

  public static final String JSON_PROPERTY_IMAGES = "images";
  private List<String> images;

  public static final String JSON_PROPERTY_SYSTEM = "system";
  private String system;

  public static final String JSON_PROPERTY_TEMPLATE = "template";
  private String template;

  public static final String JSON_PROPERTY_CONTEXT = "context";
  private List<Integer> context;

  public static final String JSON_PROPERTY_OPTIONS = "options";
  private RequestOptions options;

  public static final String JSON_PROPERTY_FORMAT = "format";
  private ResponseFormat format;

  public static final String JSON_PROPERTY_RAW = "raw";
  private Boolean raw;

  public static final String JSON_PROPERTY_STREAM = "stream";
  private Boolean stream = false;

  public GenerateCompletionRequest() {
  }

  public GenerateCompletionRequest model(String model) {
    
    this.model = model;
    return this;
  }

   /**
   * The model name.   Model names follow a &#x60;model:tag&#x60; format. Some examples are &#x60;orca-mini:3b-q4_1&#x60; and &#x60;llama2:70b&#x60;. The tag is optional and, if not provided, will default to &#x60;latest&#x60;. The tag is used to identify a specific version. 
   * @return model
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_MODEL)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getModel() {
    return model;
  }


  @JsonProperty(JSON_PROPERTY_MODEL)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setModel(String model) {
    this.model = model;
  }


  public GenerateCompletionRequest prompt(String prompt) {
    
    this.prompt = prompt;
    return this;
  }

   /**
   * The prompt to generate a response.
   * @return prompt
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_PROMPT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getPrompt() {
    return prompt;
  }


  @JsonProperty(JSON_PROPERTY_PROMPT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setPrompt(String prompt) {
    this.prompt = prompt;
  }


  public GenerateCompletionRequest images(List<String> images) {
    
    this.images = images;
    return this;
  }

  public GenerateCompletionRequest addImagesItem(String imagesItem) {
    if (this.images == null) {
      this.images = new ArrayList<>();
    }
    this.images.add(imagesItem);
    return this;
  }

   /**
   * (optional) a list of Base64-encoded images to include in the message (for multimodal models such as llava)
   * @return images
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_IMAGES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<String> getImages() {
    return images;
  }


  @JsonProperty(JSON_PROPERTY_IMAGES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setImages(List<String> images) {
    this.images = images;
  }


  public GenerateCompletionRequest system(String system) {
    
    this.system = system;
    return this;
  }

   /**
   * The system prompt to (overrides what is defined in the Modelfile).
   * @return system
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_SYSTEM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getSystem() {
    return system;
  }


  @JsonProperty(JSON_PROPERTY_SYSTEM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSystem(String system) {
    this.system = system;
  }


  public GenerateCompletionRequest template(String template) {
    
    this.template = template;
    return this;
  }

   /**
   * The full prompt or prompt template (overrides what is defined in the Modelfile).
   * @return template
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TEMPLATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getTemplate() {
    return template;
  }


  @JsonProperty(JSON_PROPERTY_TEMPLATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTemplate(String template) {
    this.template = template;
  }


  public GenerateCompletionRequest context(List<Integer> context) {
    
    this.context = context;
    return this;
  }

  public GenerateCompletionRequest addContextItem(Integer contextItem) {
    if (this.context == null) {
      this.context = new ArrayList<>();
    }
    this.context.add(contextItem);
    return this;
  }

   /**
   * The context parameter returned from a previous request to [generateCompletion], this can be used to keep a short conversational memory.
   * @return context
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CONTEXT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<Integer> getContext() {
    return context;
  }


  @JsonProperty(JSON_PROPERTY_CONTEXT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setContext(List<Integer> context) {
    this.context = context;
  }


  public GenerateCompletionRequest options(RequestOptions options) {
    
    this.options = options;
    return this;
  }

   /**
   * Get options
   * @return options
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_OPTIONS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public RequestOptions getOptions() {
    return options;
  }


  @JsonProperty(JSON_PROPERTY_OPTIONS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setOptions(RequestOptions options) {
    this.options = options;
  }


  public GenerateCompletionRequest format(ResponseFormat format) {
    
    this.format = format;
    return this;
  }

   /**
   * Get format
   * @return format
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_FORMAT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public ResponseFormat getFormat() {
    return format;
  }


  @JsonProperty(JSON_PROPERTY_FORMAT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setFormat(ResponseFormat format) {
    this.format = format;
  }


  public GenerateCompletionRequest raw(Boolean raw) {
    
    this.raw = raw;
    return this;
  }

   /**
   * If &#x60;true&#x60; no formatting will be applied to the prompt and no context will be returned.   You may choose to use the &#x60;raw&#x60; parameter if you are specifying a full templated prompt in your request to the API, and are managing history yourself. 
   * @return raw
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_RAW)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getRaw() {
    return raw;
  }


  @JsonProperty(JSON_PROPERTY_RAW)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setRaw(Boolean raw) {
    this.raw = raw;
  }


  public GenerateCompletionRequest stream(Boolean stream) {
    
    this.stream = stream;
    return this;
  }

   /**
   * If &#x60;false&#x60; the response will be returned as a single response object, otherwise the response will be streamed as a series of objects. 
   * @return stream
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_STREAM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getStream() {
    return stream;
  }


  @JsonProperty(JSON_PROPERTY_STREAM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setStream(Boolean stream) {
    this.stream = stream;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GenerateCompletionRequest generateCompletionRequest = (GenerateCompletionRequest) o;
    return Objects.equals(this.model, generateCompletionRequest.model) &&
        Objects.equals(this.prompt, generateCompletionRequest.prompt) &&
        Objects.equals(this.images, generateCompletionRequest.images) &&
        Objects.equals(this.system, generateCompletionRequest.system) &&
        Objects.equals(this.template, generateCompletionRequest.template) &&
        Objects.equals(this.context, generateCompletionRequest.context) &&
        Objects.equals(this.options, generateCompletionRequest.options) &&
        Objects.equals(this.format, generateCompletionRequest.format) &&
        Objects.equals(this.raw, generateCompletionRequest.raw) &&
        Objects.equals(this.stream, generateCompletionRequest.stream);
  }

  @Override
  public int hashCode() {
    return Objects.hash(model, prompt, images, system, template, context, options, format, raw, stream);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GenerateCompletionRequest {\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    prompt: ").append(toIndentedString(prompt)).append("\n");
    sb.append("    images: ").append(toIndentedString(images)).append("\n");
    sb.append("    system: ").append(toIndentedString(system)).append("\n");
    sb.append("    template: ").append(toIndentedString(template)).append("\n");
    sb.append("    context: ").append(toIndentedString(context)).append("\n");
    sb.append("    options: ").append(toIndentedString(options)).append("\n");
    sb.append("    format: ").append(toIndentedString(format)).append("\n");
    sb.append("    raw: ").append(toIndentedString(raw)).append("\n");
    sb.append("    stream: ").append(toIndentedString(stream)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

