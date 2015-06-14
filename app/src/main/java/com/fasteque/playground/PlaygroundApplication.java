package com.fasteque.playground;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.fasteque.playground.injection.components.ApplicationComponent;
import com.fasteque.playground.injection.components.DaggerApplicationComponent;
import com.fasteque.playground.injection.modules.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by danielealtomare on 23/05/15.
 * Project: Playground
 */
public class PlaygroundApplication extends Application {

    private ApplicationComponent applicationComponent;
    private RefWatcher refWatcher;

    @Override public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static RefWatcher getRefWatcher(@NonNull Context context) {
        PlaygroundApplication application = (PlaygroundApplication) context.getApplicationContext();
        return application.refWatcher;
    }
}
