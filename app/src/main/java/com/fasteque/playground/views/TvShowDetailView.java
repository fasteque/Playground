package com.fasteque.playground.views;

import android.support.annotation.NonNull;

import com.fasteque.playground.model.entities.TvShowDetail;

/**
 * Created by danielealtomare on 25/05/15.
 * Project: Playground
 */
public interface TvShowDetailView extends View {
    void displayTvShowDetail(@NonNull TvShowDetail tvShowDetail);
}
