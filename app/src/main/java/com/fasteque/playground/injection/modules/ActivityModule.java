package com.fasteque.playground.injection.modules;

import android.content.Context;

import com.fasteque.playground.injection.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by danielealtomare on 01/06/15.
 * Project: Playground
 */

@Module
public class ActivityModule {

    private final Context context;

    public ActivityModule(Context context) {
        this.context = context;
    }

    @Provides @PerActivity
    Context provideActivityContext() {
        return context;
    }
}
