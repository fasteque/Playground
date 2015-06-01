package com.fasteque.playground.injection.modules;

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

    /**
     * With the @Provides annotation we are saying to Dagger 2 how a dependency has to be build
     * if required.
     * Otherwise if we wouldn't indicate a provider for a particular dependency, Dagger 2 will go to
     * find it to the constructor annotated with: @Inject.
     */
    @Provides
    @Singleton
    PlaygroundApplication provideApplicationContext() {
        return playgroundApplication;
    }

    @Provides
    @Singleton
    MovieDbService provideDataService(RestMovieDbService restMovieDbService) { return  restMovieDbService; }
}
