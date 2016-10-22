package com.airplaneSoft.translateMeDude.winApp.settings.settingsView;

import com.airplaneSoft.translateMeDude.winApp.AppInitializer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static com.airplaneSoft.translateMeDude.winApp.AppInitializer.ICON_IMAGE;
import static com.airplaneSoft.translateMeDude.winApp.AppInitializer.getStringProperty;

/**
 * Created by Mezentsev.Y on 10/21/2016.
 */
public class SettingsDialogView extends Dialog {

    public SettingsDialogView() {
        loadFXML();
    }

    private void loadFXML() {
        String view;
        try {
            view = "settingsDialogView.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(view), AppInitializer.resourceBundle);
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.getIcons().add(ICON_IMAGE);
        setTitle(getStringProperty("ui.settings.header"));
        final Button ok_b = (Button) getDialogPane().lookupButton(ButtonType.OK);
        final Button apply_b = (Button) getDialogPane().lookupButton(ButtonType.APPLY);
        final Button cancel_b = (Button) getDialogPane().lookupButton(ButtonType.CANCEL);
    }

}
