package com.airplaneSoft.translateMeDude.winApp.view.components;

import com.airplaneSoft.translateMeDude.winApp.App;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Main popup close button component
 */
public class CloseButton extends Parent {
    private final static String SELECT_BUTTON = "cls_select.png";
    private final static String NOT_SELECT_BUTTON = "cls_not_select.png";
    private final ImageView imageView;

    public CloseButton(Stage stageToClose) {
        Image select = new Image(getClass().getResourceAsStream(SELECT_BUTTON));
        Image notSelect = new Image(getClass().getResourceAsStream(NOT_SELECT_BUTTON));

        this.imageView = new ImageView(notSelect);
        this.getChildren().add(this.imageView);
        this.imageView.setOnMousePressed(evt -> {
            imageView.setImage(notSelect);
            stageToClose.close();
            imageView.setImage(select);
            App.setIsShow(false);
        });

        imageView.setOnMouseReleased(evt -> {
            imageView.setImage(select);
        });
        imageView.setOnMouseEntered(evt -> {
            imageView.setImage(select);
        });
        imageView.setOnMouseExited(evt -> {
            imageView.setImage(notSelect);
        });

    }

}
