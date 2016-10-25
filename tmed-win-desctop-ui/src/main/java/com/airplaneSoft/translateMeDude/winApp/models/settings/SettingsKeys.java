package com.airplaneSoft.translateMeDude.winApp.models.settings;

/**
 * Created by Mezentsev.Y on 10/24/2016.
 */
public abstract class SettingsKeys {

    public static final String URL = "url";
    public static final String SSOID = "ssoid";
    public static final String PASSWORD = "password";
    public static final String SHOW = "show";
    public static final String SHOW_TIMER = "show.timer";
    public static final String TIMER_VALUE = "timer.value";

    public static abstract class TimerValues{
        public static final String TIMER = "timer";
        public static final String RANDOM = "random";
    }

}
