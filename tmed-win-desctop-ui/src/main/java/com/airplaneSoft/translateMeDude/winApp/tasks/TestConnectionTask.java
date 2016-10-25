package com.airplaneSoft.translateMeDude.winApp.tasks;

import com.airplaneSoft.translateMeDude.winApp.settings.settingsModel.Settings;
import com.airplaneSoft.translateMeDude.winApp.settings.settingsModel.SettingsImpl;
import com.airplaneSoft.translateMeDude.winApp.settings.settingsModel.SettingsKeys;
import com.airplaneSoft.translateMeDude.winApp.utils.RemoteServiceUtils;

import javafx.concurrent.Task;

/**
 * Created by Mezentsev.Y on 10/25/2016.
 */
public class TestConnectionTask extends Task<Boolean>{
    private Settings settings;

    public TestConnectionTask() {
        this.settings = SettingsImpl.getInstance();
    }

    @Override
    protected Boolean call() throws Exception {
       return new RemoteServiceUtils(settings.get(SettingsKeys.URL),
                settings.get(SettingsKeys.SSOID),
                settings.get(SettingsKeys.PASSWORD)).testConnection();

    }
}
