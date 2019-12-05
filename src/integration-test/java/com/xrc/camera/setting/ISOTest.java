package com.xrc.camera.setting;

import com.xrc.camera.setting.util.ISOTools;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ISOTest extends CameraSettingTestBase<Integer> {

    @Value("${test.iso.maxValueError}")
    private int maxValueError;

    @PostConstruct
    public void init() {
        setSettingHandler(ISOTools.getTestHandler(getISOSetting(), maxValueError));
    }

    @Override
    Stream<Integer> getValidValues() throws UnsupportedSettingException {
        return ISOTools.getValidValues(getISOSetting());
    }

    @Override
    Stream<Integer> getInvalidValues() throws UnsupportedSettingException {
        return ISOTools.getInvalidValues(getISOSetting());
    }

    private ExISOSetting getISOSetting() {
        return camera.getISOSetting();
    }
}
