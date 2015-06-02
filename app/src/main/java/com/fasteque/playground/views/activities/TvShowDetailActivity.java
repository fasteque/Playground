package com.fasteque.playground.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.fasteque.playground.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);

        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
