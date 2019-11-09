package com.xrc.camera.setting;


import com.xrc.camera.Camera;
import com.xrc.camera.model.Setting;
import com.xrc.util.Collections;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RandomSetTest {

    @Autowired
    private Camera camera;

    private Logger log;

    private CameraSetting<Object> cameraSetting;
    private Object initialValue;

    @Before
    public void before() {
        Collection<Setting> supportedSettings = camera.getSupportedSettings();
        List<Setting> settingsList = new ArrayList<>(supportedSettings);
        Setting setting;
        do {
            setting = Collections.getRandomElement(settingsList);
            cameraSetting = camera.getSetting(setting);
        } while (!cameraSetting.isEditable());

        log = Logger.getLogger(String.format("%s(%s)", this.getClass().getSimpleName(), setting));

        initialValue = cameraSetting.getValue();
    }

    @Test
    @Repeat(100)
    public void execute() {
        List<Object> values = cameraSetting.getValues();
        Object value = Collections.getRandomElement(values);

        log.info("Setting value: " + value);
        cameraSetting.setValue(value);
    }

    @After
    public void after() {
        cameraSetting.setValue(initialValue);
    }


}
