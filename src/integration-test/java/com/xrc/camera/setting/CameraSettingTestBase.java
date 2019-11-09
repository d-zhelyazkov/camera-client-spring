package com.xrc.camera.setting;

import com.xrc.camera.Camera;
import com.xrc.camera.setting.util.SettingTestHandler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

abstract class CameraSettingTestBase<T> {

    @Autowired
    protected Camera camera;

    private SettingTestHandler<T> settingHandler;

    @Test
    public void checkSettingType() {
        settingHandler.verifySettingType();
    }

    @Test
    public void checkSettingValue() {
        settingHandler.verifySettingValue();
    }

    @Test
    public void checkSupportedValues() {
        settingHandler.verifySupportedValues();
    }

    @Test
    public void testValidValues() {
        settingHandler.testValidValues();
    }

    @Test
    public void testInvalidValues() {
        settingHandler.testInvalidValues();
    }

    void setValue(T value) {
        settingHandler.setValue(value);
    }

    void setSettingUnsupported(T value) {
        settingHandler.setSettingUnsupported(value);
    }

    void setSettingHandler(SettingTestHandler<T> settingHandler) {
        this.settingHandler = settingHandler;

        settingHandler.setValidValuesSupplier(this::getValidValues);
        settingHandler.setInvalidValuesSupplier(this::getInvalidValues);
    }

    Stream<T> getValidValues() {
        CameraSetting<T> cameraSetting = settingHandler.getCameraSetting();
        return cameraSetting.getValues().stream();
    }

    Stream<T> getInvalidValues() {
        return Stream.empty();
    }
}
