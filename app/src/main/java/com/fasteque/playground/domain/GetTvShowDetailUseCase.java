package com.fasteque.playground.domain;

import com.fasteque.playground.model.MovieDbService;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by danielealtomare on 31/05/15.
 * Project: Playground
 */
public class GetTvShowDetailUseCase implements UseCase {

    private final MovieDbService movieDbService;
    private Number tvShowId;

    @Inject
    public GetTvShowDetailUseCase(MovieDbService movieDbService, Number tvShowId) {
        this.movieDbService = movieDbService;
        this.tvShowId = tvShowId;
    }

    @Override
    public Subscription execute(Subscriber subscriber) {
        return movieDbService.getTvShowDetail(tvShowId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
