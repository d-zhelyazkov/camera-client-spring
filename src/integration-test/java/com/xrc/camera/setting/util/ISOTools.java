package com.xrc.camera.setting.util;

import com.xrc.camera.model.Setting;
import com.xrc.camera.setting.ExISOSetting;
import com.xrc.camera.setting.UnsupportedSettingException;
import com.xrc.lang.Integers;
import com.xrc.lang.Numbers;
import com.xrc.util.Range;

import java.util.Random;
import java.util.stream.Stream;

public class ISOTools {

    private static final int RANDOM_VALUES = 10;

    public static SettingTestHandler<Integer> getTestHandler(ExISOSetting isoSetting, int maxValueError) {
        SettingTestHandler<Integer> settingHandler =
                new ComparableSettingTestHandler<>(Setting.ISO, isoSetting);
        settingHandler.setValueComparator(Numbers.getComparatorWithError(maxValueError));

        settingHandler.setValueValidator(value -> {
            Range<Integer> valuesRange = isoSetting.getValuesRange();
            int minValue = valuesRange.getLowerBound();
            int maxValue = valuesRange.getHigherBound();

            int maxAnalogSensitivity = isoSetting.getMaxAnalogValue();
            int k = maxValue / maxAnalogSensitivity;
            k = Integers.nextPowerOfTwo(k);
            maxValue = maxAnalogSensitivity * k;

            valuesRange = new Range<>(minValue, maxValue);
            return valuesRange.isInRange(value);
        });

        return settingHandler;
    }

    public static Stream<Integer> getValidValues(ExISOSetting isoSetting) {
        Range<Integer> valuesRange = isoSetting.getValuesRange();
        int minValue = valuesRange.getLowerBound();
        int maxValue = valuesRange.getHigherBound();
        int maxAnalogValue = isoSetting.getMaxAnalogValue();

        Random random = new Random();
        return Stream.concat(
                Stream.of(minValue, maxAnalogValue, maxValue),
                Stream.generate(() ->
                        random.nextInt(maxValue - minValue) + minValue
                )
                        .limit(RANDOM_VALUES)
        );
    }

    public static Stream<Integer> getInvalidValues(ExISOSetting isoSetting) {
        Range<Integer> valuesRange = isoSetting.getValuesRange();
        return Stream.of(
                valuesRange.getLowerBound() - 1,
                valuesRange.getHigherBound() + 1,
                0
        );
    }

}
