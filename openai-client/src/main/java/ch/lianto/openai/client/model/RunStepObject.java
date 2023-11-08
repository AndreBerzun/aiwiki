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
import ch.lianto.openai.client.model.RunStepObjectLastError;
import ch.lianto.openai.client.model.RunStepObjectStepDetails;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Represents a step in execution of a run. 
 */
@JsonPropertyOrder({
  RunStepObject.JSON_PROPERTY_ID,
  RunStepObject.JSON_PROPERTY_OBJECT,
  RunStepObject.JSON_PROPERTY_CREATED_AT,
  RunStepObject.JSON_PROPERTY_ASSISTANT_ID,
  RunStepObject.JSON_PROPERTY_THREAD_ID,
  RunStepObject.JSON_PROPERTY_RUN_ID,
  RunStepObject.JSON_PROPERTY_TYPE,
  RunStepObject.JSON_PROPERTY_STATUS,
  RunStepObject.JSON_PROPERTY_STEP_DETAILS,
  RunStepObject.JSON_PROPERTY_LAST_ERROR,
  RunStepObject.JSON_PROPERTY_EXPIRED_AT,
  RunStepObject.JSON_PROPERTY_CANCELLED_AT,
  RunStepObject.JSON_PROPERTY_FAILED_AT,
  RunStepObject.JSON_PROPERTY_COMPLETED_AT,
  RunStepObject.JSON_PROPERTY_METADATA
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-08T23:05:52.228713843+01:00[Europe/Zurich]")
public class RunStepObject {
  public static final String JSON_PROPERTY_ID = "id";
  private String id;

  /**
   * The object type, which is always &#x60;assistant.run.step&#x60;&#x60;.
   */
  public enum ObjectEnum {
    ASSISTANT_RUN_STEP("assistant.run.step");

    private String value;

    ObjectEnum(String value) {
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
    public static ObjectEnum fromValue(String value) {
      for (ObjectEnum b : ObjectEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_OBJECT = "object";
  private ObjectEnum _object;

  public static final String JSON_PROPERTY_CREATED_AT = "created_at";
  private Integer createdAt;

  public static final String JSON_PROPERTY_ASSISTANT_ID = "assistant_id";
  private String assistantId;

  public static final String JSON_PROPERTY_THREAD_ID = "thread_id";
  private String threadId;

  public static final String JSON_PROPERTY_RUN_ID = "run_id";
  private String runId;

  /**
   * The type of run step, which can be either &#x60;message_creation&#x60; or &#x60;tool_calls&#x60;.
   */
  public enum TypeEnum {
    MESSAGE_CREATION("message_creation"),
    
    TOOL_CALLS("tool_calls");

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
  private TypeEnum type;

  /**
   * The status of the run, which can be either &#x60;in_progress&#x60;, &#x60;cancelled&#x60;, &#x60;failed&#x60;, &#x60;completed&#x60;, or &#x60;expired&#x60;.
   */
  public enum StatusEnum {
    IN_PROGRESS("in_progress"),
    
    CANCELLED("cancelled"),
    
    FAILED("failed"),
    
    COMPLETED("completed"),
    
    EXPIRED("expired");

    private String value;

    StatusEnum(String value) {
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
    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_STATUS = "status";
  private StatusEnum status;

  public static final String JSON_PROPERTY_STEP_DETAILS = "step_details";
  private RunStepObjectStepDetails stepDetails;

  public static final String JSON_PROPERTY_LAST_ERROR = "last_error";
  private RunStepObjectLastError lastError;

  public static final String JSON_PROPERTY_EXPIRED_AT = "expired_at";
  private Integer expiredAt;

  public static final String JSON_PROPERTY_CANCELLED_AT = "cancelled_at";
  private Integer cancelledAt;

  public static final String JSON_PROPERTY_FAILED_AT = "failed_at";
  private Integer failedAt;

  public static final String JSON_PROPERTY_COMPLETED_AT = "completed_at";
  private Integer completedAt;

  public static final String JSON_PROPERTY_METADATA = "metadata";
  private Object metadata;

  public RunStepObject() {
  }

  public RunStepObject id(String id) {
    
    this.id = id;
    return this;
  }

   /**
   * The identifier of the run step, which can be referenced in API endpoints.
   * @return id
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getId() {
    return id;
  }


  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setId(String id) {
    this.id = id;
  }


  public RunStepObject _object(ObjectEnum _object) {
    
    this._object = _object;
    return this;
  }

   /**
   * The object type, which is always &#x60;assistant.run.step&#x60;&#x60;.
   * @return _object
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_OBJECT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public ObjectEnum getObject() {
    return _object;
  }


  @JsonProperty(JSON_PROPERTY_OBJECT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setObject(ObjectEnum _object) {
    this._object = _object;
  }


  public RunStepObject createdAt(Integer createdAt) {
    
    this.createdAt = createdAt;
    return this;
  }

   /**
   * The Unix timestamp (in seconds) for when the run step was created.
   * @return createdAt
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_CREATED_AT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Integer getCreatedAt() {
    return createdAt;
  }


  @JsonProperty(JSON_PROPERTY_CREATED_AT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCreatedAt(Integer createdAt) {
    this.createdAt = createdAt;
  }


  public RunStepObject assistantId(String assistantId) {
    
    this.assistantId = assistantId;
    return this;
  }

   /**
   * The ID of the [assistant](/docs/api-reference/assistants) associated with the run step.
   * @return assistantId
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_ASSISTANT_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getAssistantId() {
    return assistantId;
  }


  @JsonProperty(JSON_PROPERTY_ASSISTANT_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setAssistantId(String assistantId) {
    this.assistantId = assistantId;
  }


  public RunStepObject threadId(String threadId) {
    
    this.threadId = threadId;
    return this;
  }

   /**
   * The ID of the [thread](/docs/api-reference/threads) that was run.
   * @return threadId
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_THREAD_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getThreadId() {
    return threadId;
  }


  @JsonProperty(JSON_PROPERTY_THREAD_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setThreadId(String threadId) {
    this.threadId = threadId;
  }


  public RunStepObject runId(String runId) {
    
    this.runId = runId;
    return this;
  }

   /**
   * The ID of the [run](/docs/api-reference/runs) that this run step is a part of.
   * @return runId
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_RUN_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getRunId() {
    return runId;
  }


  @JsonProperty(JSON_PROPERTY_RUN_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setRunId(String runId) {
    this.runId = runId;
  }


  public RunStepObject type(TypeEnum type) {
    
    this.type = type;
    return this;
  }

   /**
   * The type of run step, which can be either &#x60;message_creation&#x60; or &#x60;tool_calls&#x60;.
   * @return type
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public TypeEnum getType() {
    return type;
  }


  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setType(TypeEnum type) {
    this.type = type;
  }


  public RunStepObject status(StatusEnum status) {
    
    this.status = status;
    return this;
  }

   /**
   * The status of the run, which can be either &#x60;in_progress&#x60;, &#x60;cancelled&#x60;, &#x60;failed&#x60;, &#x60;completed&#x60;, or &#x60;expired&#x60;.
   * @return status
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_STATUS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public StatusEnum getStatus() {
    return status;
  }


  @JsonProperty(JSON_PROPERTY_STATUS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  public RunStepObject stepDetails(RunStepObjectStepDetails stepDetails) {
    
    this.stepDetails = stepDetails;
    return this;
  }

   /**
   * Get stepDetails
   * @return stepDetails
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_STEP_DETAILS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public RunStepObjectStepDetails getStepDetails() {
    return stepDetails;
  }


  @JsonProperty(JSON_PROPERTY_STEP_DETAILS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setStepDetails(RunStepObjectStepDetails stepDetails) {
    this.stepDetails = stepDetails;
  }


  public RunStepObject lastError(RunStepObjectLastError lastError) {
    
    this.lastError = lastError;
    return this;
  }

   /**
   * Get lastError
   * @return lastError
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_LAST_ERROR)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public RunStepObjectLastError getLastError() {
    return lastError;
  }


  @JsonProperty(JSON_PROPERTY_LAST_ERROR)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setLastError(RunStepObjectLastError lastError) {
    this.lastError = lastError;
  }


  public RunStepObject expiredAt(Integer expiredAt) {
    
    this.expiredAt = expiredAt;
    return this;
  }

   /**
   * The Unix timestamp (in seconds) for when the run step expired. A step is considered expired if the parent run is expired.
   * @return expiredAt
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_EXPIRED_AT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Integer getExpiredAt() {
    return expiredAt;
  }


  @JsonProperty(JSON_PROPERTY_EXPIRED_AT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setExpiredAt(Integer expiredAt) {
    this.expiredAt = expiredAt;
  }


  public RunStepObject cancelledAt(Integer cancelledAt) {
    
    this.cancelledAt = cancelledAt;
    return this;
  }

   /**
   * The Unix timestamp (in seconds) for when the run step was cancelled.
   * @return cancelledAt
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CANCELLED_AT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Integer getCancelledAt() {
    return cancelledAt;
  }


  @JsonProperty(JSON_PROPERTY_CANCELLED_AT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCancelledAt(Integer cancelledAt) {
    this.cancelledAt = cancelledAt;
  }


  public RunStepObject failedAt(Integer failedAt) {
    
    this.failedAt = failedAt;
    return this;
  }

   /**
   * The Unix timestamp (in seconds) for when the run step failed.
   * @return failedAt
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_FAILED_AT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Integer getFailedAt() {
    return failedAt;
  }


  @JsonProperty(JSON_PROPERTY_FAILED_AT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setFailedAt(Integer failedAt) {
    this.failedAt = failedAt;
  }


  public RunStepObject completedAt(Integer completedAt) {
    
    this.completedAt = completedAt;
    return this;
  }

   /**
   * The Unix timestamp (in seconds) for when the run step completed.
   * @return completedAt
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_COMPLETED_AT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Integer getCompletedAt() {
    return completedAt;
  }


  @JsonProperty(JSON_PROPERTY_COMPLETED_AT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCompletedAt(Integer completedAt) {
    this.completedAt = completedAt;
  }


  public RunStepObject metadata(Object metadata) {
    
    this.metadata = metadata;
    return this;
  }

   /**
   * Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long. 
   * @return metadata
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_METADATA)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Object getMetadata() {
    return metadata;
  }


  @JsonProperty(JSON_PROPERTY_METADATA)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setMetadata(Object metadata) {
    this.metadata = metadata;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RunStepObject runStepObject = (RunStepObject) o;
    return Objects.equals(this.id, runStepObject.id) &&
        Objects.equals(this._object, runStepObject._object) &&
        Objects.equals(this.createdAt, runStepObject.createdAt) &&
        Objects.equals(this.assistantId, runStepObject.assistantId) &&
        Objects.equals(this.threadId, runStepObject.threadId) &&
        Objects.equals(this.runId, runStepObject.runId) &&
        Objects.equals(this.type, runStepObject.type) &&
        Objects.equals(this.status, runStepObject.status) &&
        Objects.equals(this.stepDetails, runStepObject.stepDetails) &&
        Objects.equals(this.lastError, runStepObject.lastError) &&
        Objects.equals(this.expiredAt, runStepObject.expiredAt) &&
        Objects.equals(this.cancelledAt, runStepObject.cancelledAt) &&
        Objects.equals(this.failedAt, runStepObject.failedAt) &&
        Objects.equals(this.completedAt, runStepObject.completedAt) &&
        Objects.equals(this.metadata, runStepObject.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, _object, createdAt, assistantId, threadId, runId, type, status, stepDetails, lastError, expiredAt, cancelledAt, failedAt, completedAt, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunStepObject {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    _object: ").append(toIndentedString(_object)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    assistantId: ").append(toIndentedString(assistantId)).append("\n");
    sb.append("    threadId: ").append(toIndentedString(threadId)).append("\n");
    sb.append("    runId: ").append(toIndentedString(runId)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    stepDetails: ").append(toIndentedString(stepDetails)).append("\n");
    sb.append("    lastError: ").append(toIndentedString(lastError)).append("\n");
    sb.append("    expiredAt: ").append(toIndentedString(expiredAt)).append("\n");
    sb.append("    cancelledAt: ").append(toIndentedString(cancelledAt)).append("\n");
    sb.append("    failedAt: ").append(toIndentedString(failedAt)).append("\n");
    sb.append("    completedAt: ").append(toIndentedString(completedAt)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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

