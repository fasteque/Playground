package com.fasteque.playground.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.android.view.ViewObservable;
import rx.functions.Action1;
import rx.functions.Func1;


public class MainActivity extends AppCompatActivity implements TvShowsView {

    public final static String EXTRA_TV_SHOW = "tv_show";

    @InjectView(R.id.shows_toolbar)
    Toolbar toolbar;

    @InjectView(R.id.shows_recyclerView)
    RecyclerView showsRecycler;

    @Inject
    TvShowsPresenter tvShowsPresenter;

    private TvShowsAdapter tvShowsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        initToolbar();
        initRecyclerView();
        initDependencyInjector();
        initPresenter();
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
        getSupportActionBar().setTitle(getString(R.string.title_airing_today));
    }

    private void initRecyclerView() {
        tvShowsAdapter = new TvShowsAdapter(this);
        showsRecycler.setAdapter(tvShowsAdapter);

        // Unsubscribe is automatically performed by bindView method.
        ViewObservable.bindView(showsRecycler, tvShowsAdapter.onClickTvShow())
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
                        // TODO: animation
                        startActivity(tvShowDetailIntent);
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
            startActivity(new Intent(this, AttributionsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showAiringToday(List<TvShow> tvShows) {
        tvShowsAdapter.insertTvShows(tvShows);
    }
}
