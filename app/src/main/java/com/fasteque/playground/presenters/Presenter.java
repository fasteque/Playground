package com.fasteque.playground.presenters;

import com.fasteque.playground.views.View;

/**
 * Created by danielealtomare on 25/05/15.
 * Project: Playground
 */
public interface Presenter {
    void onPresenterStart();
    void onPresenterStop();
    void attachView(View view);
}
