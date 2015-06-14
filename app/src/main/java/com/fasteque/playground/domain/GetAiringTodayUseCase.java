package com.fasteque.playground.domain;

import android.support.annotation.NonNull;

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
public class GetAiringTodayUseCase implements UseCase {

    private final MovieDbService movieDbService;
    private int page;

    @Inject
    public GetAiringTodayUseCase(@NonNull MovieDbService movieDbService, int page) {
        this.movieDbService = movieDbService;
        this.page = page;
    }

    @Override
    public Subscription execute(@NonNull Subscriber subscriber) {
        return movieDbService.getTvShowsAiringToday(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
