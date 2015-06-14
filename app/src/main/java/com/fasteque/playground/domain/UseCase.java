package com.fasteque.playground.domain;

import android.support.annotation.NonNull;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by danielealtomare on 31/05/15.
 * Project: Playground
 */
public interface UseCase {
    Subscription execute(@NonNull Subscriber subscriber);
}
