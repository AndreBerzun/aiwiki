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
import ch.lianto.ollama.client.model.PullModelStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Response class for pulling a model.   The first object is the manifest. Then there is a series of downloading responses. Until any of the download is completed, the &#x60;completed&#x60; key may not be included.   The number of files to be downloaded depends on the number of layers specified in the manifest. 
 */
@JsonPropertyOrder({
  PullModelResponse.JSON_PROPERTY_STATUS,
  PullModelResponse.JSON_PROPERTY_DIGEST,
  PullModelResponse.JSON_PROPERTY_TOTAL,
  PullModelResponse.JSON_PROPERTY_COMPLETED
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-03-06T00:48:30.515751098+01:00[Europe/Zurich]")
public class PullModelResponse {
  public static final String JSON_PROPERTY_STATUS = "status";
  private PullModelStatus status;

  public static final String JSON_PROPERTY_DIGEST = "digest";
  private String digest;

  public static final String JSON_PROPERTY_TOTAL = "total";
  private Integer total;

  public static final String JSON_PROPERTY_COMPLETED = "completed";
  private Integer completed;

  public PullModelResponse() {
  }

  public PullModelResponse status(PullModelStatus status) {
    
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_STATUS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public PullModelStatus getStatus() {
    return status;
  }


  @JsonProperty(JSON_PROPERTY_STATUS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setStatus(PullModelStatus status) {
    this.status = status;
  }


  public PullModelResponse digest(String digest) {
    
    this.digest = digest;
    return this;
  }

   /**
   * The model&#39;s digest.
   * @return digest
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_DIGEST)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getDigest() {
    return digest;
  }


  @JsonProperty(JSON_PROPERTY_DIGEST)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDigest(String digest) {
    this.digest = digest;
  }


  public PullModelResponse total(Integer total) {
    
    this.total = total;
    return this;
  }

   /**
   * Total size of the model.
   * @return total
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TOTAL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getTotal() {
    return total;
  }


  @JsonProperty(JSON_PROPERTY_TOTAL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTotal(Integer total) {
    this.total = total;
  }


  public PullModelResponse completed(Integer completed) {
    
    this.completed = completed;
    return this;
  }

   /**
   * Total bytes transferred.
   * @return completed
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_COMPLETED)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getCompleted() {
    return completed;
  }


  @JsonProperty(JSON_PROPERTY_COMPLETED)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCompleted(Integer completed) {
    this.completed = completed;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PullModelResponse pullModelResponse = (PullModelResponse) o;
    return Objects.equals(this.status, pullModelResponse.status) &&
        Objects.equals(this.digest, pullModelResponse.digest) &&
        Objects.equals(this.total, pullModelResponse.total) &&
        Objects.equals(this.completed, pullModelResponse.completed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, digest, total, completed);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PullModelResponse {\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    digest: ").append(toIndentedString(digest)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    completed: ").append(toIndentedString(completed)).append("\n");
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

