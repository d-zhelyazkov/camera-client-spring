package com.xrc.camera.setting;

import com.xrc.camera.model.ISettingInfo;
import com.xrc.camera.model.Setting;

import java.util.List;

public abstract class CameraSettingBase<T> implements CameraSetting<T> {

    @Override
    public T getValue() {
        ISettingInfo<T> settingInfo = getSettingInfo();
        return settingInfo.getValue();
    }

    @Override
    public List<T> getValues() {
        ISettingInfo<T> settingInfo = getSettingInfo();
        return settingInfo.getValues();
    }

    @Override
    public boolean isEditable() {
        ISettingInfo<T> settingInfo = getSettingInfo();
        return settingInfo.isEditable();
    }

    @Override
    public Setting getSetting() {
        ISettingInfo<T> settingInfo = getSettingInfo();
        return settingInfo.getSetting();
    }

    @Override
    public String toString() {
        ISettingInfo<T> settingInfo = getSettingInfo();
        return settingInfo.toString();
    }

}
