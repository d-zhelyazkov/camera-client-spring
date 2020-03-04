package com.xrc.camera.setting;

import com.xrc.camera.api.DefaultApi;
import com.xrc.camera.model.ShutterSpeedInfo;
import com.xrc.camera.model.ShutterSpeedValue;

public class ShutterSpeedSetting extends CameraSettingBase<Long> {

    private final DefaultApi swaggerApi;

    public ShutterSpeedSetting(DefaultApi swaggerApi) {
        this.swaggerApi = swaggerApi;
    }

    @Override
    public void setValue(Long value) {
        swaggerApi.settingsSHUTTERSPEEDPut(
                new ShutterSpeedValue()
                        .value(value)
        );
    }

    @Override
    public ShutterSpeedInfo getSettingInfo() {
        return swaggerApi.settingsSHUTTERSPEEDGet();
    }
}
