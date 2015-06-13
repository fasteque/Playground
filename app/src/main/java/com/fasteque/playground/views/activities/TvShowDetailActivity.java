package com.fasteque.playground.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.fasteque.playground.PlaygroundApplication;
import com.fasteque.playground.R;
import com.fasteque.playground.injection.components.DaggerTvShowDetailComponent;
import com.fasteque.playground.injection.modules.ActivityModule;
import com.fasteque.playground.injection.modules.TvShowDetailModule;
import com.fasteque.playground.model.entities.TvShow;
import com.fasteque.playground.presenters.TvShowDetailPresenter;
import com.fasteque.playground.views.TvShowDetailView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TvShowDetailActivity extends AppCompatActivity implements TvShowDetailView {

    @InjectView(R.id.show_detail_toolbar)
    Toolbar toolbar;

    @Inject
    TvShowDetailPresenter tvShowDetailPresenter;

    private TvShow tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);

        ButterKnife.inject(this);
        setSupportActionBar(toolbar);

        tvShow = getIntent().getParcelableExtra(MainActivity.EXTRA_TV_SHOW);
        Log.d(getClass().getName(), tvShow.getId() + " " + tvShow.getName());

        initToolbar();
        initDependencyInjector();
        initPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        tvShowDetailPresenter.onPresenterStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        tvShowDetailPresenter.onPresenterStop();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tvShow.getName());
    }

    private void initDependencyInjector() {
        PlaygroundApplication playgroundApplication = (PlaygroundApplication) getApplication();

        DaggerTvShowDetailComponent.builder()
                .tvShowDetailModule(new TvShowDetailModule(tvShow.getId()))
                .activityModule(new ActivityModule(this))
                .applicationComponent(playgroundApplication.getApplicationComponent())
                .build().inject(this);
    }

    private void initPresenter() {
        tvShowDetailPresenter.attachView(this);
        tvShowDetailPresenter.attachIncomingIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tv_show_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
