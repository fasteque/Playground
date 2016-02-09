package com.fasteque.playground.model.rest;

import com.fasteque.playground.model.entities.Configuration;
import com.fasteque.playground.model.entities.TvShowDetail;
import com.fasteque.playground.model.entities.TvShowsWrapper;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by danielealtomare on 26/05/15.
 * Project: Playground
 */
public interface MovieDbApi {
    String END_POINT = "https://api.themoviedb.org/3/";
    String PARAM_API_KEY = "api_key";

    @GET("configuration")
    Observable<Configuration>
    getConfiguration();

    @GET("tv/airing_today")
    Observable<TvShowsWrapper>
    getTvShowsAiringToday(@Query("page") int page);

    @GET("tv/{id}")
    Observable<TvShowDetail>
    getTvShowDetail(@Path("id") Number id);
}
