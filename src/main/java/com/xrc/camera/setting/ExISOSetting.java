package com.xrc.camera.setting;

import com.xrc.util.Range;

import java.util.List;


/**
 * Camera sensitivity (ISO) setting.
 * <p>
 * Method {@link ExISOSetting#getValues()} returns a list with 3 values:
 * min ISO value, max analog ISO value and max ISO value.
 * Compared to {@link ISOSetting} this class implements several additional methods:
 * {@link ExISOSetting#getValuesRange()} and {@link ExISOSetting#getMaxAnalogValue()}
 */
public class ExISOSetting extends CameraSettingDecorator<Integer> {

    public ExISOSetting(CameraSetting<Integer> isoSetting) {
        super(isoSetting);
    }


    public Range<Integer> getValuesRange() {
        List<Integer> values = super.getValues();
        return new Range<>(
                values.get(0),
                values.get(values.size() - 1)
        );
    }

    public int getMaxAnalogValue() {
        List<Integer> values = super.getValues();
        return values.get(1);
    }
}
