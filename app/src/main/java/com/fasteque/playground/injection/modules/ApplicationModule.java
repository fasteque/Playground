package com.fasteque.playground.injection.modules;

import com.fasteque.playground.PlaygroundApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by danielealtomare on 24/05/15.
 * Project: Playground
 */

/**
 * It defines provider methods for any injectable dependency.
 * It encapsulates knowledge of how to construct the objects to inject.
 */
@Module
public class ApplicationModule {
    private final PlaygroundApplication playgroundApplication;

    public ApplicationModule(PlaygroundApplication playgroundApplication) {
        this.playgroundApplication = playgroundApplication;
    }

    // Provider method: returned type is the type which you want to inject.
    @Provides
    @Singleton
    PlaygroundApplication provideApplicationContext() {
        return playgroundApplication;
    }
}
