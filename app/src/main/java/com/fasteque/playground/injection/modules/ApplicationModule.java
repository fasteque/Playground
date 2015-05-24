package com.fasteque.playground.injection.modules;

import com.fasteque.playground.PlaygroundApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by danielealtomare on 24/05/15.
 * Project: Playground
 */
@Module
public class ApplicationModule {
    private final PlaygroundApplication playgroundApplication;

    public ApplicationModule(PlaygroundApplication playgroundApplication) {
        this.playgroundApplication = playgroundApplication;
    }

    @Provides
    @Singleton
    PlaygroundApplication provideApplicationContext() {
        return playgroundApplication;
    }
}
