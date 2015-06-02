package com.fasteque.playground.presenters;

import com.fasteque.playground.domain.GetTvShowDetailUseCase;
import com.fasteque.playground.views.TvShowDetailView;
import com.fasteque.playground.views.View;

import javax.inject.Inject;

/**
 * Created by danielealtomare on 25/05/15.
 * Project: Playground
 */
// TODO: extends Subsciber
public class TvShowDetailPresenter implements Presenter {

    private TvShowDetailView tvShowDetailView;
    private final GetTvShowDetailUseCase getTvShowDetailUseCase;
    private String tvShowId;

    @Inject
    public TvShowDetailPresenter(GetTvShowDetailUseCase getTvShowDetailUseCase) {
        this.getTvShowDetailUseCase = getTvShowDetailUseCase;
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
