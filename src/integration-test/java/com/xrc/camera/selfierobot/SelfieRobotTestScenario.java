package com.xrc.camera.selfierobot;


import com.xrc.camera.Camera;
import com.xrc.camera.Constants;
import com.xrc.camera.ImageVerifier;
import com.xrc.camera.model.AEMode;
import com.xrc.camera.model.FocusMode;
import com.xrc.camera.model.Setting;
import com.xrc.camera.setting.CameraSetting;
import com.xrc.camera.setting.ExShutterSpeedSetting;
import com.xrc.camera.util.SettingsBackup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SelfieRobotTestScenario {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    private final ImageVerifier imageVerifier = new ImageVerifier();

    @Autowired
    private Camera camera;

    @Autowired
    private SelfieRobotTestProperties properties;

    private CameraSetting<FocusMode> focusMode;
    private CameraSetting<Boolean> aeLock;
    private CameraSetting<Float> aeCompensation;
    private CameraSetting<AEMode> aeMode;
    private CameraSetting<Integer> iso;
    private ExShutterSpeedSetting shutterSpeed;

    private SettingsBackup settingsBackup;

    @Before
    public void before() {
        aeLock = camera.getAELockSetting();
        aeCompensation = camera.getAECompensationSetting();
        aeMode = camera.getAEModeSetting();
        iso = camera.getISOSetting();
        shutterSpeed = camera.getShutterSpeedSetting();

        settingsBackup = new SettingsBackup(
                iso, shutterSpeed, aeMode, aeLock, aeCompensation
        );

        if (camera.getSupportedSettings().contains(Setting.FOCUS_MODE)) {
            focusMode = camera.getFocusModeSetting();
            settingsBackup.save(focusMode);
        }
    }

    @Test
    @Repeat(3)
    public void executeScenario() throws IOException, InterruptedException {

        RenderedImage processImage = null;
        for (int j = 0; j < properties.getIterations(); j++) {
            log.info("ITERATION START.");
            Thread.sleep(properties.getDelay());

            processImage = retrieveProcessImage();
            imageVerifier.verify(processImage);

        }

        assert processImage != null;
        String procFileName = Constants.IMAGE_FILE_DATE_FORMAT.format(new Date()) + "_processImage.jpg";
        ImageIO.write(processImage, "JPG", new File(procFileName));
        log.info("Process image saved to file " + procFileName);

        RenderedImage photo = camera.getImage();
        imageVerifier.verify(photo);
        String photoFileName = Constants.IMAGE_FILE_DATE_FORMAT.format(new Date()) + "_photo.jpg";
        ImageIO.write(photo, "JPG", new File(photoFileName));
        log.info("Photo saved to file " + photoFileName);

        Thread.sleep(properties.getDelay());

    }

    @After
    public void after() {
        restoreSettings();
    }


    private BufferedImage retrieveProcessImage() {
        setOptimalSettings();

        BufferedImage image = camera.getImage();
        log.info("Process image obtained.");

        restoreSettings();

        return image;
    }

    private void setOptimalSettings() {
        if (focusMode != null)
            focusMode.setValue(FocusMode.MACRO);

        Float lowestCompensation = aeCompensation.getValues().get(0);
        aeCompensation.setValue(lowestCompensation);

        aeLock.setValue(true);

        aeMode.setValue(AEMode.OFF);

        Integer lowestIso = iso.getValues().get(0);
        iso.setValue(lowestIso);

        Long fastestShutter = shutterSpeed.getValuesRange().getLowerBound();
        shutterSpeed.setValue(fastestShutter);

        log.info("Optimal settings set.");
    }

    private void restoreSettings() {
        settingsBackup.restore();

        log.info("Settings restored.");
    }

}
