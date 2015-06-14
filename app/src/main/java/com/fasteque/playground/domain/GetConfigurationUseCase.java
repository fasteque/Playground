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
public class GetConfigurationUseCase implements UseCase {

    private final MovieDbService movieDbService;

    @Inject
    public GetConfigurationUseCase(@NonNull MovieDbService movieDbService) {
        this.movieDbService = movieDbService;
    }

    @Override
    public Subscription execute(@NonNull Subscriber subscriber) {
        return movieDbService.getConfiguration()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
