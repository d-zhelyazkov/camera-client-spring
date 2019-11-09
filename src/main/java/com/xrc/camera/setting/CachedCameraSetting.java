package com.xrc.camera.setting;

import com.xrc.camera.model.ISettingInfo;
import com.xrc.camera.model.Setting;

import java.util.List;

public class CachedCameraSetting<T> extends CameraSettingDecorator<T> {

    private ISettingInfo<T> cachedSettingInfo = null;

    public CachedCameraSetting(CameraSetting<T> decoratedSetting) {
        super(decoratedSetting);
    }

    @Override
    public Setting getSetting() {
        ensureCachedInfo();
        return cachedSettingInfo.getSetting();
    }

    @Override
    public boolean isEditable() {
        ensureCachedInfo();
        return cachedSettingInfo.isEditable();
    }

    @Override
    public List<T> getValues() {
        ensureCachedInfo();
        return cachedSettingInfo.getValues();
    }

    @Override
    public T getValue() {
        updateCachedInfo();
        return cachedSettingInfo.getValue();
    }

    @Override
    public ISettingInfo<T> getSettingInfo() {
        updateCachedInfo();
        return cachedSettingInfo;
    }

    private void updateCachedInfo() {
        cachedSettingInfo = super.getSettingInfo();
    }

    private void ensureCachedInfo() {
        if (cachedSettingInfo == null) {
            updateCachedInfo();
        }
    }
}
