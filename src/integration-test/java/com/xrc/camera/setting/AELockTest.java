package com.xrc.camera.setting;

import com.xrc.camera.model.Setting;
import com.xrc.camera.setting.util.SettingTestHandler;
import com.xrc.lang.Booleans;
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
public class AELockTest extends CameraSettingTestBase<Boolean> {

    @PostConstruct
    public void init() {
        SettingTestHandler<Boolean> settingHandler =
                new SettingTestHandler<>(Setting.AE_LOCK, getAELockSetting());
        setSettingHandler(settingHandler);

    }

    @Override
    Stream<Boolean> getValidValues() throws UnsupportedSettingException {
        CameraSetting<Boolean> aeLockSetting = getAELockSetting();
        return aeLockSetting.getValues().stream();
    }

    @Override
    Stream<Boolean> getInvalidValues() {
        CameraSetting<Boolean> aeLockSetting = getAELockSetting();
        List<Boolean> values = aeLockSetting.getValues();
        return Collections.difference(Booleans.values(), values).stream();
    }

    private CameraSetting<Boolean> getAELockSetting() throws UnsupportedSettingException {
        return camera.getAELockSetting();
    }

}
