package com.fasteque.playground;

import android.app.Application;

import com.fasteque.playground.injection.components.ApplicationComponent;
import com.fasteque.playground.injection.components.DaggerApplicationComponent;
import com.fasteque.playground.injection.modules.ApplicationModule;

/**
 * Created by danielealtomare on 23/05/15.
 * Project: Playground
 */
public class PlaygroundApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
