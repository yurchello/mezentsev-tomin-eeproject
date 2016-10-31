package com.airplaneSoft.translateMeDude.winApp.viewModel;

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
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * Settings view model
 */
public class SettingsDialogViewModel {
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
            System.out.println("Error save settings to file ");
            e.printStackTrace();
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
            System.out.println("Setting with name " + key + " does not exist");
            return null;
        }
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
                    System.out.println("Test connection ERROR");
                }else {
                    GuiUtils.showInfoAlert(AppUtils.getStringProperty("ui.alert.success"), AppUtils.getStringProperty("ui.alert.test.connection.success"));
                    System.out.println("Test connection SUCCESS");
                }
                System.out.println("Test connection task status " + newState);
            }
        });
        new Thread(testConnectionTask).start();
    }

    public ObservableSet<Node> getInvalidNodes() {
        return invalidNodes;
    }
}
