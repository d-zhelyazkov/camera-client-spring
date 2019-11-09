package com.xrc.camera.setting;

import com.xrc.camera.api.DefaultApi;
import com.xrc.camera.model.ISOInfo;
import com.xrc.camera.model.ISOValue;

/**
 * Camera sensitivity (ISO) setting.
 * <p>
 * Method {@link ISOSetting#getValues()} returns a list with 3 values:
 * min ISO value, max analog ISO value and max ISO value.
 */
public class ISOSetting extends CameraSettingBase<Integer> {

    private final DefaultApi swaggerApi;

    public ISOSetting(DefaultApi swaggerApi) {
        this.swaggerApi = swaggerApi;
    }

    @Override
    public ISOInfo getSettingInfo() {
        return swaggerApi.settingsISOGet();
    }

    @Override
    public void setValue(Integer value) {
        swaggerApi.settingsISOPut(
                new ISOValue()
                        .value(value)
        );
    }
}
