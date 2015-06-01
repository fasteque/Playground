package com.fasteque.playground.injection.components;

import com.fasteque.playground.PlaygroundApplication;
import com.fasteque.playground.injection.modules.ApplicationModule;
import com.fasteque.playground.model.MovieDbService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by danielealtomare on 24/05/15.
 * Project: Playground
 */

/**
 * Components are interfaces (or abstract classes) defined by the developer.
 * Such interface may contain two types of methods:
 * - methods which return object and have no parameters
 *      > instances created by Dagger2
 * - methods which return void but have one argument
 *      > allow injecting non-private field members to provided object as argument
 */
@Singleton  // Constraints this component to one-per-application or un-scoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    /**
     * This is actually an important property of how components work in Dagger2:
     * they do not expose types from their modules unless you explicitly make them available.
     *
     * The dependencies provided by this module must be visible to other components that have this
     * component as a dependency of the other components, if these dependencies are not visible
     * Dagger2 could not inject them where are required.
     */
    PlaygroundApplication application();
    MovieDbService dataService();
}
