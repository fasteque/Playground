package com.fasteque.playground.domain;

import android.support.annotation.NonNull;

import com.fasteque.playground.model.MovieDbService;
import com.fasteque.playground.model.entities.Configuration;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by danielealtomare on 31/05/15.
 * Project: Playground
 */
public class GetConfigurationUseCase implements UseCase<Configuration> {

    private final MovieDbService movieDbService;

    @Inject
    public GetConfigurationUseCase(@NonNull MovieDbService movieDbService) {
        this.movieDbService = movieDbService;
    }

    @Override
    public Observable<Configuration> execute() {
        return movieDbService.getConfiguration()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
