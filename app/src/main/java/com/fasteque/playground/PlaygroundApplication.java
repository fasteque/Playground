package com.fasteque.playground;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by danielealtomare on 23/05/15.
 * Project: Playground
 */
public class PlaygroundApplication extends Application {
    public static RefWatcher getRefWatcher(Context context) {
        PlaygroundApplication application = (PlaygroundApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

    @Override public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
    }
}
