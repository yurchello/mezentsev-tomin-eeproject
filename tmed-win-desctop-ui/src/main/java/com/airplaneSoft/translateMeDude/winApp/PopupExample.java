package com.airplaneSoft.translateMeDude.winApp;


import com.airplaneSoft.translateMeDude.winApp.dialogComponent.CloseButton;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.action.Action;

/**
 * Created by Mezentsev.Y on 10/20/2016.
 */
public class PopupExample extends Application {
    volatile boolean isShown;


    @Override
    public void start(Stage primaryStage) throws Exception {
    }


    public static void main(String[] args) throws InterruptedException {
        new JFXPanel();
        for (int i = 0; i < 2; i++) {
            notifier("Good! " + i, "It's working now!");
        }


    }

    private static void notifier(String pTitle, String pMessage) {

        Platform.runLater(() -> {
                    Stage owner = new Stage(StageStyle.TRANSPARENT);
                    StackPane root = new StackPane();
                    root.setStyle("-fx-background-color: TRANSPARENT");
                    Scene scene = new Scene(root, 1, 1);
                    scene.setFill(Color.TRANSPARENT);
                    owner.setScene(scene);
                    owner.setWidth(1);
                    owner.setHeight(1);
                    owner.toBack();
                    owner.show();

                    Button button = new Button("dffffffffffffffiiiiffffffff");

                    Pane pane = new Pane();
                    pane.getChildren().add(button);
//                    Notifications notifications = getNotifications(pane);
//                    notifications.show();
//            button.setOnAction((event) -> {
//                notifications.hideAfter(Duration.ONE);
//            });

                    PopOver popOver = new PopOver();
                    MainView mainView = new MainView();
                    popOver.setContentNode(mainView);
                    popOver.setArrowLocation(PopOver.ArrowLocation.BOTTOM_CENTER);
                    popOver.show(owner);

                }
        );
    }

    public static Notifications getNotifications(Node content) {

        GridPane gridPane = new GridPane();
        //Rectangle rectangle = new Rectangle(7,7);
        CloseButton closeButton = new CloseButton(null);
        HBox titleHBox = new HBox(new Label("TranslateMe, dude!"));
        titleHBox.setAlignment(Pos.BASELINE_RIGHT);
        HBox closeHBox = new HBox(closeButton);
        closeHBox.setAlignment(Pos.BASELINE_RIGHT);

        gridPane.add(titleHBox, 0, 0);
        gridPane.add(closeButton, 1, 0);
        setGridStyle(closeButton);

        VBox vBox = new VBox(gridPane, content);
        return Notifications.create()

                //.title("TranslateMe, dude!")
                .hideAfter(Duration.INDEFINITE)
                .graphic(vBox)
                .darkStyle()
                .hideCloseButton();

    }

    private static void setGridStyle(Node... nodes) {
        for (Node node : nodes) {
            GridPane.setMargin(node, new Insets(0, 3, 0, 0));
            GridPane.setHalignment(node, HPos.RIGHT);
            GridPane.setHgrow(node, Priority.SOMETIMES);
        }
    }

    private void ex2() {
        Notifications.create()
                .title("Title Text")
                .text("Hello World 0!")
                .showWarning();
    }

}
