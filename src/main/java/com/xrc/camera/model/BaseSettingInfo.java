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
import com.xrc.camera.model.Setting;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * BaseSettingInfo
 */


public abstract class BaseSettingInfo<T> implements ISettingInfo<T> {
  @JsonProperty("setting")
  private Setting setting = null;

  @JsonProperty("editable")
  private Boolean editable = null;

  public BaseSettingInfo setting(Setting setting) {
    this.setting = setting;
    return this;
  }

   /**
   * Get setting
   * @return setting
  **/
  @Schema(required = true, description = "")
  public Setting getSetting() {
    return setting;
  }

  public void setSetting(Setting setting) {
    this.setting = setting;
  }

  public BaseSettingInfo editable(Boolean editable) {
    this.editable = editable;
    return this;
  }

   /**
   * Specifies whether the value of the setting can be edited.
   * @return editable
  **/
  @Schema(required = true, description = "Specifies whether the value of the setting can be edited.")
  public Boolean isEditable() {
    return editable;
  }

  public void setEditable(Boolean editable) {
    this.editable = editable;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BaseSettingInfo baseSettingInfo = (BaseSettingInfo) o;
    return Objects.equals(this.setting, baseSettingInfo.setting) &&
        Objects.equals(this.editable, baseSettingInfo.editable);
  }

  @Override
  public int hashCode() {
    return Objects.hash(setting, editable);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BaseSettingInfo {\n");
    
    sb.append("    setting: ").append(toIndentedString(setting)).append("\n");
    sb.append("    editable: ").append(toIndentedString(editable)).append("\n");
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
