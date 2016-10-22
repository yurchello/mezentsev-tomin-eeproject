package com.airplaneSoft.translateMeDude.winApp;

import javafx.scene.image.Image;

import java.util.ResourceBundle;

/**
 * Created by Mezentsev.Y on 10/21/2016.
 */
public class AppInitializer {
    public static ResourceBundle resourceBundle = ResourceBundle.getBundle("tmd");
    public static Image ICON_IMAGE = new Image(AppInitializer.class.getResourceAsStream("stage_icon.png"));

    public static String getStringProperty(String property) {
        return AppInitializer.resourceBundle.getString(property);
    }
}
