package com.xrc.camera.setting;

import com.xrc.util.Range;

import java.util.List;


/**
 * Camera shutter speed setting.
 * <p>
 * Method {@link ExShutterSpeedSetting#getValues()} returns a list with 2 values:
 * min shutter speed value and max shutter speed value.
 * Compared to {@link ShutterSpeedSetting} this class implements an additional method:
 * {@link ExShutterSpeedSetting#getValuesRange()}
 */
public class ExShutterSpeedSetting extends CameraSettingDecorator<Long> {

    public ExShutterSpeedSetting(CameraSetting<Long> isoSetting) {
        super(isoSetting);
    }

    public Range<Long> getValuesRange() {
        List<Long> values = super.getValues();
        return new Range<>(
                values.get(0),
                values.get(values.size() - 1)
        );
    }
}
