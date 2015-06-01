package com.fasteque.playground.injection.components;

import android.content.Context;

import com.fasteque.playground.injection.PerActivity;
import com.fasteque.playground.injection.modules.ActivityModule;

import dagger.Component;

/**
 * Created by danielealtomare on 01/06/15.
 * Project: Playground
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Context context();
}
