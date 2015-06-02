package com.fasteque.playground.injection.components;

import com.fasteque.playground.domain.GetAiringTodayUseCase;
import com.fasteque.playground.injection.PerActivity;
import com.fasteque.playground.injection.modules.ActivityModule;
import com.fasteque.playground.injection.modules.TvShowAiringTodayModule;
import com.fasteque.playground.views.activities.MainActivity;

import dagger.Component;

/**
 * Created by danielealtomare on 02/06/15.
 * Project: Playground
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = { TvShowAiringTodayModule.class, ActivityModule.class })
public interface TvShowAiringTodayComponent {

    void inject(MainActivity mainActivity);

    GetAiringTodayUseCase getAiringTodayUseCase();
}
