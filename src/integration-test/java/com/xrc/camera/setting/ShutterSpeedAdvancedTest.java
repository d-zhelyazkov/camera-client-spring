package com.xrc.camera.setting;

import com.xrc.camera.Camera;
import com.xrc.camera.model.AEMode;
import com.xrc.camera.setting.util.SSTools;
import com.xrc.camera.setting.util.SettingTestHandler;
import com.xrc.camera.util.SettingsBackup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShutterSpeedAdvancedTest {

    @Autowired
    private Camera camera;

    @Value("${test.ss.maxValueError}")
    private int maxValueError;

    private SettingTestHandler<Long> ssTestHandler;

    private SettingsBackup settingsBackup;

    @PostConstruct
    public void init() {
        ssTestHandler = SSTools.getTestHandler(camera.getShutterSpeedSetting(), maxValueError);
    }

    @Before
    public void before() {
        CameraSetting<AEMode> aeModeSetting = camera.getAEModeSetting();
        settingsBackup = new SettingsBackup(aeModeSetting);

        aeModeSetting.setValue(AEMode.OFF);
    }

    @Test
    public void testValidValues() {
        ssTestHandler.setValidValuesSupplier(() -> SSTools.getValidValues(camera.getShutterSpeedSetting()));
        ssTestHandler.testValidValues();
    }

    @Test
    public void testInvalidValues() {
        ssTestHandler.setInvalidValuesSupplier(() -> SSTools.getInvalidValues(camera.getShutterSpeedSetting()));
        ssTestHandler.testInvalidValues();
    }

    @After
    public void after() {
        settingsBackup.restore();
    }
}
