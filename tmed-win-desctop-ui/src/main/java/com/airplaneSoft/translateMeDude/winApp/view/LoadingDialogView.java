package com.airplaneSoft.translateMeDude.winApp.view;

import com.airplaneSoft.translateMeDude.winApp.utils.AppUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by Mezentsev.Y on 10/25/2016.
 */
public class LoadingDialogView extends Alert {

    private final ImageView IMAGE_VIEW = new ImageView(new Image(LoadingDialogView.class.getResourceAsStream("loading32x32.gif")));
    public LoadingDialogView(String information) {
        super(AlertType.INFORMATION);
        ((Stage)getDialogPane().getScene().getWindow()).getIcons().add(AppUtils.ICON_IMAGE);
        setHeaderText(null);
        getDialogPane().setContent(new Label(information));
        setGraphic(IMAGE_VIEW);
        getButtonTypes().clear();
        setResult(ButtonType.CLOSE);
        initModality(Modality.WINDOW_MODAL);
        initStyle(StageStyle.UNDECORATED);
        getDialogPane().setStyle("-fx-padding: 3 0 0 0;-fx-border-color: midnightblue;-fx-border-width: 2px;");
        getDialogPane().setPrefWidth(200);
      ;
    }


}
