package com.xrc.camera;

import com.xrc.camera.api.DefaultApi;
import com.xrc.camera.model.FocusMode;
import com.xrc.camera.model.Setting;
import com.xrc.camera.setting.AutoExposureCompensationSetting;
import com.xrc.camera.setting.AutoExposureLockSetting;
import com.xrc.camera.setting.CachedCameraSetting;
import com.xrc.camera.setting.CameraSetting;
import com.xrc.camera.setting.ExISOSetting;
import com.xrc.camera.setting.FocusModeSetting;
import com.xrc.camera.setting.ISOSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

@Component
public class Camera {
    private final DefaultApi swaggerApi;

    private final Set<Setting> supportedSettings;

    @Value("${camera.settings.cache}")
    private boolean settingsCache;

    @Autowired
    Camera(DefaultApi swaggerApi) {
        this.swaggerApi = swaggerApi;

        supportedSettings = new HashSet<>(swaggerApi.settingsGet());
    }

    public BufferedImage getImage() {
        byte[] imageBytes = swaggerApi.imageGet();
        try (InputStream is = new ByteArrayInputStream(imageBytes)) {
            return ImageIO.read(is);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CameraSetting<Float> getAECompensationSetting() {
        return getSetting(Setting.AE_COMPENSATION);
    }

    public CameraSetting<Boolean> getAELockSetting() {
        return getSetting(Setting.AE_LOCK);
    }

    public CameraSetting<FocusMode> getFocusModeSetting() {
        return getSetting(Setting.FOCUS_MODE);
    }

    public ExISOSetting getISOSetting() {
        CameraSetting<Integer> isoSetting = getSetting(Setting.ISO);
        return new ExISOSetting(isoSetting);
    }

    public Set<Setting> getSupportedSettings() {
        return supportedSettings;
    }

    public DefaultApi getSwaggerApi() {
        return swaggerApi;
    }

    public <T> CameraSetting<T> getSetting(Setting setting) {

        CameraSetting<?> cameraSetting;
        switch (setting) {
            case ISO:
                cameraSetting = new ISOSetting(swaggerApi);
                break;
            case AE_LOCK:
                cameraSetting = new AutoExposureLockSetting(swaggerApi);
                break;
            case AE_COMPENSATION:
                cameraSetting = new AutoExposureCompensationSetting(swaggerApi);
                break;
            case FOCUS_MODE:
                cameraSetting = new FocusModeSetting(swaggerApi);
                break;
            default:
                throw new UnsupportedOperationException("Method not implemented for setting " + setting);
        }

        //noinspection unchecked
        CameraSetting<T> typedCameraSetting = (CameraSetting<T>) cameraSetting;

        if (settingsCache)
            typedCameraSetting = new CachedCameraSetting<>(typedCameraSetting);

        return typedCameraSetting;

    }

}
