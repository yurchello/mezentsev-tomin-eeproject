package com.airplaneSoft.translateMeDude.winApp;

import com.airplaneSoft.translateMeDude.winApp.settings.settingsView.SettingsDialogView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Mezentsev.Y on 10/21/2016.
 */
public class MainView extends VBox {
    @FXML
    GridPane gridPane;
    @FXML
    TextField wordTranslationTextField;
    @FXML
    Label statusLabel;
    @FXML
    Label translationLabel;
    @FXML
    TextArea exampleTextArea;
    @FXML
    Button updateButton;
    @FXML
    Button translateButton;




    public MainView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainView.fxml"), AppInitializer.resourceBundle);
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
    @FXML
    private void initialize() {

    }
}
