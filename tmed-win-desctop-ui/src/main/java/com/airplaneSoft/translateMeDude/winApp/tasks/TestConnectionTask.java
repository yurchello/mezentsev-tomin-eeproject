package com.airplaneSoft.translateMeDude.winApp.tasks;

import com.airplaneSoft.translateMeDude.winApp.models.settings.Settings;
import com.airplaneSoft.translateMeDude.winApp.models.settings.SettingsImpl;
import com.airplaneSoft.translateMeDude.winApp.models.settings.SettingsKeys;
import com.airplaneSoft.translateMeDude.winApp.utils.RemoteServiceUtils;

import javafx.concurrent.Task;

/**
 * REST Api test connection verification task
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
