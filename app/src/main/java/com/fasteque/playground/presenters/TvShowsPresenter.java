package com.fasteque.playground.presenters;

import com.fasteque.playground.domain.GetAiringTodayUseCase;
import com.fasteque.playground.views.TvShowsView;
import com.fasteque.playground.views.View;

/**
 * Created by danielealtomare on 25/05/15.
 * Project: Playground
 */
// TODO: extends Subsciber
public class TvShowsPresenter implements Presenter {

    private TvShowsView tvShowsView;
    private final GetAiringTodayUseCase getAiringTodayUseCase;

    //FIXME: add injection
    public TvShowsPresenter(GetAiringTodayUseCase getAiringTodayUseCase) {
        this.getAiringTodayUseCase = getAiringTodayUseCase;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void attachView(View view) {

    }
}
