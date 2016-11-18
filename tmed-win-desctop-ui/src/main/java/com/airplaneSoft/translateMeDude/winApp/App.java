package com.airplaneSoft.translateMeDude.winApp;

import com.airplaneSoft.translateMeDude.winApp.view.MainView;
import com.airplaneSoft.translateMeDude.winApp.view.components.CloseButton;
import com.airplaneSoft.translateMeDude.winApp.utils.AppUtils;
import com.airplaneSoft.translateMeDude.winApp.view.AWTMenuView;
import javafx.application.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import javax.imageio.ImageIO;
import java.io.IOException;
import static com.airplaneSoft.translateMeDude.winApp.utils.AppUtils.getStringProperty;
import org.apache.log4j.Logger;

/**
 * Main app run class. This class contains AWT UI components, that provides to create windows tray app.
 */
public class App extends Application {
    private static final Logger LOGGER = Logger.getLogger(App.class);
    private static final String TRAY_IMAGE_LOCATION = "tray_icon_16x16.png";
    private static Stage mainStage;
    public static boolean isShow;

    @Override
    public void start(final Stage stage) {
        this.mainStage = stage;
        mainStage.setAlwaysOnTop(true);
        // instructs the javafx system not to exit implicitly
        // when the last application window is shut.
        Platform.setImplicitExit(false);
        // sets up the tray icon (using awt code run on the swing thread).
        javax.swing.SwingUtilities.invokeLater(this::addAppToTray);
        // give it a transparent style.
        stage.initStyle(StageStyle.TRANSPARENT);
    }

    /**
     * Call main popup balloon
     */
    private static void notifier() {
        if (!isShow) {
            mainStage = new Stage(StageStyle.TRANSPARENT);
            mainStage.getIcons().add(AppUtils.ICON_IMAGE);
                        StackPane root = new StackPane();
                        root.setStyle("-fx-background-color: TRANSPARENT");
                        Scene scene = new Scene(root, 1, 1);
                        scene.setFill(Color.TRANSPARENT);
            mainStage.setScene(scene);
            mainStage.setWidth(1);
            mainStage.setHeight(1);
            mainStage.toBack();
            mainStage.show();
            mainStage.setAlwaysOnTop(true);
            mainStage.requestFocus();
            MainView mainView = new MainView();
            setIsShow(true);
            Notifications notifications = getNotifications(mainView, mainStage);
            notifications.show();
            //check if stage was closed
            mainStage.setOnCloseRequest(event -> {
                mainStage.hide();
                setIsShow(false);
                event.consume();
            });

        }
    }

    /**
     * Create styled notification balloon
     * @param content
     * @param owner
     * @return
     */
    private static  Notifications getNotifications(Node content, Stage owner){
        GridPane gridPane = new GridPane();
        CloseButton closeButton = new CloseButton(owner);
        Label label = new Label(getStringProperty("ui.mainView.header"));
        label.setStyle("-fx-font-weight: bold; -fx-text-fill: #ff8105; -fx-font-size: 20px;" );
        HBox titleHBox = new HBox(label);
        titleHBox.setAlignment(Pos.BASELINE_RIGHT);
        HBox closeHBox = new HBox(closeButton);
        closeHBox.setAlignment(Pos.BASELINE_RIGHT);

        gridPane.add(titleHBox,0,0);
        gridPane.add(closeButton,1,0);
        setGridStyle(closeButton);

        VBox vBox = new VBox(gridPane,content);
        vBox.setFocusTraversable(true);
//        vBox.setOnMouseClicked(event -> {
//            owner.requestFocus();
//        });
        return Notifications.create()
                .hideAfter(Duration.INDEFINITE)
                .graphic(vBox)
                .darkStyle()
                //.owner(owner)
                .hideCloseButton();
    }

    /**
     * Set specific grid view constraints
     * @param nodes
     */
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
            java.awt.TrayIcon trayIcon = new java.awt.TrayIcon(ImageIO.read(getClass()
                    .getResourceAsStream(TRAY_IMAGE_LOCATION)));
            // if the user double-clicks on the tray icon, show the main app stage.
            trayIcon.addActionListener(event -> {

                Platform.runLater(App::notifier);
            });
            // setup the popup menu for the application.
            trayIcon.setPopupMenu(new AWTMenuView(()->{
                Platform.exit();
                tray.remove(trayIcon);//clean icon
            }));
            tray.add(trayIcon);
            LOGGER.info("Application run");
        } catch (java.awt.AWTException | IOException e) {
            LOGGER.error("Unable to init system tray" , e);
        }
    }

    public static void main(String[] args) throws IOException, java.awt.AWTException {
        launch(args);
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    /**
     * @return true if main popup is shown.
     */
    public static boolean isShow() {
        return isShow;
    }

    public static void setIsShow(boolean isShow) {
        App.isShow = isShow;
    }
}
