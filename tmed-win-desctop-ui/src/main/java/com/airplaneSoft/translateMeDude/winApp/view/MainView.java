package com.airplaneSoft.translateMeDude.winApp.view;

import com.airplaneSoft.translateMeDude.core.translationVerefier.TranslationVerifierImpl;
import com.airplaneSoft.translateMeDude.winApp.App;
import com.airplaneSoft.translateMeDude.winApp.utils.AppUtils;
import com.airplaneSoft.translateMeDude.winApp.viewModel.ForeignNativeMainViewModel;
import com.airplaneSoft.translateMeDude.winApp.viewModel.MainViewModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
    Label wordLabel;
    @FXML
    Label correctTranslationLabel;
    @FXML
    TextArea exampleTextArea;
    @FXML
    Button translateButton;
    @FXML
    VBox helpVBox;

    private MainViewModel viewModel;

    public MainView() {
        this.viewModel = new ForeignNativeMainViewModel(new TranslationVerifierImpl());
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
        getChildren().remove(helpVBox);
        wordLabel.textProperty().bind(viewModel.wordPropertyProperty());
        correctTranslationLabel.textProperty().bind(viewModel.correctTranslationPropertyProperty());
        viewModel.translationPropertyProperty().bind(wordTranslationTextField.textProperty());
        exampleTextArea.textProperty().bind(viewModel.descriptionPropertyProperty());

        translateButton.addEventFilter(ActionEvent.ACTION, (event) -> {
           translate();
        });

        wordTranslationTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) translate();
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                //App.getMainStage().hide();
            }
        });
    }

    private void translate(){
        viewModel.translationPropertyProperty();
        setStatusLabel(true, viewModel.isCorrectTranslate());
        System.out.println("Translate button pressed");
    }

    private void setStatusLabel(boolean setVisible, boolean isCorrect){
        getChildren().add(1,helpVBox);
        statusLabel.setVisible(setVisible);
        if (isCorrect){
            statusLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: green");
            statusLabel.setText(AppUtils.getStringProperty("ui.mainView.label.status.passed"));
        }else {
            statusLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: red");
            statusLabel.setText(AppUtils.getStringProperty("ui.mainView.label.status.failed"));
        }
    }
}
