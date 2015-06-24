package com.fasteque.playground.domain;

import android.support.annotation.NonNull;

import com.fasteque.playground.model.MovieDbService;
import com.fasteque.playground.model.entities.TvShowDetail;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
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
                .observeOn(AndroidSchedulers.mainThread());
    }
}
