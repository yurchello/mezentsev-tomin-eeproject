package com.airplaneSoft.translateMeDude.winApp.viewModel;

import com.airplaneSoft.translateMeDude.winApp.App;
import com.airplaneSoft.translateMeDude.winApp.utils.AppUtils;
import com.airplaneSoft.translateMeDude.winApp.view.LoadingDialogView;
import com.airplaneSoft.translateMeDude.winApp.models.settings.Settings;
import com.airplaneSoft.translateMeDude.winApp.models.settings.SettingsImpl;
import com.airplaneSoft.translateMeDude.winApp.models.settings.SettingsKeys;
import com.airplaneSoft.translateMeDude.winApp.tasks.UpdateVocabularyTask;
import com.airplaneSoft.translateMeDude.winApp.utils.GuiUtils;
import com.airplaneSoft.translateMeDude.winApp.utils.RemoteServiceUtils;
import com.airplaneSoft.translateMeDude.winApp.utils.vocabularyUtils.VocabularyUtilsImpl;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import org.apache.log4j.Logger;

/**
 * AWT context menu model
 */
public class AWTMenuModel {
    private static final Logger LOGGER = Logger.getLogger(AWTMenuModel.class);
    private final Settings settings;
    private LoadingDialogView loadingDialog;

    public AWTMenuModel() {
        this.settings = SettingsImpl.getInstance();
    }

    /**
     * This method call vocabulary update with loading dialog
     */
    public void update(){
        //AWT isn't runs in javafx thread
        Platform.runLater(() -> {
            loadingDialog = new LoadingDialogView(AppUtils.getStringProperty("ui.loading.dialog.loading.vocabulary"));
            loadingDialog.show();
        });
        UpdateVocabularyTask task = new UpdateVocabularyTask(new VocabularyUtilsImpl(),
                new RemoteServiceUtils(
                        settings.get(SettingsKeys.URL),
                        settings.get(SettingsKeys.SSOID),
                        settings.get(SettingsKeys.PASSWORD)));
        task.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.FAILED) {
                loadingDialog.close();
                GuiUtils.showErrorAlert(AppUtils.getStringProperty("ui.alert.error"),
                        AppUtils.getStringProperty("ui.alert.update.error"));
                LOGGER.info("Update ERROR");
            } else if (newState == Worker.State.SUCCEEDED) {
                loadingDialog.close();
                GuiUtils.showInfoAlert(AppUtils.getStringProperty("ui.alert.success"),
                        AppUtils.getStringProperty("ui.alert.update.success"));
                LOGGER.info("Test connection SUCCESS");
            }
            LOGGER.info("Update task status " + newState);
        });
        new Thread(task).start();
    }
}
