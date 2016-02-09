package com.fasteque.playground.model.rest;

import android.support.annotation.IntRange;

import com.fasteque.playground.BuildConfig;
import com.fasteque.playground.model.MovieDbService;
import com.fasteque.playground.model.entities.Configuration;
import com.fasteque.playground.model.entities.TvShowDetail;
import com.fasteque.playground.model.entities.TvShowsWrapper;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by danielealtomare on 26/05/15.
 * Project: Playground
 */
public class RestMovieDbService implements MovieDbService {
    private final MovieDbApi movieDbApi;

    @Inject
    public RestMovieDbService() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().addQueryParameter(MovieDbApi.PARAM_API_KEY,
                        BuildConfig.MOVIE_DB_API_KEY).build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(MovieDbApi.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        movieDbApi = retrofit.create(MovieDbApi.class);
    }

    @Override
    public Observable<Configuration> getConfiguration() {
        return movieDbApi.getConfiguration();
    }

    @Override
    public Observable<TvShowsWrapper> getTvShowsAiringToday(@IntRange(from=0, to=1000) int page) {
        return movieDbApi.getTvShowsAiringToday(page);
    }

    @Override
    public Observable<TvShowDetail> getTvShowDetail(Number id) {
        return movieDbApi.getTvShowDetail(id);
    }
}
