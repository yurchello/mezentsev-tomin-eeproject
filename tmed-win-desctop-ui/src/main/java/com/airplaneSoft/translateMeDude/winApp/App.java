package com.airplaneSoft.translateMeDude.winApp;

/**
 * Created by Mezentsev.Y on 10/20/2016.
 */
import com.airplaneSoft.translateMeDude.winApp.dialogComponent.CloseButton;
import com.airplaneSoft.translateMeDude.winApp.settings.settingsModel.SettingsImpl;
import com.airplaneSoft.translateMeDude.winApp.settings.settingsView.SettingsDialogView;
import com.airplaneSoft.translateMeDude.winApp.utils.GuiUtils;
import javafx.application.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import javax.imageio.ImageIO;
import java.io.IOException;

import static com.airplaneSoft.translateMeDude.winApp.AppUtils.getStringProperty;

public class App extends Application {

    private static final String TRAY_IMAGE_LOCATION = "tray_icon_16x16.png";
    private static final String STAGE_IMAGE_LOCATION = "stage_icon.png";
    private static Stage mainStage;
    public static boolean isShow;

    @Override
    public void start(final Stage stage) {
        this.mainStage = stage;

        // instructs the javafx system not to exit implicitly when the last application window is shut.
        Platform.setImplicitExit(false);

        // sets up the tray icon (using awt code run on the swing thread).
        javax.swing.SwingUtilities.invokeLater(this::addAppToTray);

        // out stage will be translucent, so give it a transparent style.
        stage.initStyle(StageStyle.TRANSPARENT);
    }

    private static void notifier() {

        Platform.runLater(() -> {

                    //Stage owner = new Stage(StageStyle.TRANSPARENT);
                    Stage owner = mainStage;
                    owner.getIcons().add(AppUtils.ICON_IMAGE);
                    StackPane root = new StackPane();
                    root.setStyle("-fx-background-color: TRANSPARENT");
                    Scene scene = new Scene(root, 1, 1);
                    scene.setFill(Color.TRANSPARENT);
                    owner.setScene(scene);
                    owner.setWidth(1);
                    owner.setHeight(1);
                    owner.toBack();
                    owner.show();
                    MainView mainView = new MainView();
                    if (!isShow) {
                        isShow = true;
                        Notifications notifications = getNotifications(mainView, owner);
                        notifications.show();
                    }
                }
        );
    }

    public static  Notifications getNotifications(Node content, Stage owner){
        GridPane gridPane = new GridPane();
        CloseButton closeButton = new CloseButton(owner);
        HBox titleHBox = new HBox(new Label(getStringProperty("ui.mainView.header")));
        titleHBox.setAlignment(Pos.BASELINE_RIGHT);
        HBox closeHBox = new HBox(closeButton);
        closeHBox.setAlignment(Pos.BASELINE_RIGHT);

        gridPane.add(titleHBox,0,0);
        gridPane.add(closeButton,1,0);
        setGridStyle(closeButton);

        VBox vBox = new VBox(gridPane,content);
        return Notifications.create()
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


    /**
     * Sets up a system tray icon for the application.
     */
    private void addAppToTray() {
        try {
            // ensure awt toolkit is initialized.
            java.awt.Toolkit.getDefaultToolkit();
            // app requires system tray support, just exit if there is no support.
            if (!java.awt.SystemTray.isSupported()) {
                Platform.exit();
            }

            // set up a system tray icon.
            java.awt.SystemTray tray = java.awt.SystemTray.getSystemTray();
            java.awt.TrayIcon trayIcon = new java.awt.TrayIcon(ImageIO.read(getClass().getResourceAsStream(TRAY_IMAGE_LOCATION)));
            // if the user double-clicks on the tray icon, show the main app stage.
            trayIcon.addActionListener(event -> {
                Platform.runLater(() -> {
                    //show word
                    notifier();
                });
            });

            java.awt.MenuItem openItem = new java.awt.MenuItem(getStringProperty("ui.mainView.tray.menu.settings"));
            java.awt.Font defaultFont = java.awt.Font.decode(null);
            java.awt.Font boldFont = defaultFont.deriveFont(java.awt.Font.BOLD);
            openItem.setFont(boldFont);
            SettingsImpl.getInstance();
            openItem.addActionListener(event -> {
                Platform.runLater(() -> {
                    //call settings
                    new SettingsDialogView().show();
                });
            });


            java.awt.MenuItem exitItem = new java.awt.MenuItem("Exit");
            exitItem.addActionListener(event -> {
                Platform.exit();
                tray.remove(trayIcon);
            });

            // setup the popup menu for the application.
            final java.awt.PopupMenu popup = new java.awt.PopupMenu();
            popup.add(openItem);
            popup.addSeparator();
            popup.add(exitItem);
            trayIcon.setPopupMenu(popup);
            tray.add(trayIcon);
        } catch (java.awt.AWTException | IOException e) {
            System.out.println("Unable to init system tray");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, java.awt.AWTException {
        launch(args);
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static boolean isShow() {
        return isShow;
    }

    public static void setIsShow(boolean isShow) {
        App.isShow = isShow;
    }
}
