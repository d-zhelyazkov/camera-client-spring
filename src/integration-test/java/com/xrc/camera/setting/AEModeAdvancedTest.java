package com.xrc.camera.setting;

import com.xrc.camera.Camera;
import com.xrc.camera.model.AEMode;
import com.xrc.camera.util.SettingsBackup;
import com.xrc.lang.Numbers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AEModeAdvancedTest {

    @Autowired
    private Camera camera;

    @Value("${test.iso.maxValueError}")
    private int isoMaxError;

    @Value("${test.iso.manual.maxValueError}")
    private int isoMaxManualError;

    private CameraSetting<AEMode> aeModeSetting;
    private ExISOSetting isoSetting;

    private SettingsBackup settingsBackup;

    @PostConstruct
    public void init() {
        aeModeSetting = camera.getAEModeSetting();
        isoSetting = camera.getISOSetting();
    }

    @Before
    public void before() {
        settingsBackup = new SettingsBackup(
                aeModeSetting,
                isoSetting
        );
    }

    @Test
    public void testAeOn() {
        List<AEMode> values = aeModeSetting.getValues();
        if (!values.contains(AEMode.ON))
            return;

        aeModeSetting.setValue(AEMode.ON);

        for (Integer isoValue : getIsoTestValues()) {
            setUneditableIsoValue(isoValue);
        }

    }

    @Test
    public void testAeOff() {
        List<AEMode> values = aeModeSetting.getValues();
        if (!values.contains(AEMode.OFF))
            return;

        aeModeSetting.setValue(AEMode.OFF);

        for (Integer isoValue : getIsoTestValues()) {
            setIsoValue(isoValue);
        }

    }

    private void setIsoValue(int isoValue) {

        isoSetting.setValue(isoValue);

        Integer setValue = isoSetting.getValue();
        Comparator<Integer> comparator = Numbers.getComparatorWithError(isoMaxManualError);
        //noinspection SimplifiableJUnitAssertion
        Assert.assertTrue(comparator.compare(setValue, isoValue) == 0);
    }

    private void setUneditableIsoValue(int isoValue) {
        int initialValue = isoSetting.getValue();
        try {
            isoSetting.setValue(isoValue);
            Assert.fail();
        } catch (HttpClientErrorException.BadRequest e) {
            Integer finalValue = isoSetting.getValue();

            Comparator<Integer> comparator = Numbers.getComparatorWithError(isoMaxError);
            //noinspection SimplifiableJUnitAssertion
            Assert.assertTrue(comparator.compare(finalValue, initialValue) == 0);
        }
    }

    private Collection<Integer> getIsoTestValues() {
        List<Integer> isoValues = isoSetting.getValues();
        return List.of(
                isoValues.get(0),
                isoValues.get(isoValues.size() - 1)
        );
    }

    @After
    public void after() {
        settingsBackup.restore();
    }

}
