package com.fasteque.playground.model;

import android.support.annotation.IntRange;

import com.fasteque.playground.model.entities.Configuration;
import com.fasteque.playground.model.entities.TvShowDetail;
import com.fasteque.playground.model.entities.TvShowsWrapper;

import rx.Observable;

/**
 * Created by danielealtomare on 26/05/15.
 * Project: Playground
 */
public interface MovieDbService {
    Observable<Configuration> getConfiguration();
    Observable<TvShowsWrapper> getTvShowsAiringToday(@IntRange(from=0, to=1000) final int page);
    Observable<TvShowDetail> getTvShowDetail(final Number id);
}
