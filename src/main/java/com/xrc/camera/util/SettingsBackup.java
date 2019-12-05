package com.xrc.camera.util;

import com.xrc.camera.setting.CameraSetting;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class SettingsBackup {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    private final Collection<SettingBackupEntry<?>> backup = new ArrayList<>();

    public SettingsBackup(CameraSetting<?>... settings) {
        save(settings);
    }

    public void save(CameraSetting<?>... settings) {
        for (CameraSetting<?> setting : settings) {
            backup.add(new SettingBackupEntry<>(setting));
        }

    }

    public void restore() {
        for (SettingBackupEntry<?> settingBackup : backup) {
            try {
                settingBackup.restore();
            } catch (HttpClientErrorException.BadRequest e) {
                e.printStackTrace();
                log.warning(String.format("Unable to restore %s setting value %s",
                        settingBackup.getSetting(), settingBackup.getValue()));
            }
        }

    }

    private static class SettingBackupEntry<T> {
        private final CameraSetting<T> setting;

        private final T value;

        SettingBackupEntry(CameraSetting<T> setting) {
            this.setting = setting;
            value = setting.getValue();
        }

        void restore() {
            setting.setValue(value);
        }

        public CameraSetting<T> getSetting() {
            return setting;
        }

        public T getValue() {
            return value;
        }
    }
}
