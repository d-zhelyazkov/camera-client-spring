package com.xrc.camera.setting;

import com.xrc.camera.model.ISettingInfo;
import com.xrc.camera.model.Setting;

import java.util.List;

public interface CameraSetting<T> {

    T getValue();

    void setValue(T value);

    /**
     * Retrieves a list of supported values for this setting.
     */
    List<T> getValues();

    boolean isEditable();

    Setting getSetting();

    ISettingInfo<T> getSettingInfo();
}
