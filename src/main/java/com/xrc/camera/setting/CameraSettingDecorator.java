package com.xrc.camera.setting;

import com.xrc.camera.model.ISettingInfo;
import com.xrc.camera.model.Setting;

import java.util.List;

public class CameraSettingDecorator<T> implements CameraSetting<T> {
    private final CameraSetting<T> decoratedSetting;

    public CameraSettingDecorator(CameraSetting<T> decoratedSetting) {
        this.decoratedSetting = decoratedSetting;
    }

    @Override
    public T getValue() {
        return decoratedSetting.getValue();
    }

    @Override
    public void setValue(T value) throws UnsupportedOperationException {
        decoratedSetting.setValue(value);
    }

    @Override
    public List<T> getValues() {
        return decoratedSetting.getValues();
    }

    @Override
    public boolean isEditable() {
        return decoratedSetting.isEditable();
    }

    @Override
    public Setting getSetting() {
        return decoratedSetting.getSetting();
    }

    @Override
    public ISettingInfo<T> getSettingInfo() {
        return decoratedSetting.getSettingInfo();
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", this.getClass().getSimpleName(), decoratedSetting);
    }
}
