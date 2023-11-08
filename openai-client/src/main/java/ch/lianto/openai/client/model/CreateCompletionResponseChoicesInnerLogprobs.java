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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * CreateCompletionResponseChoicesInnerLogprobs
 */
@JsonPropertyOrder({
  CreateCompletionResponseChoicesInnerLogprobs.JSON_PROPERTY_TEXT_OFFSET,
  CreateCompletionResponseChoicesInnerLogprobs.JSON_PROPERTY_TOKEN_LOGPROBS,
  CreateCompletionResponseChoicesInnerLogprobs.JSON_PROPERTY_TOKENS,
  CreateCompletionResponseChoicesInnerLogprobs.JSON_PROPERTY_TOP_LOGPROBS
})
@JsonTypeName("CreateCompletionResponse_choices_inner_logprobs")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-08T23:05:52.228713843+01:00[Europe/Zurich]")
public class CreateCompletionResponseChoicesInnerLogprobs {
  public static final String JSON_PROPERTY_TEXT_OFFSET = "text_offset";
  private List<Integer> textOffset;

  public static final String JSON_PROPERTY_TOKEN_LOGPROBS = "token_logprobs";
  private List<BigDecimal> tokenLogprobs;

  public static final String JSON_PROPERTY_TOKENS = "tokens";
  private List<String> tokens;

  public static final String JSON_PROPERTY_TOP_LOGPROBS = "top_logprobs";
  private List<Map<String, Integer>> topLogprobs;

  public CreateCompletionResponseChoicesInnerLogprobs() {
  }

  public CreateCompletionResponseChoicesInnerLogprobs textOffset(List<Integer> textOffset) {
    
    this.textOffset = textOffset;
    return this;
  }

  public CreateCompletionResponseChoicesInnerLogprobs addTextOffsetItem(Integer textOffsetItem) {
    if (this.textOffset == null) {
      this.textOffset = new ArrayList<>();
    }
    this.textOffset.add(textOffsetItem);
    return this;
  }

   /**
   * Get textOffset
   * @return textOffset
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TEXT_OFFSET)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<Integer> getTextOffset() {
    return textOffset;
  }


  @JsonProperty(JSON_PROPERTY_TEXT_OFFSET)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTextOffset(List<Integer> textOffset) {
    this.textOffset = textOffset;
  }


  public CreateCompletionResponseChoicesInnerLogprobs tokenLogprobs(List<BigDecimal> tokenLogprobs) {
    
    this.tokenLogprobs = tokenLogprobs;
    return this;
  }

  public CreateCompletionResponseChoicesInnerLogprobs addTokenLogprobsItem(BigDecimal tokenLogprobsItem) {
    if (this.tokenLogprobs == null) {
      this.tokenLogprobs = new ArrayList<>();
    }
    this.tokenLogprobs.add(tokenLogprobsItem);
    return this;
  }

   /**
   * Get tokenLogprobs
   * @return tokenLogprobs
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TOKEN_LOGPROBS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<BigDecimal> getTokenLogprobs() {
    return tokenLogprobs;
  }


  @JsonProperty(JSON_PROPERTY_TOKEN_LOGPROBS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTokenLogprobs(List<BigDecimal> tokenLogprobs) {
    this.tokenLogprobs = tokenLogprobs;
  }


  public CreateCompletionResponseChoicesInnerLogprobs tokens(List<String> tokens) {
    
    this.tokens = tokens;
    return this;
  }

  public CreateCompletionResponseChoicesInnerLogprobs addTokensItem(String tokensItem) {
    if (this.tokens == null) {
      this.tokens = new ArrayList<>();
    }
    this.tokens.add(tokensItem);
    return this;
  }

   /**
   * Get tokens
   * @return tokens
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TOKENS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<String> getTokens() {
    return tokens;
  }


  @JsonProperty(JSON_PROPERTY_TOKENS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTokens(List<String> tokens) {
    this.tokens = tokens;
  }


  public CreateCompletionResponseChoicesInnerLogprobs topLogprobs(List<Map<String, Integer>> topLogprobs) {
    
    this.topLogprobs = topLogprobs;
    return this;
  }

  public CreateCompletionResponseChoicesInnerLogprobs addTopLogprobsItem(Map<String, Integer> topLogprobsItem) {
    if (this.topLogprobs == null) {
      this.topLogprobs = new ArrayList<>();
    }
    this.topLogprobs.add(topLogprobsItem);
    return this;
  }

   /**
   * Get topLogprobs
   * @return topLogprobs
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TOP_LOGPROBS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<Map<String, Integer>> getTopLogprobs() {
    return topLogprobs;
  }


  @JsonProperty(JSON_PROPERTY_TOP_LOGPROBS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTopLogprobs(List<Map<String, Integer>> topLogprobs) {
    this.topLogprobs = topLogprobs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateCompletionResponseChoicesInnerLogprobs createCompletionResponseChoicesInnerLogprobs = (CreateCompletionResponseChoicesInnerLogprobs) o;
    return Objects.equals(this.textOffset, createCompletionResponseChoicesInnerLogprobs.textOffset) &&
        Objects.equals(this.tokenLogprobs, createCompletionResponseChoicesInnerLogprobs.tokenLogprobs) &&
        Objects.equals(this.tokens, createCompletionResponseChoicesInnerLogprobs.tokens) &&
        Objects.equals(this.topLogprobs, createCompletionResponseChoicesInnerLogprobs.topLogprobs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(textOffset, tokenLogprobs, tokens, topLogprobs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateCompletionResponseChoicesInnerLogprobs {\n");
    sb.append("    textOffset: ").append(toIndentedString(textOffset)).append("\n");
    sb.append("    tokenLogprobs: ").append(toIndentedString(tokenLogprobs)).append("\n");
    sb.append("    tokens: ").append(toIndentedString(tokens)).append("\n");
    sb.append("    topLogprobs: ").append(toIndentedString(topLogprobs)).append("\n");
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

