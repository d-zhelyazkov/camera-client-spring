package com.xrc.camera.setting.util;

import com.xrc.camera.model.Setting;
import com.xrc.camera.setting.ExShutterSpeedSetting;
import com.xrc.lang.Numbers;
import com.xrc.util.Range;

import java.util.Random;
import java.util.stream.Stream;

public class SSTools {

    private static final int RANDOM_VALUES = 10;

    public static SettingTestHandler<Long> getTestHandler(ExShutterSpeedSetting ssSetting) {
        return getTestHandler(ssSetting, 0);
    }

    public static SettingTestHandler<Long> getTestHandler(ExShutterSpeedSetting ssSetting, long maxValueError) {
        SettingTestHandler<Long> settingHandler =
                new ComparableSettingTestHandler<>(Setting.SHUTTER_SPEED, ssSetting);
        settingHandler.setValueComparator(Numbers.getComparatorWithError(maxValueError));

        settingHandler.setValueValidator(value -> {
            Range<Long> valuesRange = ssSetting.getValuesRange();
            return valuesRange.isInRange(value);
        });

        return settingHandler;
    }

    public static Stream<Long> getValidValues(ExShutterSpeedSetting ssSetting) {
        Range<Long> valuesRange = ssSetting.getValuesRange();
        long minValue = valuesRange.getLowerBound();
        long maxValue = valuesRange.getHigherBound();
        long amplitude = maxValue - minValue;
        long mod = amplitude / 4;
        // high values reduce camera responsiveness

        Random random = new Random();
        return Stream.concat(
                Stream.of(minValue, maxValue),
                Stream.generate(() ->
                        (Math.abs(random.nextLong()) % mod) + minValue
                )
                        .limit(RANDOM_VALUES)
        );
    }

    public static Stream<Long> getInvalidValues(ExShutterSpeedSetting ssSetting) {
        Range<Long> valuesRange = ssSetting.getValuesRange();
        return Stream.of(
                valuesRange.getLowerBound() - 1,
                valuesRange.getHigherBound() + 1,
                0L
        );
    }

}
