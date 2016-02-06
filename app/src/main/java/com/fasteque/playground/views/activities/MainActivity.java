package com.fasteque.playground.views.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.fasteque.playground.PlaygroundApplication;
import com.fasteque.playground.R;
import com.fasteque.playground.injection.components.DaggerTvShowAiringTodayComponent;
import com.fasteque.playground.injection.modules.ActivityModule;
import com.fasteque.playground.injection.modules.TvShowAiringTodayModule;
import com.fasteque.playground.model.entities.TvShow;
import com.fasteque.playground.presenters.TvShowsPresenter;
import com.fasteque.playground.views.TvShowsView;
import com.fasteque.playground.views.adapters.TvShowsAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;


public class MainActivity extends AppCompatActivity implements TvShowsView {

    public final static String EXTRA_TV_SHOW = "tv_show";

    @Bind(R.id.shows_toolbar)
    Toolbar toolbar;

    @Bind(R.id.shows_recyclerView)
    RecyclerView showsRecycler;

    @Inject
    TvShowsPresenter tvShowsPresenter;

    private TvShowsAdapter tvShowsAdapter;
    private Subscription tvShowsSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setAllowEnterTransitionOverlap(true);
            getWindow().setEnterTransition(new Explode());
            getWindow().setExitTransition(new Explode());
        }
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initToolbar();
        initRecyclerView();
        initDependencyInjector();
        initPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tvShowsSubscription != null) {
            tvShowsSubscription.unsubscribe();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        tvShowsPresenter.onPresenterStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        tvShowsPresenter.onPresenterStop();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.title_airing_today));
        }
    }

    private void initRecyclerView() {
        tvShowsAdapter = new TvShowsAdapter(this);
        showsRecycler.setAdapter(tvShowsAdapter);

        tvShowsSubscription = tvShowsAdapter.onClickTvShow()
                .map(new Func1<View, TvShow>() {
                    @Override
                    public TvShow call(View view) {
                        return tvShowsAdapter.getTvShowAtPosition(showsRecycler.getChildLayoutPosition(view));
                    }
                })
                .subscribe(new Action1<TvShow>() {
                    @Override
                    public void call(TvShow tvShow) {
                        Intent tvShowDetailIntent = new Intent(MainActivity.this, TvShowDetailActivity.class);
                        tvShowDetailIntent.putExtra(EXTRA_TV_SHOW, tvShow);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            //noinspection unchecked
                            startActivity(tvShowDetailIntent, ActivityOptions.makeSceneTransitionAnimation
                                    (MainActivity.this)
                                    .toBundle());
                        } else {
                            startActivity(tvShowDetailIntent);
                        }
                    }
                });
    }

    private void initDependencyInjector() {
        PlaygroundApplication playgroundApplication = (PlaygroundApplication) getApplication();

        DaggerTvShowAiringTodayComponent.builder()
                .tvShowAiringTodayModule(new TvShowAiringTodayModule(1))
                .activityModule(new ActivityModule(this))
                .applicationComponent(playgroundApplication.getApplicationComponent())
                .build().inject(this);
    }

    private void initPresenter() {
        tvShowsPresenter.attachView(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_attributions) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //noinspection unchecked
                startActivity(new Intent(this, AttributionsActivity.class), ActivityOptions
                        .makeSceneTransitionAnimation(this).toBundle());
            } else {
                startActivity(new Intent(this, AttributionsActivity.class));
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showAiringToday(@NonNull List<TvShow> tvShows) {
        tvShowsAdapter.insertTvShows(tvShows);
    }

    @Override
    public void displayError() {
        Snackbar.make(findViewById(android.R.id.content),
                getString(R.string.error_tv_shows_airing_today), Snackbar.LENGTH_SHORT).show();
    }
}
