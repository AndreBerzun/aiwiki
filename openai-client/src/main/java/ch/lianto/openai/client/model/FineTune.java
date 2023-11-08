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
import ch.lianto.openai.client.model.FineTuneEvent;
import ch.lianto.openai.client.model.FineTuneHyperparams;
import ch.lianto.openai.client.model.OpenAIFile;
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
 * The &#x60;FineTune&#x60; object represents a legacy fine-tune job that has been created through the API. 
 * @deprecated
 */
@Deprecated
@JsonPropertyOrder({
  FineTune.JSON_PROPERTY_ID,
  FineTune.JSON_PROPERTY_CREATED_AT,
  FineTune.JSON_PROPERTY_EVENTS,
  FineTune.JSON_PROPERTY_FINE_TUNED_MODEL,
  FineTune.JSON_PROPERTY_HYPERPARAMS,
  FineTune.JSON_PROPERTY_MODEL,
  FineTune.JSON_PROPERTY_OBJECT,
  FineTune.JSON_PROPERTY_ORGANIZATION_ID,
  FineTune.JSON_PROPERTY_RESULT_FILES,
  FineTune.JSON_PROPERTY_STATUS,
  FineTune.JSON_PROPERTY_TRAINING_FILES,
  FineTune.JSON_PROPERTY_UPDATED_AT,
  FineTune.JSON_PROPERTY_VALIDATION_FILES
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-08T23:05:52.228713843+01:00[Europe/Zurich]")
public class FineTune {
  public static final String JSON_PROPERTY_ID = "id";
  private String id;

  public static final String JSON_PROPERTY_CREATED_AT = "created_at";
  private Integer createdAt;

  public static final String JSON_PROPERTY_EVENTS = "events";
  private List<FineTuneEvent> events;

  public static final String JSON_PROPERTY_FINE_TUNED_MODEL = "fine_tuned_model";
  private String fineTunedModel;

  public static final String JSON_PROPERTY_HYPERPARAMS = "hyperparams";
  private FineTuneHyperparams hyperparams;

  public static final String JSON_PROPERTY_MODEL = "model";
  private String model;

  /**
   * The object type, which is always \&quot;fine-tune\&quot;.
   */
  public enum ObjectEnum {
    FINE_TUNE("fine-tune");

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

  public static final String JSON_PROPERTY_ORGANIZATION_ID = "organization_id";
  private String organizationId;

  public static final String JSON_PROPERTY_RESULT_FILES = "result_files";
  private List<OpenAIFile> resultFiles = new ArrayList<>();

  public static final String JSON_PROPERTY_STATUS = "status";
  private String status;

  public static final String JSON_PROPERTY_TRAINING_FILES = "training_files";
  private List<OpenAIFile> trainingFiles = new ArrayList<>();

  public static final String JSON_PROPERTY_UPDATED_AT = "updated_at";
  private Integer updatedAt;

  public static final String JSON_PROPERTY_VALIDATION_FILES = "validation_files";
  private List<OpenAIFile> validationFiles = new ArrayList<>();

  public FineTune() {
  }

  public FineTune id(String id) {
    
    this.id = id;
    return this;
  }

   /**
   * The object identifier, which can be referenced in the API endpoints.
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


  public FineTune createdAt(Integer createdAt) {
    
    this.createdAt = createdAt;
    return this;
  }

   /**
   * The Unix timestamp (in seconds) for when the fine-tuning job was created.
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


  public FineTune events(List<FineTuneEvent> events) {
    
    this.events = events;
    return this;
  }

  public FineTune addEventsItem(FineTuneEvent eventsItem) {
    if (this.events == null) {
      this.events = new ArrayList<>();
    }
    this.events.add(eventsItem);
    return this;
  }

   /**
   * The list of events that have been observed in the lifecycle of the FineTune job.
   * @return events
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_EVENTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<FineTuneEvent> getEvents() {
    return events;
  }


  @JsonProperty(JSON_PROPERTY_EVENTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setEvents(List<FineTuneEvent> events) {
    this.events = events;
  }


  public FineTune fineTunedModel(String fineTunedModel) {
    
    this.fineTunedModel = fineTunedModel;
    return this;
  }

   /**
   * The name of the fine-tuned model that is being created.
   * @return fineTunedModel
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_FINE_TUNED_MODEL)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getFineTunedModel() {
    return fineTunedModel;
  }


  @JsonProperty(JSON_PROPERTY_FINE_TUNED_MODEL)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setFineTunedModel(String fineTunedModel) {
    this.fineTunedModel = fineTunedModel;
  }


  public FineTune hyperparams(FineTuneHyperparams hyperparams) {
    
    this.hyperparams = hyperparams;
    return this;
  }

   /**
   * Get hyperparams
   * @return hyperparams
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_HYPERPARAMS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public FineTuneHyperparams getHyperparams() {
    return hyperparams;
  }


  @JsonProperty(JSON_PROPERTY_HYPERPARAMS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setHyperparams(FineTuneHyperparams hyperparams) {
    this.hyperparams = hyperparams;
  }


  public FineTune model(String model) {
    
    this.model = model;
    return this;
  }

   /**
   * The base model that is being fine-tuned.
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


  public FineTune _object(ObjectEnum _object) {
    
    this._object = _object;
    return this;
  }

   /**
   * The object type, which is always \&quot;fine-tune\&quot;.
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


  public FineTune organizationId(String organizationId) {
    
    this.organizationId = organizationId;
    return this;
  }

   /**
   * The organization that owns the fine-tuning job.
   * @return organizationId
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_ORGANIZATION_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getOrganizationId() {
    return organizationId;
  }


  @JsonProperty(JSON_PROPERTY_ORGANIZATION_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setOrganizationId(String organizationId) {
    this.organizationId = organizationId;
  }


  public FineTune resultFiles(List<OpenAIFile> resultFiles) {
    
    this.resultFiles = resultFiles;
    return this;
  }

  public FineTune addResultFilesItem(OpenAIFile resultFilesItem) {
    if (this.resultFiles == null) {
      this.resultFiles = new ArrayList<>();
    }
    this.resultFiles.add(resultFilesItem);
    return this;
  }

   /**
   * The compiled results files for the fine-tuning job.
   * @return resultFiles
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_RESULT_FILES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public List<OpenAIFile> getResultFiles() {
    return resultFiles;
  }


  @JsonProperty(JSON_PROPERTY_RESULT_FILES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setResultFiles(List<OpenAIFile> resultFiles) {
    this.resultFiles = resultFiles;
  }


  public FineTune status(String status) {
    
    this.status = status;
    return this;
  }

   /**
   * The current status of the fine-tuning job, which can be either &#x60;created&#x60;, &#x60;running&#x60;, &#x60;succeeded&#x60;, &#x60;failed&#x60;, or &#x60;cancelled&#x60;.
   * @return status
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_STATUS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getStatus() {
    return status;
  }


  @JsonProperty(JSON_PROPERTY_STATUS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setStatus(String status) {
    this.status = status;
  }


  public FineTune trainingFiles(List<OpenAIFile> trainingFiles) {
    
    this.trainingFiles = trainingFiles;
    return this;
  }

  public FineTune addTrainingFilesItem(OpenAIFile trainingFilesItem) {
    if (this.trainingFiles == null) {
      this.trainingFiles = new ArrayList<>();
    }
    this.trainingFiles.add(trainingFilesItem);
    return this;
  }

   /**
   * The list of files used for training.
   * @return trainingFiles
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_TRAINING_FILES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public List<OpenAIFile> getTrainingFiles() {
    return trainingFiles;
  }


  @JsonProperty(JSON_PROPERTY_TRAINING_FILES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setTrainingFiles(List<OpenAIFile> trainingFiles) {
    this.trainingFiles = trainingFiles;
  }


  public FineTune updatedAt(Integer updatedAt) {
    
    this.updatedAt = updatedAt;
    return this;
  }

   /**
   * The Unix timestamp (in seconds) for when the fine-tuning job was last updated.
   * @return updatedAt
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_UPDATED_AT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Integer getUpdatedAt() {
    return updatedAt;
  }


  @JsonProperty(JSON_PROPERTY_UPDATED_AT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setUpdatedAt(Integer updatedAt) {
    this.updatedAt = updatedAt;
  }


  public FineTune validationFiles(List<OpenAIFile> validationFiles) {
    
    this.validationFiles = validationFiles;
    return this;
  }

  public FineTune addValidationFilesItem(OpenAIFile validationFilesItem) {
    if (this.validationFiles == null) {
      this.validationFiles = new ArrayList<>();
    }
    this.validationFiles.add(validationFilesItem);
    return this;
  }

   /**
   * The list of files used for validation.
   * @return validationFiles
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_VALIDATION_FILES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public List<OpenAIFile> getValidationFiles() {
    return validationFiles;
  }


  @JsonProperty(JSON_PROPERTY_VALIDATION_FILES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setValidationFiles(List<OpenAIFile> validationFiles) {
    this.validationFiles = validationFiles;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FineTune fineTune = (FineTune) o;
    return Objects.equals(this.id, fineTune.id) &&
        Objects.equals(this.createdAt, fineTune.createdAt) &&
        Objects.equals(this.events, fineTune.events) &&
        Objects.equals(this.fineTunedModel, fineTune.fineTunedModel) &&
        Objects.equals(this.hyperparams, fineTune.hyperparams) &&
        Objects.equals(this.model, fineTune.model) &&
        Objects.equals(this._object, fineTune._object) &&
        Objects.equals(this.organizationId, fineTune.organizationId) &&
        Objects.equals(this.resultFiles, fineTune.resultFiles) &&
        Objects.equals(this.status, fineTune.status) &&
        Objects.equals(this.trainingFiles, fineTune.trainingFiles) &&
        Objects.equals(this.updatedAt, fineTune.updatedAt) &&
        Objects.equals(this.validationFiles, fineTune.validationFiles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdAt, events, fineTunedModel, hyperparams, model, _object, organizationId, resultFiles, status, trainingFiles, updatedAt, validationFiles);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FineTune {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    events: ").append(toIndentedString(events)).append("\n");
    sb.append("    fineTunedModel: ").append(toIndentedString(fineTunedModel)).append("\n");
    sb.append("    hyperparams: ").append(toIndentedString(hyperparams)).append("\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    _object: ").append(toIndentedString(_object)).append("\n");
    sb.append("    organizationId: ").append(toIndentedString(organizationId)).append("\n");
    sb.append("    resultFiles: ").append(toIndentedString(resultFiles)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    trainingFiles: ").append(toIndentedString(trainingFiles)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("    validationFiles: ").append(toIndentedString(validationFiles)).append("\n");
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

