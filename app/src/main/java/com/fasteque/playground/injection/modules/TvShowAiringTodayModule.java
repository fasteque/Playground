package com.fasteque.playground.injection.modules;

import com.fasteque.playground.domain.GetAiringTodayUseCase;
import com.fasteque.playground.domain.GetConfigurationUseCase;
import com.fasteque.playground.injection.PerActivity;
import com.fasteque.playground.model.MovieDbService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by danielealtomare on 01/06/15.
 * Project: Playground
 */

@Module
public class TvShowAiringTodayModule {

    private final int page;

    public TvShowAiringTodayModule(int page) {
        this.page = page;
    }

    @Provides @PerActivity
    GetConfigurationUseCase provideGetConfigurationUseCase(MovieDbService movieDbService) {
        return new GetConfigurationUseCase(movieDbService);
    }

    @Provides @PerActivity
    GetAiringTodayUseCase provideGetAiringTodayUseCase(MovieDbService movieDbService) {
        return new GetAiringTodayUseCase(movieDbService, page);
    }
}
