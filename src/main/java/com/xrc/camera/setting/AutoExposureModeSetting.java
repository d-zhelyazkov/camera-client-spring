package com.xrc.camera.setting;

import com.xrc.camera.api.DefaultApi;
import com.xrc.camera.model.AEMode;
import com.xrc.camera.model.AEModeValue;
import com.xrc.camera.model.ISettingInfo;

public class AutoExposureModeSetting extends CameraSettingBase<AEMode> {

    private final DefaultApi swaggerApi;

    public AutoExposureModeSetting(DefaultApi swaggerApi) {
        this.swaggerApi = swaggerApi;
    }

    @Override
    public void setValue(AEMode value) {
        swaggerApi.settingsAEMODEPut(
                new AEModeValue()
                        .value(value)
        );
    }

    @Override
    public ISettingInfo<AEMode> getSettingInfo() {
        return swaggerApi.settingsAEMODEGet();
    }
}
