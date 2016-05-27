package com.fasteque.playground.views.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fasteque.playground.PlaygroundApplication;
import com.fasteque.playground.R;
import com.fasteque.playground.injection.components.DaggerTvShowDetailComponent;
import com.fasteque.playground.injection.modules.ActivityModule;
import com.fasteque.playground.injection.modules.TvShowDetailModule;
import com.fasteque.playground.model.entities.TvShow;
import com.fasteque.playground.model.entities.TvShowDetail;
import com.fasteque.playground.presenters.TvShowDetailPresenter;
import com.fasteque.playground.utils.MovieDbConstants;
import com.fasteque.playground.views.TvShowDetailView;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class TvShowDetailActivity extends AppCompatActivity implements TvShowDetailView {

    private static final int HOMEPAGE = 0;
    private static final int OVERVIEW = 1;
    private static final int TYPE = 2;
    private static final int STATUS = 3;

    @BindView(R.id.tv_show_detail_toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_show_detail_collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.tv_show_detail_backdrop)
    ImageView tvShowBackdrop;

    @BindView(R.id.tv_show_detail_cover)
    ImageView tvShowCover;

    @BindView(R.id.tv_show_detail_detail_homepage_container)
    LinearLayout tvShowHomepageContainer;

    @BindViews({
            R.id.tv_show_detail_detail_homepage,
            R.id.tv_show_detail_detail_overview,
            R.id.tv_show_detail_detail_type,
            R.id.tv_show_detail_detail_status,
    })
    List<TextView> tvShowInfoTextViews;

    @Inject
    TvShowDetailPresenter tvShowDetailPresenter;

    private TvShow tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setAllowEnterTransitionOverlap(true);
            getWindow().setEnterTransition(new Explode());
            getWindow().setExitTransition(new Explode());
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        tvShow = getIntent().getParcelableExtra(MainActivity.EXTRA_TV_SHOW);

        initToolbar();
        initDependencyInjector();
        initPresenter();
        initCachedDetail();
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
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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

    private void initCachedDetail() {
        /**
         * So far there's not a real cache but anyway the intent
         * which has started this activity already have some information
         * about the TV show to be displayed.
         */
        collapsingToolbar.setTitle(tvShow.getName());

        Picasso.with(this)
                .load(MovieDbConstants.getBasicStaticUrl()
                        + MovieDbConstants.getBackdropPreferredSize()
                        + tvShow.getBackdrop_path())
                .fit().centerCrop()
                .into(tvShowBackdrop);

        Picasso.with(this)
                .load(MovieDbConstants.getBasicStaticUrl()
                        + MovieDbConstants.getPosterPreferredSize()
                        + tvShow.getPoster_path())
                .fit().centerCrop()
                .into(tvShowCover);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    finish();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displayTvShowDetail(@NonNull TvShowDetail tvShowDetail) {
        if(!"".equals(tvShowDetail.getHomepage())) {
            tvShowInfoTextViews.get(HOMEPAGE).setVisibility(View.VISIBLE);
            tvShowInfoTextViews.get(HOMEPAGE).setText(tvShowDetail.getHomepage());

            tvShowHomepageContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri url = Uri.parse(tvShowInfoTextViews.get(HOMEPAGE).getText().toString());
                    Intent intent = new Intent(Intent.ACTION_VIEW, url);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            });
        }

        if(!"".equals(tvShowDetail.getOverview())) {
            tvShowInfoTextViews.get(OVERVIEW).setVisibility(View.VISIBLE);
            tvShowInfoTextViews.get(OVERVIEW).setText(tvShowDetail.getOverview());
        }

        if(!"".equals(tvShowDetail.getType())) {
            tvShowInfoTextViews.get(TYPE).setVisibility(View.VISIBLE);
            tvShowInfoTextViews.get(TYPE).setText(tvShowDetail.getType());
        }

        if(!"".equals(tvShowDetail.getStatus())) {
            tvShowInfoTextViews.get(STATUS).setVisibility(View.VISIBLE);
            tvShowInfoTextViews.get(STATUS).setText(tvShowDetail.getStatus());
        }
    }

    @Override
    public void displayError() {
        Snackbar.make(findViewById(android.R.id.content),
                getString(R.string.error_tv_show_detail), Snackbar.LENGTH_SHORT).show();
    }
}
