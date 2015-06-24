package com.fasteque.playground.domain;

import rx.Observable;

/**
 * Created by danielealtomare on 31/05/15.
 * Project: Playground
 */
public interface UseCase<T> {
    Observable<T> execute();
}
