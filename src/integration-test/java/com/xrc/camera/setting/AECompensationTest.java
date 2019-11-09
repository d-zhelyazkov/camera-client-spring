package com.xrc.camera.setting;

import com.xrc.camera.model.Setting;
import com.xrc.camera.setting.util.ComparableSettingTestHandler;
import com.xrc.camera.setting.util.SettingTestHandler;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;


@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AECompensationTest extends CameraSettingTestBase<Float> {

    @PostConstruct
    public void init() {
        SettingTestHandler<Float> settingHandler = new ComparableSettingTestHandler<>(
                Setting.AE_COMPENSATION,
                getAECompensationSetting());
        setSettingHandler(settingHandler);
    }

    @Override
    Stream<Float> getInvalidValues() {
        CameraSetting<Float> aeCompensationSetting = getAECompensationSetting();
        List<Float> supportedValues = aeCompensationSetting.getValues();
        List<Float> unsupportedValues = new ArrayList<>();
        Collections.addAll(unsupportedValues,
                Float.MIN_VALUE, Float.MAX_VALUE);

        int supportedValuesNum = supportedValues.size();
        if (supportedValuesNum == 0) {
            Collections.addAll(unsupportedValues,
                    0f, -1f, 1f);
        } else {
            Float firstValue = supportedValues.get(0);
            Float lastValue = supportedValues.get(supportedValuesNum - 1);
            Collections.addAll(unsupportedValues,
                    firstValue - 1, lastValue + 1);
            if (supportedValuesNum > 1) {
                Float secondValue = supportedValues.get(1);
                unsupportedValues.add((firstValue + secondValue) / 2);
            }
        }

        return unsupportedValues.stream();

    }

    private CameraSetting<Float> getAECompensationSetting() {
        return camera.getAECompensationSetting();
    }
}
