package com.airplaneSoft.translateMeDude.winApp.view;

import com.airplaneSoft.translateMeDude.winApp.App;
import com.airplaneSoft.translateMeDude.winApp.viewModel.SettingsDialogViewModel;
import com.airplaneSoft.translateMeDude.winApp.utils.AppUtils;
import com.airplaneSoft.translateMeDude.winApp.models.settings.SettingsKeys;
import com.airplaneSoft.translateMeDude.winApp.utils.GuiUtils;
import javafx.beans.binding.Bindings;
import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.converter.BooleanStringConverter;
import org.apache.log4j.Logger;
import org.controlsfx.validation.ValidationSupport;

import java.io.IOException;

import static com.airplaneSoft.translateMeDude.winApp.utils.AppUtils.ICON_IMAGE;
import static com.airplaneSoft.translateMeDude.winApp.utils.AppUtils.getStringProperty;

/**
 * Settings dialog view component
 */
public class SettingsDialogView extends Dialog {
    private static final Logger LOGGER = Logger.getLogger(SettingsDialogView.class);

    @FXML
    TextField urlField;
    @FXML
    TextField ssoidField;
    @FXML
    CheckBox showCheckBox;
    @FXML
    TextField passwordField;
    @FXML
    RadioButton rbRandom;
    @FXML
    RadioButton rbTimer;
    @FXML
    HBox timerHBox;
    @FXML
    TextField timerValueField;
    @FXML
    Button testButton;

    private SettingsDialogViewModel viewModel = new SettingsDialogViewModel();

    public SettingsDialogView() {
        loadFXML();
    }

    private void loadFXML() {
        String view;
        try {
            view = "settingsDialogView.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(view), AppUtils.resourceBundle);
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
        final Button okButton = (Button) getDialogPane().lookupButton(ButtonType.OK);
        final Button cancelButton = (Button) getDialogPane().lookupButton(ButtonType.CANCEL);
        final Button applyButton = (Button) getDialogPane().lookupButton(ButtonType.APPLY);
        initUIComponents();

        okButton.addEventFilter(ActionEvent.ACTION, (event) -> {
            viewModel.saveToFile();
            LOGGER.info("Ok settings button pressed");
        });

        applyButton.addEventFilter(ActionEvent.ACTION, (event) -> {
            viewModel.saveModel();
            LOGGER.info("Apply settings button pressed");
            event.consume();
        });

        cancelButton.addEventFilter(ActionEvent.ACTION, (event) -> {
            LOGGER.info("Cancel settings button pressed");
        });

        //invalid characters handling. Ok and apply buttons will be disabled,
        // if the invalid characters are present.
        viewModel.getInvalidNodes().addListener((SetChangeListener<Node>) change -> {
            okButton.disableProperty().setValue(viewModel.getInvalidNodes().size() != 0);
            applyButton.disableProperty().setValue(viewModel.getInvalidNodes().size() != 0);
        });

        testButton.addEventFilter(ActionEvent.ACTION, (event) -> {
            viewModel.testConnection();
            LOGGER.info("Test connection button pressed");
        });
    }

    /**
     * UI component initialization and bindings initialization
     */
    private void initUIComponents(){
        urlField.textProperty().bindBidirectional(viewModel.getSettingModel(SettingsKeys.URL).valueProperty());
        ssoidField.textProperty().bindBidirectional(viewModel.getSettingModel(SettingsKeys.SSOID).valueProperty());
        passwordField.textProperty().bindBidirectional(viewModel.getSettingModel(SettingsKeys.PASSWORD).valueProperty());

        timerValueField.textProperty().bindBidirectional(viewModel.getSettingModel(SettingsKeys.TIMER_VALUE).valueProperty());
        ValidationSupport validationSupport = new ValidationSupport();
        validationSupport.invalidProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                viewModel.getInvalidNodes().add(timerValueField);
            }else {
                viewModel.getInvalidNodes().remove(timerValueField);
            }
        });
        validationSupport.registerValidator(timerValueField, true, GuiUtils.TEN_DIGIT_LIMITED_VALIDATOR);

        showCheckBox.selectedProperty().set(Boolean.valueOf(viewModel.getSettingModel(SettingsKeys.SHOW).getValue()));
        Bindings.bindBidirectional(viewModel.getSettingModel(SettingsKeys.SHOW).valueProperty(), showCheckBox.selectedProperty(), new BooleanStringConverter());

        ToggleGroup toggleGroup = new ToggleGroup();
        rbRandom.setToggleGroup(toggleGroup);
        rbTimer.setToggleGroup(toggleGroup);
        String timerMode = viewModel.getSettingModel(SettingsKeys.SHOW_TIMER).getValue();
        if (SettingsKeys.TimerValues.RANDOM.equals(timerMode)){
            rbRandom.selectedProperty().set(true);
            timerHBox.setDisable(true);
        }else if(SettingsKeys.TimerValues.TIMER.equals(timerMode)){
            rbTimer.selectedProperty().set(true);
            timerHBox.setDisable(false);
        }
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            RadioButton radioButton = (RadioButton) (newValue.getToggleGroup().getSelectedToggle());
            switch (radioButton.getId()){
                case "rbRandom":
                    viewModel.getSettingModel(SettingsKeys.SHOW_TIMER).setValue(SettingsKeys.TimerValues.RANDOM);
                    timerHBox.setDisable(true);
                    break;
                case "rbTimer":
                    viewModel.getSettingModel(SettingsKeys.SHOW_TIMER).setValue(SettingsKeys.TimerValues.TIMER);
                    timerHBox.setDisable(false);
                    break;
            }
        });

    }

}
