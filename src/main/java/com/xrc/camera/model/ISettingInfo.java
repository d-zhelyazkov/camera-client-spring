package com.xrc.camera.model;

import java.util.List;

public interface ISettingInfo<T> {

    Setting getSetting();

    Boolean isEditable();

    T getValue();

    List<T> getValues();
}
