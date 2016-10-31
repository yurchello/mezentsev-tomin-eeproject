package com.airplaneSoft.translateMeDude.winApp.utils;

import javafx.scene.image.Image;

import java.util.ResourceBundle;

/**
 * Utils class that provides basic functionality for resources getting etc.
 */
public class AppUtils {
    public static ResourceBundle resourceBundle = ResourceBundle.getBundle("tmd");
    public static Image ICON_IMAGE = new Image(AppUtils.class.getResourceAsStream("stage_icon.png"));

    public static String getStringProperty(String property) {
        return AppUtils.resourceBundle.getString(property);
    }
}
