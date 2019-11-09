package com.xrc.camera.setting;

import com.xrc.camera.model.FocusMode;
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
public class FocusModeTest extends CameraSettingTestBase<FocusMode> {

    @PostConstruct
    public void init() {
        SettingTestHandler<FocusMode> settingHandler =
                new SettingTestHandler<>(Setting.FOCUS_MODE, getFocusModeSetting());
        setSettingHandler(settingHandler);
    }

    @Override
    Stream<FocusMode> getValidValues() {
        CameraSetting<FocusMode> focusModeSetting = getFocusModeSetting();
        return focusModeSetting.getValues().stream();
    }

    @Override
    Stream<FocusMode> getInvalidValues() {
        CameraSetting<FocusMode> focusModeSetting = getFocusModeSetting();
        List<FocusMode> supportedValues = focusModeSetting.getValues();
        return Collections.difference(FocusMode.values(), supportedValues).stream();
    }

    private CameraSetting<FocusMode> getFocusModeSetting() {
        return camera.getFocusModeSetting();
    }

}
