package com.airplaneSoft.translateMeDude.winApp.models.settings;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by Mezentsev.Y on 12/2/2016.
 */
public class SettingsTest {

    @Test(priority = 10)
    public void testLoad() throws Exception {
        Settings settings = SettingsImpl.getInstance();
        Assert.assertTrue(SettingsImpl.getSettingsFile().exists());
    }

    @Test(priority = 20)
    public void testSave() throws Exception {
        Settings settings = SettingsImpl.getInstance();
        settings.set(SettingsKeys.TIMER_VALUE, SettingsKeys.TimerValues.RANDOM);
        Assert.assertEquals(settings.getSettingsMap().get(SettingsKeys.TIMER_VALUE),SettingsKeys.TimerValues.RANDOM);
    }

    @Test(priority = 40)
    public void testGet() throws Exception {
        Settings settings = SettingsImpl.getInstance();
        Assert.assertEquals(settings.getSettingsMap().get(SettingsKeys.TIMER_VALUE),SettingsKeys.TimerValues.RANDOM);
    }
}