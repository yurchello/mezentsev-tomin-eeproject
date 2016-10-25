package com.airplaneSoft.translateMeDude.winApp.utils;

import javafx.scene.image.Image;

import java.util.ResourceBundle;

/**
 * Created by Mezentsev.Y on 10/21/2016.
 */
public class AppUtils {
    public static ResourceBundle resourceBundle = ResourceBundle.getBundle("tmd");
    public static Image ICON_IMAGE = new Image(AppUtils.class.getResourceAsStream("stage_icon.png"));

    public static String getStringProperty(String property) {
        return AppUtils.resourceBundle.getString(property);
    }
}
