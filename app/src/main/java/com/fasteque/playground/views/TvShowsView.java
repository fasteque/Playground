package com.fasteque.playground.views;

import com.fasteque.playground.model.entities.TvShow;

import java.util.List;

/**
 * Created by danielealtomare on 25/05/15.
 * Project: Playground
 */
public interface TvShowsView extends View {
    void showAiringToday(List<TvShow> tvShows);
}
