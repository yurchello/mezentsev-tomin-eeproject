package com.airplaneSoft.translateMeDude.winApp.view;

import com.airplaneSoft.translateMeDude.core.translationVerefier.TranslationVerifierImpl;
import com.airplaneSoft.translateMeDude.winApp.App;
import com.airplaneSoft.translateMeDude.winApp.utils.AppUtils;
import com.airplaneSoft.translateMeDude.winApp.viewModel.ForeignNativeMainViewModel;
import com.airplaneSoft.translateMeDude.winApp.viewModel.MainViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.io.IOException;

/**
 * Main popup view
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
            System.out.println("Translate button pressed");
        });

        //handling the enter key pressed
        wordTranslationTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) translate();
//            if (keyEvent.getCode() == KeyCode.ESCAPE) {
//                App.getMainStage().hide();
//                App.setIsShow(false);
//                keyEvent.consume();
//            }
        });
        wordTranslationTextField.setOnMouseClicked(event -> {
            App.getMainStage().requestFocus();
            System.out.println("Mouse clicked on wordTranslationTextField");
        });
    }

    /**
     * start translation verification
     */
    private void translate(){
        //add content to view status, examples and correct translation
        getChildren().remove(helpVBox);
        getChildren().add(1,helpVBox);
        viewModel.translationPropertyProperty();
        setStatusLabel(true, viewModel.isCorrectTranslate());
    }

    /**
     * Set view style of status label only
     * @param setVisible
     * @param isCorrect
     */
    private void setStatusLabel(boolean setVisible, boolean isCorrect){
        statusLabel.setVisible(setVisible);
        if (isCorrect){
            statusLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #79c963; -fx-font-size: 15px;");
            statusLabel.setText(AppUtils.getStringProperty("ui.mainView.label.status.passed"));
        }else {
            statusLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #ff2a36;");
            statusLabel.setText(AppUtils.getStringProperty("ui.mainView.label.status.failed"));
        }
    }
}
