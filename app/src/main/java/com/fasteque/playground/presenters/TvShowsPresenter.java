package com.fasteque.playground.presenters;

import android.util.Log;

import com.fasteque.playground.domain.GetAiringTodayUseCase;
import com.fasteque.playground.model.entities.TvShowsWrapper;
import com.fasteque.playground.views.TvShowsView;
import com.fasteque.playground.views.View;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by danielealtomare on 25/05/15.
 * Project: Playground
 */
public class TvShowsPresenter extends Subscriber<TvShowsWrapper> implements Presenter {

    private TvShowsView tvShowsView;
    private final GetAiringTodayUseCase getAiringTodayUseCase;

    @Inject
    public TvShowsPresenter(GetAiringTodayUseCase getAiringTodayUseCase) {
        this.getAiringTodayUseCase = getAiringTodayUseCase;
    }

    @Override
    public void onPresenterStart() {
        getAiringTodayUseCase.execute(this);
    }

    @Override
    public void onPresenterStop() {
        // TODO
    }

    @Override
    public void attachView(View view) {
        tvShowsView = (TvShowsView) view;
    }

    @Override
    public void onCompleted() {
        Log.d(getClass().getName(), "onCompleted");
    }

    @Override
    public void onError(Throwable e) {
        Log.d(getClass().getName(), "onError: " + e.getMessage());
    }

    @Override
    public void onNext(TvShowsWrapper tvShowsWrapper) {
        Log.d(getClass().getName(), "onNext");
        tvShowsView.showAiringToday(tvShowsWrapper.getResults());
    }
}
