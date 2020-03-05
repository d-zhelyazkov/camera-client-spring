package com.xrc.camera.setting;

import com.xrc.camera.Camera;
import com.xrc.camera.model.ISettingInfo;
import com.xrc.camera.model.Setting;
import com.xrc.util.Collections;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;
import java.util.logging.Logger;


@SpringBootTest
@RunWith(SpringRunner.class)
public class SettingsTest {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    @Autowired
    private Camera camera;


    @Test
    public void verifySupportedSettings() {
        Set<Setting> supportedSettings = camera.getSupportedSettings();
        log.info("Supported settings set: " + supportedSettings);

        verifySettings(supportedSettings,
                setting -> {
                    CameraSetting<Object> cameraSetting = camera.getSetting(setting);
                    ISettingInfo<Object> settingInfo = cameraSetting.getSettingInfo();
                    Assert.assertNotNull(settingInfo);
                });

    }

    @Test
    public void verifyUnsupportedSettings() {
        Collection<Setting> unsupportedSettings = Collections.difference(
                Setting.values(), camera.getSupportedSettings());
        log.info("Unsupported settings: " + unsupportedSettings);

        verifySettings(unsupportedSettings,
                setting -> {
                    try {
                        CameraSetting<Object> cameraSetting = camera.getSetting(setting);
                        ISettingInfo<Object> settingInfo = cameraSetting.getSettingInfo();
                        Assert.fail("Successfully accessed unsupported setting: " + settingInfo);
                    } catch (HttpClientErrorException.NotFound ignored) {

                    }
                });

    }

    private void verifySettings(Collection<Setting> settings, Consumer<Setting> settingVerifier) {
        Collection<Throwable> errors = new ArrayList<>();
        for (Setting setting : settings) {
            try {
                log.info("Testing setting " + setting);
                Assert.assertNotNull(setting);

                settingVerifier.accept(setting);
            } catch (Throwable e) {
                errors.add(e);
                e.printStackTrace();
            }
        }
        Assert.assertTrue(
                "Some settings are not valid: " + errors,
                errors.isEmpty());
    }
}
