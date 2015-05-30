package com.fasteque.playground.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.fasteque.playground.R;
import com.fasteque.playground.model.entities.TvShow;
import com.fasteque.playground.views.TvShowsView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends AppCompatActivity implements TvShowsView {
    @InjectView(R.id.shows_toolbar)
    Toolbar toolbar;

    @InjectView(R.id.shows_recyclerView)
    RecyclerView showsRecycler;

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

    private void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.title_airing_today));
    }

    private void initRecyclerView() {

    }

    private void initDependencyInjector() {

    }

    private void initPresenter() {

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

    }
}
