package com.fasteque.playground.presenters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.fasteque.playground.domain.GetAiringTodayUseCase;
import com.fasteque.playground.domain.GetConfigurationUseCase;
import com.fasteque.playground.model.entities.Configuration;
import com.fasteque.playground.model.entities.TvShowsWrapper;
import com.fasteque.playground.utils.ConnectionUtils;
import com.fasteque.playground.utils.MovieDbConstants;
import com.fasteque.playground.views.TvShowsView;
import com.fasteque.playground.views.View;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by danielealtomare on 25/05/15.
 * Project: Playground
 */
public class TvShowsPresenter implements Presenter {

    private TvShowsView tvShowsView;
    private final GetConfigurationUseCase getConfigurationUseCase;
    private final GetAiringTodayUseCase getAiringTodayUseCase;

    private Subscription configurationSubscription;
    private Subscription airingTodaySubscription;

    @Inject
    public TvShowsPresenter(GetConfigurationUseCase getConfigurationUseCase,
                            GetAiringTodayUseCase getAiringTodayUseCase) {
        this.getConfigurationUseCase = getConfigurationUseCase;
        this.getAiringTodayUseCase = getAiringTodayUseCase;
    }

    @Override
    public void onPresenterStart() {
        configurationSubscription = getConfigurationUseCase.execute().subscribe(new Subscriber<Configuration>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Configuration configuration) {
                MovieDbConstants.setBasicStaticUrl(configuration.getImages().getBase_url());
                MovieDbConstants.setBackdropPreferredSize(getSize(configuration.getImages().getBackdrop_sizes()));
                MovieDbConstants.setPosterPreferredSize(getSize(configuration.getImages().getPoster_sizes()));

                airingTodaySubscription = getAiringTodayUseCase.execute().subscribe(new Subscriber<TvShowsWrapper>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        tvShowsView.displayError();
                    }

                    @Override
                    public void onNext(TvShowsWrapper tvShowsWrapper) {
                        tvShowsView.showAiringToday(tvShowsWrapper.getResults());
                    }
                });
            }
        });
    }

    @Override
    public void onPresenterStop() {
        if (configurationSubscription != null && !configurationSubscription.isUnsubscribed())
            configurationSubscription.unsubscribe();

        if (airingTodaySubscription != null && !airingTodaySubscription.isUnsubscribed())
            airingTodaySubscription.unsubscribe();
    }

    @Override
    public void attachView(@NonNull View view) {
        tvShowsView = (TvShowsView) view;
    }

    @Override
    public void attachIncomingIntent(@NonNull Intent intent) {
        // nothing to do by this presenter.
    }

    private String getSize(String[] sizes) {
        // last size is "original".
        int size = 0;

        if(sizes.length > 4) {
            if (ConnectionUtils.isConnectedToWiFi((Context) tvShowsView)) {
                size = sizes.length - 2;
            } else {
                size = sizes.length - 3;
            }
        }

        return sizes[size];
    }
}
