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
 * ISOInfo
 */


public class ISOInfo extends BaseSettingInfo {
  @JsonProperty("value")
  private Integer value = null;

  @JsonProperty("values")
  private java.util.List<Integer> values = new java.util.ArrayList<>();

  public ISOInfo value(Integer value) {
    this.value = value;
    return this;
  }

   /**
   * Get value
   * @return value
  **/
  @Schema(required = true, description = "")
  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public ISOInfo values(java.util.List<Integer> values) {
    this.values = values;
    return this;
  }

  public ISOInfo addValuesItem(Integer valuesItem) {
    this.values.add(valuesItem);
    return this;
  }

   /**
   * Get values
   * @return values
  **/
  @Schema(required = true, description = "")
  public java.util.List<Integer> getValues() {
    return values;
  }

  public void setValues(java.util.List<Integer> values) {
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
    ISOInfo isOInfo = (ISOInfo) o;
    return Objects.equals(this.value, isOInfo.value) &&
        Objects.equals(this.values, isOInfo.values) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, values, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ISOInfo {\n");
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
