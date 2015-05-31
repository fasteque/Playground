package com.fasteque.playground.domain;

import com.fasteque.playground.model.MovieDbService;

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
    private String tvShowId;

    // FIXME: add injection.
    public GetTvShowDetailUseCase(MovieDbService movieDbService, String tvShowId) {
        this.movieDbService = movieDbService;
        this.tvShowId = tvShowId;
    }

    @Override
    public Subscription execute(Subscriber subscriber) {
        return movieDbService.getTvShowDetail(tvShowId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
