package com.airplaneSoft.translateMeDude.winApp.utils;

import com.airplaneSoft.translateMeDude.winApp.App;
import com.airplaneSoft.translateMeDude.winApp.AppUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.Validator;

import java.text.MessageFormat;

/**
 * Created by Mezentsev.Y on 10/24/2016.
 */
public class GuiUtils {

    public static final Validator<String> TEN_DIGIT_LIMITED_VALIDATOR = new Validator<String>() {

        private boolean zeroVerifying(String value){
            return !(value.length() > 1 && value.charAt(0) == '0');
        }
        @Override
        public ValidationResult apply(Control control, String value) {
            final Integer MAX_VAL = 1000;
            final Integer MIN_VAL = 1;
            String err_message = null;
            boolean isAllowableNumber = true;
            try {
                if (!zeroVerifying(value))isAllowableNumber = false;
                if (Integer.parseInt(value) < MIN_VAL || Integer.parseInt(value) > MAX_VAL) {
                    isAllowableNumber = false;
                }
            } catch (NumberFormatException nfe) {
                isAllowableNumber = false;
                err_message = MessageFormat.format(AppUtils.getStringProperty("ui.settings.validator"), MAX_VAL);
            }
            boolean condition =
                    value == null || (!(value
                            .matches(
                                    "[\\d]{1,10}") && isAllowableNumber));
            return ValidationResult.fromMessageIf(control, err_message, Severity.ERROR, condition);
        }
    };

    public static void showInfoAlert(String title, String info){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(AppUtils.ICON_IMAGE);
        alert.setTitle(title);
        alert.setHeaderText(info);
        alert.show();
    }

    public static void showErrorAlert(String title, String info){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(AppUtils.ICON_IMAGE);
        alert.setTitle(title);
        alert.setHeaderText(info);
        alert.show();
    }

    public static Alert createAlert(Alert.AlertType alertType, String title, String header, String text) {
        Alert alert = new Alert(alertType);
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(AppUtils.ICON_IMAGE);
        return alert;
    }

}

