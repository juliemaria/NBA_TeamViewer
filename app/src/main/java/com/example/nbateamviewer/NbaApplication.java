package com.example.nbateamviewer;

import android.app.Application;
import android.content.Context;

import com.example.nbateamviewer.network.ApiServiceInterface;

public class NbaApplication extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return context;
    }
}
