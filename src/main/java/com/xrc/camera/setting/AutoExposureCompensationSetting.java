package com.xrc.camera.setting;


import com.xrc.camera.api.DefaultApi;
import com.xrc.camera.model.AECompensationInfo;
import com.xrc.camera.model.AECompensationValue;

public class AutoExposureCompensationSetting
        extends CameraSettingBase<Float> {

    private final DefaultApi swaggerApi;

    public AutoExposureCompensationSetting(DefaultApi swaggerApi) {
        this.swaggerApi = swaggerApi;
    }

    @Override
    public AECompensationInfo getSettingInfo() {
        return swaggerApi.settingsAECOMPENSATIONGet();
    }

    @Override
    public void setValue(Float value) {
        swaggerApi.settingsAECOMPENSATIONPut(
                new AECompensationValue()
                        .value(value)
        );
    }
}
