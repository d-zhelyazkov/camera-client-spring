package com.xrc.camera.setting;

import com.xrc.camera.model.Setting;
import com.xrc.camera.setting.util.ComparableSettingTestHandler;
import com.xrc.camera.setting.util.SettingTestHandler;
import com.xrc.lang.Integers;
import com.xrc.lang.Numbers;
import com.xrc.util.Range;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.stream.Stream;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ISOTest extends CameraSettingTestBase<Integer> {

    private static final int RANDOM_VALUES = 10;

    @Value("${test.iso.maxValueError}")
    private int maxValueError;

    @PostConstruct
    public void init() {
        SettingTestHandler<Integer> settingHandler =
                new ComparableSettingTestHandler<>(Setting.ISO, getISOSetting());
        settingHandler.setValueValidator(this::validateValue);
        settingHandler.setValueComparator(Numbers.getComparatorWithError(maxValueError));
        setSettingHandler(settingHandler);
    }

    public boolean validateValue(Integer value) throws UnsupportedSettingException {
        ExISOSetting isoSetting = getISOSetting();

        Range<Integer> valuesRange = isoSetting.getValuesRange();
        int minValue = valuesRange.getLowerBound();
        int maxValue = valuesRange.getHigherBound();

        int maxAnalogSensitivity = isoSetting.getMaxAnalogValue();
        int k = maxValue / maxAnalogSensitivity;
        k = Integers.nextPowerOfTwo(k);
        maxValue = maxAnalogSensitivity * k;

        valuesRange = new Range<>(minValue, maxValue);
        return valuesRange.isInRange(value);

    }

    @Override
    Stream<Integer> getValidValues() throws UnsupportedSettingException {
        ExISOSetting isoSetting = getISOSetting();
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

    @Override
    Stream<Integer> getInvalidValues() throws UnsupportedSettingException {
        ExISOSetting isoSetting = getISOSetting();
        Range<Integer> valuesRange = isoSetting.getValuesRange();
        return Stream.of(
                valuesRange.getLowerBound() - 1,
                valuesRange.getHigherBound() + 1,
                0
        );
    }

    private ExISOSetting getISOSetting() {
        return camera.getISOSetting();
    }
}
