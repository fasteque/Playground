package com.fasteque.playground.model;

import com.fasteque.playground.model.entities.TvShowDetail;
import com.fasteque.playground.model.entities.TvShowsWrapper;

import rx.Observable;

/**
 * Created by danielealtomare on 26/05/15.
 * Project: Playground
 */
public interface MovieDbService {
    Observable<TvShowsWrapper> getTvShowsAiringToday(final int page);
    Observable<TvShowDetail> getTvShowDetail(final Number id);
}
