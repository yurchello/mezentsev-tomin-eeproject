package com.airplaneSoft.translateMeDude.winApp.models.settings;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Mezentsev.Y on 7/18/2016.
 */
public class SettingModel {
    private String key;
    private SimpleStringProperty value;
    private Settings settings;

    public SettingModel(String key, String value) {
        this.key = key;
        this.value = new SimpleStringProperty(this, "value", value);
        this.settings = SettingsImpl.getInstance();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value.get();
    }

    public SimpleStringProperty valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    public void save() {
        settings.set(this.key, this.value.getValue());
    }

    @Override
    public String toString() {
        return "SettingModel{" +
                "key='" + key + '\'' +
                ", value=" + value.get() +
                '}';
    }

}
