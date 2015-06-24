package com.fasteque.playground.domain;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.fasteque.playground.model.MovieDbService;
import com.fasteque.playground.model.entities.TvShowsWrapper;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by danielealtomare on 31/05/15.
 * Project: Playground
 */
public class GetAiringTodayUseCase implements UseCase<TvShowsWrapper> {

    private final MovieDbService movieDbService;
    private int page;

    @Inject
    public GetAiringTodayUseCase(@NonNull MovieDbService movieDbService,
                                 @IntRange(from=0, to=1000) int page) {
        this.movieDbService = movieDbService;
        this.page = page;
    }

    @Override
    public Observable<TvShowsWrapper> execute() {
        return movieDbService.getTvShowsAiringToday(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
