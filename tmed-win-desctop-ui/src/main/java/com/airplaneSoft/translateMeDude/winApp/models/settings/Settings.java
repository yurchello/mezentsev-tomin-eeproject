package com.airplaneSoft.translateMeDude.winApp.models.settings;

import java.util.Map;

public interface Settings {

    void clearSettingsMap();
    Map<String,String> getSettingsMap();
    void set(String key, Object value);
    String get(String key);
    void save();
    void load();


    default boolean getBoolean(String key) {
        String rawValue = get(key);
        if ("TRUE".equalsIgnoreCase(rawValue.trim())){
            return true;
        }else {
            return false;
        }
    }




}
