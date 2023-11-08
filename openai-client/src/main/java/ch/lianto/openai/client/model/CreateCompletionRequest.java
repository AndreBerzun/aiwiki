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
import ch.lianto.openai.client.model.CreateCompletionRequestModel;
import ch.lianto.openai.client.model.CreateCompletionRequestPrompt;
import ch.lianto.openai.client.model.CreateCompletionRequestStop;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.openapitools.jackson.nullable.JsonNullable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.openapitools.jackson.nullable.JsonNullable;
import java.util.NoSuchElementException;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * CreateCompletionRequest
 */
@JsonPropertyOrder({
  CreateCompletionRequest.JSON_PROPERTY_MODEL,
  CreateCompletionRequest.JSON_PROPERTY_PROMPT,
  CreateCompletionRequest.JSON_PROPERTY_BEST_OF,
  CreateCompletionRequest.JSON_PROPERTY_ECHO,
  CreateCompletionRequest.JSON_PROPERTY_FREQUENCY_PENALTY,
  CreateCompletionRequest.JSON_PROPERTY_LOGIT_BIAS,
  CreateCompletionRequest.JSON_PROPERTY_LOGPROBS,
  CreateCompletionRequest.JSON_PROPERTY_MAX_TOKENS,
  CreateCompletionRequest.JSON_PROPERTY_N,
  CreateCompletionRequest.JSON_PROPERTY_PRESENCE_PENALTY,
  CreateCompletionRequest.JSON_PROPERTY_SEED,
  CreateCompletionRequest.JSON_PROPERTY_STOP,
  CreateCompletionRequest.JSON_PROPERTY_STREAM,
  CreateCompletionRequest.JSON_PROPERTY_SUFFIX,
  CreateCompletionRequest.JSON_PROPERTY_TEMPERATURE,
  CreateCompletionRequest.JSON_PROPERTY_TOP_P,
  CreateCompletionRequest.JSON_PROPERTY_USER
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-08T23:05:52.228713843+01:00[Europe/Zurich]")
public class CreateCompletionRequest {
  public static final String JSON_PROPERTY_MODEL = "model";
  private CreateCompletionRequestModel model;

  public static final String JSON_PROPERTY_PROMPT = "prompt";
  private CreateCompletionRequestPrompt prompt = null;

  public static final String JSON_PROPERTY_BEST_OF = "best_of";
  private JsonNullable<Integer> bestOf = JsonNullable.<Integer>of(1);

  public static final String JSON_PROPERTY_ECHO = "echo";
  private JsonNullable<Boolean> echo = JsonNullable.<Boolean>of(false);

  public static final String JSON_PROPERTY_FREQUENCY_PENALTY = "frequency_penalty";
  private JsonNullable<BigDecimal> frequencyPenalty = JsonNullable.<BigDecimal>of(new BigDecimal("0"));

  public static final String JSON_PROPERTY_LOGIT_BIAS = "logit_bias";
  private JsonNullable<Map<String, Integer>> logitBias = JsonNullable.<Map<String, Integer>>undefined();

  public static final String JSON_PROPERTY_LOGPROBS = "logprobs";
  private JsonNullable<Integer> logprobs = JsonNullable.<Integer>undefined();

  public static final String JSON_PROPERTY_MAX_TOKENS = "max_tokens";
  private JsonNullable<Integer> maxTokens = JsonNullable.<Integer>of(16);

  public static final String JSON_PROPERTY_N = "n";
  private JsonNullable<Integer> n = JsonNullable.<Integer>of(1);

  public static final String JSON_PROPERTY_PRESENCE_PENALTY = "presence_penalty";
  private JsonNullable<BigDecimal> presencePenalty = JsonNullable.<BigDecimal>of(new BigDecimal("0"));

  public static final String JSON_PROPERTY_SEED = "seed";
  private JsonNullable<Integer> seed = JsonNullable.<Integer>undefined();

  public static final String JSON_PROPERTY_STOP = "stop";
  private JsonNullable<CreateCompletionRequestStop> stop = JsonNullable.<CreateCompletionRequestStop>of(null);

  public static final String JSON_PROPERTY_STREAM = "stream";
  private JsonNullable<Boolean> stream = JsonNullable.<Boolean>of(false);

  public static final String JSON_PROPERTY_SUFFIX = "suffix";
  private JsonNullable<String> suffix = JsonNullable.<String>undefined();

  public static final String JSON_PROPERTY_TEMPERATURE = "temperature";
  private JsonNullable<BigDecimal> temperature = JsonNullable.<BigDecimal>of(new BigDecimal("1"));

  public static final String JSON_PROPERTY_TOP_P = "top_p";
  private JsonNullable<BigDecimal> topP = JsonNullable.<BigDecimal>of(new BigDecimal("1"));

  public static final String JSON_PROPERTY_USER = "user";
  private String user;

  public CreateCompletionRequest() {
  }

  public CreateCompletionRequest model(CreateCompletionRequestModel model) {
    
    this.model = model;
    return this;
  }

   /**
   * Get model
   * @return model
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_MODEL)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public CreateCompletionRequestModel getModel() {
    return model;
  }


  @JsonProperty(JSON_PROPERTY_MODEL)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setModel(CreateCompletionRequestModel model) {
    this.model = model;
  }


  public CreateCompletionRequest prompt(CreateCompletionRequestPrompt prompt) {
    
    this.prompt = prompt;
    return this;
  }

   /**
   * Get prompt
   * @return prompt
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_PROMPT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public CreateCompletionRequestPrompt getPrompt() {
    return prompt;
  }


  @JsonProperty(JSON_PROPERTY_PROMPT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setPrompt(CreateCompletionRequestPrompt prompt) {
    this.prompt = prompt;
  }


  public CreateCompletionRequest bestOf(Integer bestOf) {
    this.bestOf = JsonNullable.<Integer>of(bestOf);
    
    return this;
  }

   /**
   * Generates &#x60;best_of&#x60; completions server-side and returns the \&quot;best\&quot; (the one with the highest log probability per token). Results cannot be streamed.  When used with &#x60;n&#x60;, &#x60;best_of&#x60; controls the number of candidate completions and &#x60;n&#x60; specifies how many to return – &#x60;best_of&#x60; must be greater than &#x60;n&#x60;.  **Note:** Because this parameter generates many completions, it can quickly consume your token quota. Use carefully and ensure that you have reasonable settings for &#x60;max_tokens&#x60; and &#x60;stop&#x60;. 
   * minimum: 0
   * maximum: 20
   * @return bestOf
  **/
  @javax.annotation.Nullable
  @JsonIgnore

  public Integer getBestOf() {
        return bestOf.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_BEST_OF)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public JsonNullable<Integer> getBestOf_JsonNullable() {
    return bestOf;
  }
  
  @JsonProperty(JSON_PROPERTY_BEST_OF)
  public void setBestOf_JsonNullable(JsonNullable<Integer> bestOf) {
    this.bestOf = bestOf;
  }

  public void setBestOf(Integer bestOf) {
    this.bestOf = JsonNullable.<Integer>of(bestOf);
  }


  public CreateCompletionRequest echo(Boolean echo) {
    this.echo = JsonNullable.<Boolean>of(echo);
    
    return this;
  }

   /**
   * Echo back the prompt in addition to the completion 
   * @return echo
  **/
  @javax.annotation.Nullable
  @JsonIgnore

  public Boolean getEcho() {
        return echo.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_ECHO)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public JsonNullable<Boolean> getEcho_JsonNullable() {
    return echo;
  }
  
  @JsonProperty(JSON_PROPERTY_ECHO)
  public void setEcho_JsonNullable(JsonNullable<Boolean> echo) {
    this.echo = echo;
  }

  public void setEcho(Boolean echo) {
    this.echo = JsonNullable.<Boolean>of(echo);
  }


  public CreateCompletionRequest frequencyPenalty(BigDecimal frequencyPenalty) {
    this.frequencyPenalty = JsonNullable.<BigDecimal>of(frequencyPenalty);
    
    return this;
  }

   /**
   * Number between -2.0 and 2.0. Positive values penalize new tokens based on their existing frequency in the text so far, decreasing the model&#39;s likelihood to repeat the same line verbatim.  [See more information about frequency and presence penalties.](/docs/guides/gpt/parameter-details) 
   * minimum: -2
   * maximum: 2
   * @return frequencyPenalty
  **/
  @javax.annotation.Nullable
  @JsonIgnore

  public BigDecimal getFrequencyPenalty() {
        return frequencyPenalty.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_FREQUENCY_PENALTY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public JsonNullable<BigDecimal> getFrequencyPenalty_JsonNullable() {
    return frequencyPenalty;
  }
  
  @JsonProperty(JSON_PROPERTY_FREQUENCY_PENALTY)
  public void setFrequencyPenalty_JsonNullable(JsonNullable<BigDecimal> frequencyPenalty) {
    this.frequencyPenalty = frequencyPenalty;
  }

  public void setFrequencyPenalty(BigDecimal frequencyPenalty) {
    this.frequencyPenalty = JsonNullable.<BigDecimal>of(frequencyPenalty);
  }


  public CreateCompletionRequest logitBias(Map<String, Integer> logitBias) {
    this.logitBias = JsonNullable.<Map<String, Integer>>of(logitBias);
    
    return this;
  }

  public CreateCompletionRequest putLogitBiasItem(String key, Integer logitBiasItem) {
    if (this.logitBias == null || !this.logitBias.isPresent()) {
      this.logitBias = JsonNullable.<Map<String, Integer>>of(new HashMap<>());
    }
    try {
      this.logitBias.get().put(key, logitBiasItem);
    } catch (java.util.NoSuchElementException e) {
      // this can never happen, as we make sure above that the value is present
    }
    return this;
  }

   /**
   * Modify the likelihood of specified tokens appearing in the completion.  Accepts a JSON object that maps tokens (specified by their token ID in the GPT tokenizer) to an associated bias value from -100 to 100. You can use this [tokenizer tool](/tokenizer?view&#x3D;bpe) (which works for both GPT-2 and GPT-3) to convert text to token IDs. Mathematically, the bias is added to the logits generated by the model prior to sampling. The exact effect will vary per model, but values between -1 and 1 should decrease or increase likelihood of selection; values like -100 or 100 should result in a ban or exclusive selection of the relevant token.  As an example, you can pass &#x60;{\&quot;50256\&quot;: -100}&#x60; to prevent the &lt;|endoftext|&gt; token from being generated. 
   * @return logitBias
  **/
  @javax.annotation.Nullable
  @JsonIgnore

  public Map<String, Integer> getLogitBias() {
        return logitBias.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_LOGIT_BIAS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public JsonNullable<Map<String, Integer>> getLogitBias_JsonNullable() {
    return logitBias;
  }
  
  @JsonProperty(JSON_PROPERTY_LOGIT_BIAS)
  public void setLogitBias_JsonNullable(JsonNullable<Map<String, Integer>> logitBias) {
    this.logitBias = logitBias;
  }

  public void setLogitBias(Map<String, Integer> logitBias) {
    this.logitBias = JsonNullable.<Map<String, Integer>>of(logitBias);
  }


  public CreateCompletionRequest logprobs(Integer logprobs) {
    this.logprobs = JsonNullable.<Integer>of(logprobs);
    
    return this;
  }

   /**
   * Include the log probabilities on the &#x60;logprobs&#x60; most likely tokens, as well the chosen tokens. For example, if &#x60;logprobs&#x60; is 5, the API will return a list of the 5 most likely tokens. The API will always return the &#x60;logprob&#x60; of the sampled token, so there may be up to &#x60;logprobs+1&#x60; elements in the response.  The maximum value for &#x60;logprobs&#x60; is 5. 
   * minimum: 0
   * maximum: 5
   * @return logprobs
  **/
  @javax.annotation.Nullable
  @JsonIgnore

  public Integer getLogprobs() {
        return logprobs.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_LOGPROBS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public JsonNullable<Integer> getLogprobs_JsonNullable() {
    return logprobs;
  }
  
  @JsonProperty(JSON_PROPERTY_LOGPROBS)
  public void setLogprobs_JsonNullable(JsonNullable<Integer> logprobs) {
    this.logprobs = logprobs;
  }

  public void setLogprobs(Integer logprobs) {
    this.logprobs = JsonNullable.<Integer>of(logprobs);
  }


  public CreateCompletionRequest maxTokens(Integer maxTokens) {
    this.maxTokens = JsonNullable.<Integer>of(maxTokens);
    
    return this;
  }

   /**
   * The maximum number of [tokens](/tokenizer) to generate in the completion.  The token count of your prompt plus &#x60;max_tokens&#x60; cannot exceed the model&#39;s context length. [Example Python code](https://cookbook.openai.com/examples/how_to_count_tokens_with_tiktoken) for counting tokens. 
   * minimum: 0
   * @return maxTokens
  **/
  @javax.annotation.Nullable
  @JsonIgnore

  public Integer getMaxTokens() {
        return maxTokens.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_MAX_TOKENS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public JsonNullable<Integer> getMaxTokens_JsonNullable() {
    return maxTokens;
  }
  
  @JsonProperty(JSON_PROPERTY_MAX_TOKENS)
  public void setMaxTokens_JsonNullable(JsonNullable<Integer> maxTokens) {
    this.maxTokens = maxTokens;
  }

  public void setMaxTokens(Integer maxTokens) {
    this.maxTokens = JsonNullable.<Integer>of(maxTokens);
  }


  public CreateCompletionRequest n(Integer n) {
    this.n = JsonNullable.<Integer>of(n);
    
    return this;
  }

   /**
   * How many completions to generate for each prompt.  **Note:** Because this parameter generates many completions, it can quickly consume your token quota. Use carefully and ensure that you have reasonable settings for &#x60;max_tokens&#x60; and &#x60;stop&#x60;. 
   * minimum: 1
   * maximum: 128
   * @return n
  **/
  @javax.annotation.Nullable
  @JsonIgnore

  public Integer getN() {
        return n.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_N)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public JsonNullable<Integer> getN_JsonNullable() {
    return n;
  }
  
  @JsonProperty(JSON_PROPERTY_N)
  public void setN_JsonNullable(JsonNullable<Integer> n) {
    this.n = n;
  }

  public void setN(Integer n) {
    this.n = JsonNullable.<Integer>of(n);
  }


  public CreateCompletionRequest presencePenalty(BigDecimal presencePenalty) {
    this.presencePenalty = JsonNullable.<BigDecimal>of(presencePenalty);
    
    return this;
  }

   /**
   * Number between -2.0 and 2.0. Positive values penalize new tokens based on whether they appear in the text so far, increasing the model&#39;s likelihood to talk about new topics.  [See more information about frequency and presence penalties.](/docs/guides/gpt/parameter-details) 
   * minimum: -2
   * maximum: 2
   * @return presencePenalty
  **/
  @javax.annotation.Nullable
  @JsonIgnore

  public BigDecimal getPresencePenalty() {
        return presencePenalty.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_PRESENCE_PENALTY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public JsonNullable<BigDecimal> getPresencePenalty_JsonNullable() {
    return presencePenalty;
  }
  
  @JsonProperty(JSON_PROPERTY_PRESENCE_PENALTY)
  public void setPresencePenalty_JsonNullable(JsonNullable<BigDecimal> presencePenalty) {
    this.presencePenalty = presencePenalty;
  }

  public void setPresencePenalty(BigDecimal presencePenalty) {
    this.presencePenalty = JsonNullable.<BigDecimal>of(presencePenalty);
  }


  public CreateCompletionRequest seed(Integer seed) {
    this.seed = JsonNullable.<Integer>of(seed);
    
    return this;
  }

   /**
   * If specified, our system will make a best effort to sample deterministically, such that repeated requests with the same &#x60;seed&#x60; and parameters should return the same result.  Determinism is not guaranteed, and you should refer to the &#x60;system_fingerprint&#x60; response parameter to monitor changes in the backend. 
   * minimum: -9223372036854775808
   * maximum: 9223372036854775807
   * @return seed
  **/
  @javax.annotation.Nullable
  @JsonIgnore

  public Integer getSeed() {
        return seed.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_SEED)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public JsonNullable<Integer> getSeed_JsonNullable() {
    return seed;
  }
  
  @JsonProperty(JSON_PROPERTY_SEED)
  public void setSeed_JsonNullable(JsonNullable<Integer> seed) {
    this.seed = seed;
  }

  public void setSeed(Integer seed) {
    this.seed = JsonNullable.<Integer>of(seed);
  }


  public CreateCompletionRequest stop(CreateCompletionRequestStop stop) {
    this.stop = JsonNullable.<CreateCompletionRequestStop>of(stop);
    
    return this;
  }

   /**
   * Get stop
   * @return stop
  **/
  @javax.annotation.Nullable
  @JsonIgnore

  public CreateCompletionRequestStop getStop() {
        return stop.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_STOP)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public JsonNullable<CreateCompletionRequestStop> getStop_JsonNullable() {
    return stop;
  }
  
  @JsonProperty(JSON_PROPERTY_STOP)
  public void setStop_JsonNullable(JsonNullable<CreateCompletionRequestStop> stop) {
    this.stop = stop;
  }

  public void setStop(CreateCompletionRequestStop stop) {
    this.stop = JsonNullable.<CreateCompletionRequestStop>of(stop);
  }


  public CreateCompletionRequest stream(Boolean stream) {
    this.stream = JsonNullable.<Boolean>of(stream);
    
    return this;
  }

   /**
   * Whether to stream back partial progress. If set, tokens will be sent as data-only [server-sent events](https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events/Using_server-sent_events#Event_stream_format) as they become available, with the stream terminated by a &#x60;data: [DONE]&#x60; message. [Example Python code](https://cookbook.openai.com/examples/how_to_stream_completions). 
   * @return stream
  **/
  @javax.annotation.Nullable
  @JsonIgnore

  public Boolean getStream() {
        return stream.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_STREAM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public JsonNullable<Boolean> getStream_JsonNullable() {
    return stream;
  }
  
  @JsonProperty(JSON_PROPERTY_STREAM)
  public void setStream_JsonNullable(JsonNullable<Boolean> stream) {
    this.stream = stream;
  }

  public void setStream(Boolean stream) {
    this.stream = JsonNullable.<Boolean>of(stream);
  }


  public CreateCompletionRequest suffix(String suffix) {
    this.suffix = JsonNullable.<String>of(suffix);
    
    return this;
  }

   /**
   * The suffix that comes after a completion of inserted text.
   * @return suffix
  **/
  @javax.annotation.Nullable
  @JsonIgnore

  public String getSuffix() {
        return suffix.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_SUFFIX)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public JsonNullable<String> getSuffix_JsonNullable() {
    return suffix;
  }
  
  @JsonProperty(JSON_PROPERTY_SUFFIX)
  public void setSuffix_JsonNullable(JsonNullable<String> suffix) {
    this.suffix = suffix;
  }

  public void setSuffix(String suffix) {
    this.suffix = JsonNullable.<String>of(suffix);
  }


  public CreateCompletionRequest temperature(BigDecimal temperature) {
    this.temperature = JsonNullable.<BigDecimal>of(temperature);
    
    return this;
  }

   /**
   * What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the output more random, while lower values like 0.2 will make it more focused and deterministic.  We generally recommend altering this or &#x60;top_p&#x60; but not both. 
   * minimum: 0
   * maximum: 2
   * @return temperature
  **/
  @javax.annotation.Nullable
  @JsonIgnore

  public BigDecimal getTemperature() {
        return temperature.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_TEMPERATURE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public JsonNullable<BigDecimal> getTemperature_JsonNullable() {
    return temperature;
  }
  
  @JsonProperty(JSON_PROPERTY_TEMPERATURE)
  public void setTemperature_JsonNullable(JsonNullable<BigDecimal> temperature) {
    this.temperature = temperature;
  }

  public void setTemperature(BigDecimal temperature) {
    this.temperature = JsonNullable.<BigDecimal>of(temperature);
  }


  public CreateCompletionRequest topP(BigDecimal topP) {
    this.topP = JsonNullable.<BigDecimal>of(topP);
    
    return this;
  }

   /**
   * An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass are considered.  We generally recommend altering this or &#x60;temperature&#x60; but not both. 
   * minimum: 0
   * maximum: 1
   * @return topP
  **/
  @javax.annotation.Nullable
  @JsonIgnore

  public BigDecimal getTopP() {
        return topP.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_TOP_P)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public JsonNullable<BigDecimal> getTopP_JsonNullable() {
    return topP;
  }
  
  @JsonProperty(JSON_PROPERTY_TOP_P)
  public void setTopP_JsonNullable(JsonNullable<BigDecimal> topP) {
    this.topP = topP;
  }

  public void setTopP(BigDecimal topP) {
    this.topP = JsonNullable.<BigDecimal>of(topP);
  }


  public CreateCompletionRequest user(String user) {
    
    this.user = user;
    return this;
  }

   /**
   * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse. [Learn more](/docs/guides/safety-best-practices/end-user-ids). 
   * @return user
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_USER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getUser() {
    return user;
  }


  @JsonProperty(JSON_PROPERTY_USER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setUser(String user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateCompletionRequest createCompletionRequest = (CreateCompletionRequest) o;
    return Objects.equals(this.model, createCompletionRequest.model) &&
        Objects.equals(this.prompt, createCompletionRequest.prompt) &&
        equalsNullable(this.bestOf, createCompletionRequest.bestOf) &&
        equalsNullable(this.echo, createCompletionRequest.echo) &&
        equalsNullable(this.frequencyPenalty, createCompletionRequest.frequencyPenalty) &&
        equalsNullable(this.logitBias, createCompletionRequest.logitBias) &&
        equalsNullable(this.logprobs, createCompletionRequest.logprobs) &&
        equalsNullable(this.maxTokens, createCompletionRequest.maxTokens) &&
        equalsNullable(this.n, createCompletionRequest.n) &&
        equalsNullable(this.presencePenalty, createCompletionRequest.presencePenalty) &&
        equalsNullable(this.seed, createCompletionRequest.seed) &&
        equalsNullable(this.stop, createCompletionRequest.stop) &&
        equalsNullable(this.stream, createCompletionRequest.stream) &&
        equalsNullable(this.suffix, createCompletionRequest.suffix) &&
        equalsNullable(this.temperature, createCompletionRequest.temperature) &&
        equalsNullable(this.topP, createCompletionRequest.topP) &&
        Objects.equals(this.user, createCompletionRequest.user);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(model, prompt, hashCodeNullable(bestOf), hashCodeNullable(echo), hashCodeNullable(frequencyPenalty), hashCodeNullable(logitBias), hashCodeNullable(logprobs), hashCodeNullable(maxTokens), hashCodeNullable(n), hashCodeNullable(presencePenalty), hashCodeNullable(seed), hashCodeNullable(stop), hashCodeNullable(stream), hashCodeNullable(suffix), hashCodeNullable(temperature), hashCodeNullable(topP), user);
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateCompletionRequest {\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    prompt: ").append(toIndentedString(prompt)).append("\n");
    sb.append("    bestOf: ").append(toIndentedString(bestOf)).append("\n");
    sb.append("    echo: ").append(toIndentedString(echo)).append("\n");
    sb.append("    frequencyPenalty: ").append(toIndentedString(frequencyPenalty)).append("\n");
    sb.append("    logitBias: ").append(toIndentedString(logitBias)).append("\n");
    sb.append("    logprobs: ").append(toIndentedString(logprobs)).append("\n");
    sb.append("    maxTokens: ").append(toIndentedString(maxTokens)).append("\n");
    sb.append("    n: ").append(toIndentedString(n)).append("\n");
    sb.append("    presencePenalty: ").append(toIndentedString(presencePenalty)).append("\n");
    sb.append("    seed: ").append(toIndentedString(seed)).append("\n");
    sb.append("    stop: ").append(toIndentedString(stop)).append("\n");
    sb.append("    stream: ").append(toIndentedString(stream)).append("\n");
    sb.append("    suffix: ").append(toIndentedString(suffix)).append("\n");
    sb.append("    temperature: ").append(toIndentedString(temperature)).append("\n");
    sb.append("    topP: ").append(toIndentedString(topP)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
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

