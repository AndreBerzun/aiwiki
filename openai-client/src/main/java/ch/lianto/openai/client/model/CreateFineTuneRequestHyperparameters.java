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
import ch.lianto.openai.client.model.CreateFineTuneRequestHyperparametersNEpochs;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The hyperparameters used for the fine-tuning job.
 */
@JsonPropertyOrder({
  CreateFineTuneRequestHyperparameters.JSON_PROPERTY_N_EPOCHS
})
@JsonTypeName("CreateFineTuneRequest_hyperparameters")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-08T23:05:52.228713843+01:00[Europe/Zurich]")
public class CreateFineTuneRequestHyperparameters {
  public static final String JSON_PROPERTY_N_EPOCHS = "n_epochs";
  private CreateFineTuneRequestHyperparametersNEpochs nEpochs = auto;

  public CreateFineTuneRequestHyperparameters() {
  }

  public CreateFineTuneRequestHyperparameters nEpochs(CreateFineTuneRequestHyperparametersNEpochs nEpochs) {
    
    this.nEpochs = nEpochs;
    return this;
  }

   /**
   * Get nEpochs
   * @return nEpochs
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_N_EPOCHS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public CreateFineTuneRequestHyperparametersNEpochs getnEpochs() {
    return nEpochs;
  }


  @JsonProperty(JSON_PROPERTY_N_EPOCHS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setnEpochs(CreateFineTuneRequestHyperparametersNEpochs nEpochs) {
    this.nEpochs = nEpochs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateFineTuneRequestHyperparameters createFineTuneRequestHyperparameters = (CreateFineTuneRequestHyperparameters) o;
    return Objects.equals(this.nEpochs, createFineTuneRequestHyperparameters.nEpochs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nEpochs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateFineTuneRequestHyperparameters {\n");
    sb.append("    nEpochs: ").append(toIndentedString(nEpochs)).append("\n");
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

