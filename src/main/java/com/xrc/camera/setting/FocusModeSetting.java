package com.xrc.camera.setting;

import com.xrc.camera.api.DefaultApi;
import com.xrc.camera.model.FocusMode;
import com.xrc.camera.model.FocusModeInfo;
import com.xrc.camera.model.FocusModeValue;

public class FocusModeSetting
        extends CameraSettingBase<FocusMode> {

    private final DefaultApi swaggerApi;

    public FocusModeSetting(DefaultApi swaggerApi) {
        this.swaggerApi = swaggerApi;
    }


    @Override
    public FocusModeInfo getSettingInfo() {
        return swaggerApi.settingsFOCUSMODEGet();
    }

    @Override
    public void setValue(FocusMode value) {
        swaggerApi.settingsFOCUSMODEPut(
                new FocusModeValue()
                        .value(value)
        );
    }
}
