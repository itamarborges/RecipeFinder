package com.example.itamarborges.recipefinder;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by itamarborges on 19/05/18.
 */

public class MainApp extends Application {

    final static String GOOGLE_ANALITICS_KEY = BuildConfig.GOOGLE_ANALITICS_KEY;

    @Override
    public void onCreate() {
        super.onCreate();
        boolean isTest= Boolean.valueOf(getString(R.string.app_in_test));
        if(!isTest) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            analytics.setLocalDispatchPeriod(1800);

            Tracker tracker = analytics.newTracker(GOOGLE_ANALITICS_KEY);
            tracker.enableExceptionReporting(true);
            tracker.enableAdvertisingIdCollection(true);
            tracker.enableAutoActivityTracking(true);
            tracker.enableAdvertisingIdCollection(true);
        }
    }


}
