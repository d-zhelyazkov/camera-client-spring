package com.xrc.camera.setting.util;

import com.xrc.camera.model.Setting;
import com.xrc.camera.setting.CameraSetting;
import com.xrc.util.Collections;
import org.junit.Assert;

import java.util.Comparator;
import java.util.List;

public class ComparableSettingTestHandler<T extends Comparable<? super T>>
        extends SettingTestHandler<T> {

    public ComparableSettingTestHandler(Setting setting, CameraSetting<T> cameraSetting) {
        super(setting, cameraSetting);

        setValueComparator(Comparator.naturalOrder());
    }

    @Override
    public void verifySupportedValues() {
        super.verifySupportedValues();

        List<T> supportedValues = cameraSetting.getValues();
        Assert.assertTrue(
                "Supported values not ordered: " + supportedValues,
                Collections.isOrdered(supportedValues)
        );
    }
}
