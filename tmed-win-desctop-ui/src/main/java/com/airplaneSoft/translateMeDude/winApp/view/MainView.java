package com.airplaneSoft.translateMeDude.winApp.view;

import com.airplaneSoft.translateMeDude.winApp.utils.AppUtils;
import com.airplaneSoft.translateMeDude.winApp.viewModel.ForeignNativeMainViewModel;
import com.airplaneSoft.translateMeDude.winApp.viewModel.MainViewModel;
import javafx.event.ActionEvent;
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
    Label wordLabel;
    @FXML
    Label correctTranslationLabel;
    @FXML
    TextArea exampleTextArea;
    @FXML
    Button translateButton;

    private MainViewModel viewModel = new ForeignNativeMainViewModel();

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
        wordLabel.textProperty().bind(viewModel.wordPropertyProperty());
        correctTranslationLabel.textProperty().bind(viewModel.correctTranslationPropertyProperty());
        viewModel.translationPropertyProperty().bind(wordTranslationTextField.textProperty());
        exampleTextArea.textProperty().bind(viewModel.descriptionPropertyProperty());

        translateButton.addEventFilter(ActionEvent.ACTION, (event) -> {
viewModel.translationPropertyProperty();
            //todo call comparsion module
            System.out.println("Translate button pressed");
        });

    }
}
