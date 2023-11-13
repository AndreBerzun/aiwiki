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


package ch.lianto.openai.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * An object specifying the format that the model must output. Used to enable JSON mode.
 */
@JsonPropertyOrder({
  CreateChatCompletionRequestResponseFormat.JSON_PROPERTY_TYPE
})
@JsonTypeName("CreateChatCompletionRequest_response_format")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-13T00:24:13.926778838+01:00[Europe/Zurich]")
public class CreateChatCompletionRequestResponseFormat {
  /**
   * Setting to &#x60;json_object&#x60; enables JSON mode. This guarantees that the message the model generates is valid JSON.   Note that your system prompt must still instruct the model to produce JSON, and to help ensure you don&#39;t forget, the API will throw an error if the string &#x60;JSON&#x60; does not appear in your system message. Also note that the message content may be partial (i.e. cut off) if &#x60;finish_reason&#x3D;\&quot;length\&quot;&#x60;, which indicates the generation exceeded &#x60;max_tokens&#x60; or the conversation exceeded the max context length.   Must be one of &#x60;text&#x60; or &#x60;json_object&#x60;. 
   */
  public enum TypeEnum {
    TEXT("text"),
    
    JSON_OBJECT("json_object");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_TYPE = "type";
  private TypeEnum type = TypeEnum.TEXT;

  public CreateChatCompletionRequestResponseFormat() {
  }

  public CreateChatCompletionRequestResponseFormat type(TypeEnum type) {
    
    this.type = type;
    return this;
  }

   /**
   * Setting to &#x60;json_object&#x60; enables JSON mode. This guarantees that the message the model generates is valid JSON.   Note that your system prompt must still instruct the model to produce JSON, and to help ensure you don&#39;t forget, the API will throw an error if the string &#x60;JSON&#x60; does not appear in your system message. Also note that the message content may be partial (i.e. cut off) if &#x60;finish_reason&#x3D;\&quot;length\&quot;&#x60;, which indicates the generation exceeded &#x60;max_tokens&#x60; or the conversation exceeded the max context length.   Must be one of &#x60;text&#x60; or &#x60;json_object&#x60;. 
   * @return type
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public TypeEnum getType() {
    return type;
  }


  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setType(TypeEnum type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateChatCompletionRequestResponseFormat createChatCompletionRequestResponseFormat = (CreateChatCompletionRequestResponseFormat) o;
    return Objects.equals(this.type, createChatCompletionRequestResponseFormat.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateChatCompletionRequestResponseFormat {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

