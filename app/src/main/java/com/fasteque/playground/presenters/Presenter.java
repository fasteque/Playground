package com.fasteque.playground.presenters;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.fasteque.playground.views.View;

/**
 * Created by danielealtomare on 25/05/15.
 * Project: Playground
 */
public interface Presenter {
    void onPresenterStart();
    void onPresenterStop();
    void attachView(@NonNull View view);
    void attachIncomingIntent(@NonNull Intent intent);
}
