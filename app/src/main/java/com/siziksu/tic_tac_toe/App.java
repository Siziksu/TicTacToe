package com.siziksu.tic_tac_toe;

import android.app.Application;

import com.siziksu.tic_tac_toe.common.ActivityCommon;
import com.siziksu.tic_tac_toe.common.Preferences;
import com.siziksu.tic_tac_toe.injector.GameModule;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Preferences.init(getApplicationContext());
        ActivityCommon.init();
        GameModule.init(getApplicationContext());
    }

    public static GameModule gameModule() {
        return GameModule.get();
    }
}