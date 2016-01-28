package com.fasteque.playground.presenters;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.fasteque.playground.domain.GetTvShowDetailUseCase;
import com.fasteque.playground.model.entities.TvShowDetail;
import com.fasteque.playground.views.TvShowDetailView;
import com.fasteque.playground.views.View;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by danielealtomare on 25/05/15.
 * Project: Playground
 */
public class TvShowDetailPresenter extends Subscriber<TvShowDetail> implements Presenter {

    private TvShowDetailView tvShowDetailView;
    private final GetTvShowDetailUseCase getTvShowDetailUseCase;
    @SuppressWarnings("FieldCanBeLocal")
    private Intent tvShowIntent;

    private Subscription tvShowDetailSubscription;

    @Inject
    public TvShowDetailPresenter(GetTvShowDetailUseCase getTvShowDetailUseCase) {
        this.getTvShowDetailUseCase = getTvShowDetailUseCase;
    }

    @Override
    public void onPresenterStart() {
        tvShowDetailSubscription = getTvShowDetailUseCase.execute().subscribe(this);
    }

    @Override
    public void onPresenterStop() {
        if(tvShowDetailSubscription.isUnsubscribed()) {
            tvShowDetailSubscription.unsubscribe();
        }
    }

    @Override
    public void attachView(@NonNull View view) {
        tvShowDetailView = (TvShowDetailView) view;
    }

    @Override
    public void attachIncomingIntent(@NonNull Intent intent) {
        tvShowIntent = intent;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        tvShowDetailView.displayError();
    }

    @Override
    public void onNext(TvShowDetail tvShowDetail) {
        tvShowDetailView.displayTvShowDetail(tvShowDetail);
    }
}
