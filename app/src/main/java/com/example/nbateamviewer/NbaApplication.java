package com.example.nbateamviewer;

import android.app.Application;
import android.content.Context;

public class NbaApplication extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        NbaApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return NbaApplication.context;
    }
}
