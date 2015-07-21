package com.fasteque.playground.domain;

import android.support.annotation.NonNull;

import com.fasteque.playground.model.MovieDbService;
import com.fasteque.playground.model.entities.Configuration;
import com.fasteque.playground.utils.MovieDbConstants;

import java.net.SocketTimeoutException;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(new Func2<Integer, Throwable, Boolean>() {
                    @Override
                    public Boolean call(Integer integer, Throwable throwable) {
                        return throwable instanceof SocketTimeoutException &&
                                integer < MovieDbConstants.getMaxAttemps();
                    }
                });
    }
}
