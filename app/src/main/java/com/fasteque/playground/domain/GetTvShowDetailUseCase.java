package com.fasteque.playground.domain;

import android.support.annotation.NonNull;

import com.fasteque.playground.model.MovieDbService;
import com.fasteque.playground.model.entities.TvShowDetail;
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
public class GetTvShowDetailUseCase implements UseCase<TvShowDetail> {

    private final MovieDbService movieDbService;
    private Number tvShowId;

    @Inject
    public GetTvShowDetailUseCase(@NonNull MovieDbService movieDbService, Number tvShowId) {
        this.movieDbService = movieDbService;
        this.tvShowId = tvShowId;
    }

    @Override
    public Observable<TvShowDetail> execute() {
        return movieDbService.getTvShowDetail(tvShowId)
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
