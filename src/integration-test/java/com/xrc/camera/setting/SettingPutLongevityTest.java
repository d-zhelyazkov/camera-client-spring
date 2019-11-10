package com.xrc.camera.setting;

import com.xrc.camera.api.DefaultApi;
import com.xrc.camera.model.AELockInfo;
import com.xrc.camera.model.AELockValue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.logging.Logger;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SettingPutLongevityTest {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    @Autowired
    private DefaultApi api;

    private boolean initialValue;

    @Before
    public void before() {
        initialValue = getValue();
    }

    @Test
    @Repeat(100)
    public void execute() {
        setValue(false);
    }

    @After
    public void after() {
        setValue(initialValue);
    }

    private Boolean getValue() {
        AELockInfo aeLockInfo = api.settingsAELOCKGet();
        return aeLockInfo.getValue();
    }

    private void setValue(boolean value) {
        log.info("Setting value.");
        api.settingsAELOCKPut(
                new AELockValue()
                        .value(value)
        );
    }

}
