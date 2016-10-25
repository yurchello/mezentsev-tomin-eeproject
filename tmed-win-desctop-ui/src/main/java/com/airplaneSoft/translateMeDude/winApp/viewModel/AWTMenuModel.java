package com.airplaneSoft.translateMeDude.winApp.viewModel;

import com.airplaneSoft.translateMeDude.models.vocabulary.WordsGroup;
import com.airplaneSoft.translateMeDude.winApp.AppUtils;
import com.airplaneSoft.translateMeDude.winApp.dialogComponent.LoadingDialogView;
import com.airplaneSoft.translateMeDude.winApp.settings.settingsModel.Settings;
import com.airplaneSoft.translateMeDude.winApp.settings.settingsModel.SettingsImpl;
import com.airplaneSoft.translateMeDude.winApp.settings.settingsModel.SettingsKeys;
import com.airplaneSoft.translateMeDude.winApp.tasks.UpdateVocabularyTask;
import com.airplaneSoft.translateMeDude.winApp.utils.GuiUtils;
import com.airplaneSoft.translateMeDude.winApp.utils.RemoteServiceUtils;
import com.airplaneSoft.translateMeDude.winApp.utils.vocabularyUtils.VocabularyUtilsImpl;
import javafx.application.Platform;
import javafx.concurrent.Worker;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Mezentsev.Y on 10/25/2016.
 */
public class AWTMenuModel {
    private final Settings settings;
    private LoadingDialogView loadingDialog;

    public AWTMenuModel() {
        this.settings = SettingsImpl.getInstance();
    }

    public void update(){
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
                System.out.println("Update ERROR");
            } else if (newState == Worker.State.SUCCEEDED) {
                loadingDialog.close();
                GuiUtils.showInfoAlert(AppUtils.getStringProperty("ui.alert.success"),
                        AppUtils.getStringProperty("ui.alert.update.success"));
                System.out.println("Test connection SUCCESS");
            }
            System.out.println("Update task status " + newState);
        });
        new Thread(task).start();
    }
}
