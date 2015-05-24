package com.fasteque.playground.injection.components;

import com.fasteque.playground.PlaygroundApplication;
import com.fasteque.playground.injection.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by danielealtomare on 24/05/15.
 * Project: Playground
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    PlaygroundApplication application();
}
