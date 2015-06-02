package com.fasteque.playground.model.rest;

import com.fasteque.playground.model.MovieDbService;
import com.fasteque.playground.model.entities.TvShowDetail;
import com.fasteque.playground.model.entities.TvShowsWrapper;

import javax.inject.Inject;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import rx.Observable;

/**
 * Created by danielealtomare on 26/05/15.
 * Project: Playground
 */
public class RestMovieDbService implements MovieDbService {
    private final MovieDbApi movieDbApi;

    @Inject
    public RestMovieDbService() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(MovieDbApi.END_POINT)
                .setRequestInterceptor(authorizationInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();


        movieDbApi = restAdapter.create(MovieDbApi.class);
    }

    RequestInterceptor authorizationInterceptor = new RequestInterceptor() {
        @Override
        public void intercept(RequestInterceptor.RequestFacade request) {
            // FIXME: remove API Key value from code.
            request.addQueryParam(MovieDbApi.PARAM_API_KEY, "a46a3d7b1f4afc0fbbb66ee9d7775d1b");
        }
    };

    @Override
    public Observable<TvShowsWrapper> getTvShowsAiringToday(int page) {
        return movieDbApi.getTvShowsAiringToday(page);
    }

    @Override
    public Observable<TvShowDetail> getTvShowDetail(Number id) {
        return movieDbApi.getTvShowDetail(id);
    }
}
