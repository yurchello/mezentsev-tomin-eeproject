package com.airplaneSoft.translateMeDude.winApp.settings.settingsView;

import com.airplaneSoft.translateMeDude.winApp.settings.settingsModel.SettingModel;
import com.airplaneSoft.translateMeDude.winApp.settings.settingsModel.Settings;
import com.airplaneSoft.translateMeDude.winApp.settings.settingsModel.SettingsImpl;
import javafx.collections.FXCollections;

import javafx.collections.ObservableSet;
import javafx.scene.Node;

import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Created by Mezentsev.Y on 10/24/2016.
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

    public void saveModel(){
        settingModels.forEach(SettingModel::save);
    }

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

    public SettingModel getSettingModel(String key){
        try{
            return settingModels.stream().filter(settingStateModel -> settingStateModel.getKey().equals(key)).findFirst().get();
        }catch (NoSuchElementException e){
            System.out.println("Setting with name " + key + " does not exist");
            return null;
        }
    }

    public ObservableSet<Node> getInvalidNodes() {
        return invalidNodes;
    }
}
