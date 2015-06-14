package com.fasteque.playground.injection.modules;

import android.content.Context;
import android.support.annotation.NonNull;

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

    public ActivityModule(@NonNull Context context) {
        this.context = context;
    }

    @Provides @PerActivity
    Context provideActivityContext() {
        return context;
    }
}
