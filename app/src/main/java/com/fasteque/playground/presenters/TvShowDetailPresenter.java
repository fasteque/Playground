package com.fasteque.playground.presenters;

import com.fasteque.playground.domain.GetTvShowDetailUseCase;
import com.fasteque.playground.model.entities.TvShowDetail;
import com.fasteque.playground.views.TvShowDetailView;
import com.fasteque.playground.views.View;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by danielealtomare on 25/05/15.
 * Project: Playground
 */
public class TvShowDetailPresenter extends Subscriber<TvShowDetail> implements Presenter {

    private TvShowDetailView tvShowDetailView;
    private final GetTvShowDetailUseCase getTvShowDetailUseCase;
    private String tvShowId;

    @Inject
    public TvShowDetailPresenter(GetTvShowDetailUseCase getTvShowDetailUseCase) {
        this.getTvShowDetailUseCase = getTvShowDetailUseCase;
    }

    @Override
    public void onPresenterStart() {
        getTvShowDetailUseCase.execute(this);
    }

    @Override
    public void onPresenterStop() {
        // TODO
    }

    @Override
    public void attachView(View view) {
        tvShowDetailView = (TvShowDetailView) view;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(TvShowDetail tvShowDetail) {

    }
}
