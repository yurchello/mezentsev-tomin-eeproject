package com.airplaneSoft.translateMeDude.winApp.view;

import com.airplaneSoft.translateMeDude.winApp.utils.AppUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

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
    Button translateButton;

    public MainView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainView.fxml"), AppUtils.resourceBundle);
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
