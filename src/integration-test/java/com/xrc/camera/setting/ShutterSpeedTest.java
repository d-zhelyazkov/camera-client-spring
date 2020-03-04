package com.xrc.camera.setting;

import com.xrc.camera.setting.util.SSTools;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShutterSpeedTest extends CameraSettingTestBase<Long> {

    @PostConstruct
    public void init() {
        setSettingHandler(SSTools.getTestHandler(getSSSetting()));
    }

    @Override
    Stream<Long> getValidValues() throws UnsupportedSettingException {

        return SSTools.getValidValues(getSSSetting());
    }

    @Override
    Stream<Long> getInvalidValues() throws UnsupportedSettingException {
        return SSTools.getInvalidValues(getSSSetting());
    }

    private ExShutterSpeedSetting getSSSetting() {
        return camera.getShutterSpeedSetting();
    }
}
