package com.xrc.camera.setting;

import com.xrc.camera.model.AEMode;
import com.xrc.camera.model.Setting;
import com.xrc.camera.setting.util.SettingTestHandler;
import com.xrc.util.Collections;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Stream;


@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AEModeTest extends CameraSettingTestBase<AEMode> {

    @PostConstruct
    public void init() {
        SettingTestHandler<AEMode> settingHandler =
                new SettingTestHandler<>(Setting.AE_MODE, getFocusModeSetting());
        setSettingHandler(settingHandler);
    }

    @Override
    Stream<AEMode> getValidValues() {
        CameraSetting<AEMode> focusModeSetting = getFocusModeSetting();
        return focusModeSetting.getValues().stream();
    }

    @Override
    Stream<AEMode> getInvalidValues() {
        CameraSetting<AEMode> focusModeSetting = getFocusModeSetting();
        List<AEMode> supportedValues = focusModeSetting.getValues();
        return Collections.difference(AEMode.values(), supportedValues).stream();
    }

    private CameraSetting<AEMode> getFocusModeSetting() {
        return camera.getAEModeSetting();
    }

}