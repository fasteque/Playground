package com.fasteque.playground.injection.modules;

import android.support.annotation.NonNull;

import com.fasteque.playground.PlaygroundApplication;
import com.fasteque.playground.model.MovieDbService;
import com.fasteque.playground.model.rest.RestMovieDbService;

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

    @Provides
    @Singleton
    PlaygroundApplication provideApplicationContext() {
        return playgroundApplication;
    }

    @Provides
    @Singleton
    MovieDbService provideDataService(@NonNull RestMovieDbService restMovieDbService) { return  restMovieDbService; }
}
