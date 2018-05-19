package com.example.itamarborges.recipefinder;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by itamarborges on 19/05/18.
 */

public class MainApp extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
