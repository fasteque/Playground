package com.fasteque.playground.injection.components;

import com.fasteque.playground.domain.GetTvShowDetailUseCase;
import com.fasteque.playground.injection.PerActivity;
import com.fasteque.playground.injection.modules.ActivityModule;
import com.fasteque.playground.injection.modules.TvShowDetailModule;
import com.fasteque.playground.views.activities.TvShowDetailActivity;

import dagger.Component;

/**
 * Created by danielealtomare on 02/06/15.
 * Project: Playground
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {TvShowDetailModule.class, ActivityModule.class })
public interface TvShowDetailComponent {

    void inject(TvShowDetailActivity tvShowDetailActivity);

    GetTvShowDetailUseCase getTvShowDetailUseCase();
}
