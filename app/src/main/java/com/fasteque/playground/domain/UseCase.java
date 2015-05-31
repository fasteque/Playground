package com.fasteque.playground.domain;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by danielealtomare on 31/05/15.
 * Project: Playground
 */
public interface UseCase {
    Subscription execute(Subscriber subscriber);
}
