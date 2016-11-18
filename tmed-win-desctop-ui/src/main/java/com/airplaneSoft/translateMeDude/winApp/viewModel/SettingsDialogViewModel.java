package com.airplaneSoft.translateMeDude.winApp.viewModel;

import com.airplaneSoft.translateMeDude.winApp.App;
import com.airplaneSoft.translateMeDude.winApp.utils.AppUtils;
import com.airplaneSoft.translateMeDude.winApp.view.LoadingDialogView;
import com.airplaneSoft.translateMeDude.winApp.models.settings.SettingModel;
import com.airplaneSoft.translateMeDude.winApp.models.settings.Settings;
import com.airplaneSoft.translateMeDude.winApp.models.settings.SettingsImpl;
import com.airplaneSoft.translateMeDude.winApp.tasks.TestConnectionTask;
import com.airplaneSoft.translateMeDude.winApp.utils.GuiUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.concurrent.Worker;
import javafx.scene.Node;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * Settings view model
 */
public class SettingsDialogViewModel {
    private static final Logger LOGGER = Logger.getLogger(SettingsDialogViewModel.class);
    private Set<SettingModel> settingModels = new HashSet<>();
    private Settings settings;
    private ObservableSet<Node> invalidNodes = FXCollections.observableSet();

    public SettingsDialogViewModel() {
        settings = SettingsImpl.getInstance();
        Map<String, String> settingsMap = settings.getSettingsMap();
        settingsMap.forEach((k, v) -> settingModels.add(new SettingModel(k, v)));
    }

    /**
     * Save changed settings from UI to map without saving file
     */
    public void saveModel(){
        settingModels.forEach(SettingModel::save);
    }

    /**
     * Save changed settings from UI to file
     */
    public void saveToFile(){
        saveModel();
        saveSettingsToFile();
    }

    private void saveSettingsToFile(){
        try {
            settings.save();
        }catch (Exception e){
           LOGGER.error("Error save settings to file", e);
        }
    }

    /**
     * @param key settings key
     * @return settings value from settings map by key. Returns null if settings key not present.
     */
    public SettingModel getSettingModel(String key){
        try{
            return settingModels.stream().filter(settingStateModel -> settingStateModel.getKey().equals(key)).findFirst().get();
        }catch (NoSuchElementException e){
            LOGGER.error("Setting with name " + key + " does not exist" , e);
        }
        return null;
    }

    /**
     * Call connection to personal account test with loading dialog
     */
    public void testConnection(){
        LoadingDialogView loadingDialog = new LoadingDialogView(AppUtils.getStringProperty("ui.loading.dialog.test.connection"));
        loadingDialog.show();
        final TestConnectionTask testConnectionTask = new TestConnectionTask();
        testConnectionTask.stateProperty().addListener((observableValue, oldState, newState) -> {
            boolean isValidConnections = false;
            Exception exception = null;
            if (newState == Worker.State.SUCCEEDED || newState == Worker.State.FAILED) {
                try {
                    isValidConnections = testConnectionTask.get();
                } catch (InterruptedException | ExecutionException e) {
                    exception = e;
                    e.printStackTrace();
                }
                loadingDialog.close();
                if (newState == Worker.State.FAILED || !isValidConnections || exception!= null) {
                    GuiUtils.showErrorAlert(AppUtils.getStringProperty("ui.alert.error"), AppUtils.getStringProperty("ui.alert.test.connection.error"));
                    LOGGER.info("Test connection ERROR", exception);
                }else {
                    GuiUtils.showInfoAlert(AppUtils.getStringProperty("ui.alert.success"), AppUtils.getStringProperty("ui.alert.test.connection.success"));
                    LOGGER.info("Test connection SUCCESS");
                }
                LOGGER.info("Test connection task status " + newState);
            }
        });
        new Thread(testConnectionTask).start();
    }

    public ObservableSet<Node> getInvalidNodes() {
        return invalidNodes;
    }
}
