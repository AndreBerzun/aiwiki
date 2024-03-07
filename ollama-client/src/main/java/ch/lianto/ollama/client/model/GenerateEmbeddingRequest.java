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
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Generate embeddings from a model.
 */
@JsonPropertyOrder({
  GenerateEmbeddingRequest.JSON_PROPERTY_MODEL,
  GenerateEmbeddingRequest.JSON_PROPERTY_PROMPT,
  GenerateEmbeddingRequest.JSON_PROPERTY_OPTIONS
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-03-06T00:48:30.515751098+01:00[Europe/Zurich]")
public class GenerateEmbeddingRequest {
  public static final String JSON_PROPERTY_MODEL = "model";
  private String model;

  public static final String JSON_PROPERTY_PROMPT = "prompt";
  private String prompt;

  public static final String JSON_PROPERTY_OPTIONS = "options";
  private RequestOptions options;

  public GenerateEmbeddingRequest() {
  }

  public GenerateEmbeddingRequest model(String model) {
    
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


  public GenerateEmbeddingRequest prompt(String prompt) {
    
    this.prompt = prompt;
    return this;
  }

   /**
   * Text to generate embeddings for.
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


  public GenerateEmbeddingRequest options(RequestOptions options) {
    
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GenerateEmbeddingRequest generateEmbeddingRequest = (GenerateEmbeddingRequest) o;
    return Objects.equals(this.model, generateEmbeddingRequest.model) &&
        Objects.equals(this.prompt, generateEmbeddingRequest.prompt) &&
        Objects.equals(this.options, generateEmbeddingRequest.options);
  }

  @Override
  public int hashCode() {
    return Objects.hash(model, prompt, options);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GenerateEmbeddingRequest {\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    prompt: ").append(toIndentedString(prompt)).append("\n");
    sb.append("    options: ").append(toIndentedString(options)).append("\n");
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

