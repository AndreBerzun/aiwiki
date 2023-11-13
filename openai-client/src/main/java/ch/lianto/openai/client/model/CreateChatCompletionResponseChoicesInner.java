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
import ch.lianto.openai.client.model.ChatCompletionResponseMessage;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * CreateChatCompletionResponseChoicesInner
 */
@JsonPropertyOrder({
  CreateChatCompletionResponseChoicesInner.JSON_PROPERTY_FINISH_REASON,
  CreateChatCompletionResponseChoicesInner.JSON_PROPERTY_INDEX,
  CreateChatCompletionResponseChoicesInner.JSON_PROPERTY_MESSAGE
})
@JsonTypeName("CreateChatCompletionResponse_choices_inner")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-13T00:24:13.926778838+01:00[Europe/Zurich]")
public class CreateChatCompletionResponseChoicesInner {
  /**
   * The reason the model stopped generating tokens. This will be &#x60;stop&#x60; if the model hit a natural stop point or a provided stop sequence, &#x60;length&#x60; if the maximum number of tokens specified in the request was reached, &#x60;content_filter&#x60; if content was omitted due to a flag from our content filters, &#x60;tool_calls&#x60; if the model called a tool, or &#x60;function_call&#x60; (deprecated) if the model called a function. 
   */
  public enum FinishReasonEnum {
    STOP("stop"),
    
    LENGTH("length"),
    
    TOOL_CALLS("tool_calls"),
    
    CONTENT_FILTER("content_filter"),
    
    FUNCTION_CALL("function_call");

    private String value;

    FinishReasonEnum(String value) {
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
    public static FinishReasonEnum fromValue(String value) {
      for (FinishReasonEnum b : FinishReasonEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_FINISH_REASON = "finish_reason";
  private FinishReasonEnum finishReason;

  public static final String JSON_PROPERTY_INDEX = "index";
  private Integer index;

  public static final String JSON_PROPERTY_MESSAGE = "message";
  private ChatCompletionResponseMessage message;

  public CreateChatCompletionResponseChoicesInner() {
  }

  public CreateChatCompletionResponseChoicesInner finishReason(FinishReasonEnum finishReason) {
    
    this.finishReason = finishReason;
    return this;
  }

   /**
   * The reason the model stopped generating tokens. This will be &#x60;stop&#x60; if the model hit a natural stop point or a provided stop sequence, &#x60;length&#x60; if the maximum number of tokens specified in the request was reached, &#x60;content_filter&#x60; if content was omitted due to a flag from our content filters, &#x60;tool_calls&#x60; if the model called a tool, or &#x60;function_call&#x60; (deprecated) if the model called a function. 
   * @return finishReason
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_FINISH_REASON)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public FinishReasonEnum getFinishReason() {
    return finishReason;
  }


  @JsonProperty(JSON_PROPERTY_FINISH_REASON)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setFinishReason(FinishReasonEnum finishReason) {
    this.finishReason = finishReason;
  }


  public CreateChatCompletionResponseChoicesInner index(Integer index) {
    
    this.index = index;
    return this;
  }

   /**
   * The index of the choice in the list of choices.
   * @return index
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_INDEX)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Integer getIndex() {
    return index;
  }


  @JsonProperty(JSON_PROPERTY_INDEX)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setIndex(Integer index) {
    this.index = index;
  }


  public CreateChatCompletionResponseChoicesInner message(ChatCompletionResponseMessage message) {
    
    this.message = message;
    return this;
  }

   /**
   * Get message
   * @return message
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_MESSAGE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public ChatCompletionResponseMessage getMessage() {
    return message;
  }


  @JsonProperty(JSON_PROPERTY_MESSAGE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setMessage(ChatCompletionResponseMessage message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateChatCompletionResponseChoicesInner createChatCompletionResponseChoicesInner = (CreateChatCompletionResponseChoicesInner) o;
    return Objects.equals(this.finishReason, createChatCompletionResponseChoicesInner.finishReason) &&
        Objects.equals(this.index, createChatCompletionResponseChoicesInner.index) &&
        Objects.equals(this.message, createChatCompletionResponseChoicesInner.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(finishReason, index, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateChatCompletionResponseChoicesInner {\n");
    sb.append("    finishReason: ").append(toIndentedString(finishReason)).append("\n");
    sb.append("    index: ").append(toIndentedString(index)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

