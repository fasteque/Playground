package com.fasteque.playground.injection.modules;

import android.support.annotation.NonNull;

import com.fasteque.playground.domain.GetTvShowDetailUseCase;
import com.fasteque.playground.injection.PerActivity;
import com.fasteque.playground.model.MovieDbService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by danielealtomare on 01/06/15.
 * Project: Playground
 */

@Module
public class TvShowDetailModule {

    private final Number tvShowId;

    public TvShowDetailModule(Number tvShowId) {
        this.tvShowId = tvShowId;
    }

    @Provides @PerActivity
    GetTvShowDetailUseCase provideGetTvShowDetailUseCase(@NonNull MovieDbService movieDbService) {
        return new GetTvShowDetailUseCase(movieDbService, tvShowId);
    }
}
