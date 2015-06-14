package com.fasteque.playground.presenters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.fasteque.playground.domain.GetAiringTodayUseCase;
import com.fasteque.playground.domain.GetConfigurationUseCase;
import com.fasteque.playground.model.entities.Configuration;
import com.fasteque.playground.model.entities.TvShowsWrapper;
import com.fasteque.playground.utils.MovieDbConstants;
import com.fasteque.playground.views.TvShowsView;
import com.fasteque.playground.views.View;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by danielealtomare on 25/05/15.
 * Project: Playground
 */
public class TvShowsPresenter extends Subscriber<Configuration> implements Presenter {

    private TvShowsView tvShowsView;
    private final GetConfigurationUseCase getConfigurationUseCase;
    private final GetAiringTodayUseCase getAiringTodayUseCase;

    @Inject
    public TvShowsPresenter(GetConfigurationUseCase getConfigurationUseCase,
                            GetAiringTodayUseCase getAiringTodayUseCase) {
        this.getConfigurationUseCase = getConfigurationUseCase;
        this.getAiringTodayUseCase = getAiringTodayUseCase;
    }

    @Override
    public void onPresenterStart() {
        getConfigurationUseCase.execute(this);
    }

    @Override
    public void onPresenterStop() {
        // TODO
    }

    @Override
    public void attachView(@NonNull View view) {
        tvShowsView = (TvShowsView) view;
    }

    @Override
    public void attachIncomingIntent(@NonNull Intent intent) {
        // nothing to do by this presenter.
    }

    @Override
    public void onCompleted() {
        Log.d(getClass().getName(), "onCompleted");
    }

    @Override
    public void onError(Throwable e) {
        Log.d(getClass().getName(), "onError: " + e.getMessage());
    }

    @Override
    public void onNext(Configuration configuration) {
        Log.d(getClass().getName(), "onNext");
        MovieDbConstants.setBasicStaticUrl(configuration.getImages().getBase_url());
        // TODO: improve the logic to pick the best poster size
        MovieDbConstants.setPosterPreferredSize(configuration.getImages().getPoster_sizes()
                [configuration.getImages().getPoster_sizes().length - 3]);

        getAiringTodayUseCase.execute(new Subscriber<TvShowsWrapper>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(TvShowsWrapper tvShowsWrapper) {
                tvShowsView.showAiringToday(tvShowsWrapper.getResults());
            }
        });
    }
}
