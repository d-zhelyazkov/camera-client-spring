/*
 * Camera API
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 1.0.0-oas3
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.xrc.camera.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.xrc.camera.model.BaseSettingInfo;
import com.xrc.camera.model.Setting;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * AELockInfo
 */


public class AELockInfo extends BaseSettingInfo {
  @JsonProperty("value")
  private Boolean value = null;

  @JsonProperty("values")
  private java.util.List<Boolean> values = new java.util.ArrayList<>();

  public AELockInfo value(Boolean value) {
    this.value = value;
    return this;
  }

   /**
   * Get value
   * @return value
  **/
  @Schema(required = true, description = "")
  public Boolean getValue() {
    return value;
  }

  public void setValue(Boolean value) {
    this.value = value;
  }

  public AELockInfo values(java.util.List<Boolean> values) {
    this.values = values;
    return this;
  }

  public AELockInfo addValuesItem(Boolean valuesItem) {
    this.values.add(valuesItem);
    return this;
  }

   /**
   * Get values
   * @return values
  **/
  @Schema(required = true, description = "")
  public java.util.List<Boolean> getValues() {
    return values;
  }

  public void setValues(java.util.List<Boolean> values) {
    this.values = values;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AELockInfo aeLockInfo = (AELockInfo) o;
    return Objects.equals(this.value, aeLockInfo.value) &&
        Objects.equals(this.values, aeLockInfo.values) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, values, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AELockInfo {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    values: ").append(toIndentedString(values)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
