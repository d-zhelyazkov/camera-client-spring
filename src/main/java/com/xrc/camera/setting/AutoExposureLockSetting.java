package com.xrc.camera.setting;

import com.xrc.camera.api.DefaultApi;
import com.xrc.camera.model.AELockInfo;
import com.xrc.camera.model.AELockValue;

public class AutoExposureLockSetting
        extends CameraSettingBase<Boolean> {

    private final DefaultApi swaggerApi;

    public AutoExposureLockSetting(DefaultApi swaggerApi) {
        this.swaggerApi = swaggerApi;
    }

    @Override
    public AELockInfo getSettingInfo() {
        return swaggerApi.settingsAELOCKGet();
    }

    @Override
    public void setValue(Boolean value) {
        swaggerApi.settingsAELOCKPut(
                new AELockValue()
                        .value(value)
        );
    }
}
