package com.fasteque.playground.views.activities;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.MenuItem;
import android.view.Window;

import com.fasteque.playground.R;
import com.fasteque.playground.helpers.CustomTabActivityHelper;
import com.fasteque.playground.helpers.WebViewFallback;
import com.fasteque.playground.views.fragments.AttributionsFragment;

/**
 * Created by danielealtomare on 27/05/15.
 * Project: Playground
 */
public class AttributionsActivity extends AppCompatActivity implements AttributionsFragment
        .OnPreferenceSelectedListener {

    //@Inject FIXME: do it with dependency injection
    CustomTabActivityHelper customTabActivityHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setAllowEnterTransitionOverlap(true);
            getWindow().setEnterTransition(new Explode());
            getWindow().setExitTransition(new Explode());
        }

        super.onCreate(savedInstanceState);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setupCustomTabHelper();

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new AttributionsFragment())
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        customTabActivityHelper.bindCustomTabsService(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        customTabActivityHelper.unbindCustomTabsService(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finishAfterTransition();
            } else {
                finish();
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void setupCustomTabHelper() {
        // FIXME: do it with dependency injection
        customTabActivityHelper = new CustomTabActivityHelper();
        customTabActivityHelper.setConnectionCallback(connectionCallback);
    }

    private CustomTabActivityHelper.ConnectionCallback connectionCallback = new CustomTabActivityHelper
            .ConnectionCallback() {
        @Override
        public void onCustomTabsConnected() {
            // Use this callback to perform UI changes.
        }

        @Override
        public void onCustomTabsDisconnected() {
            // Use this callback to perform UI changes.
        }
    };

    @Override
    public void onPreferenceWithUriSelected(Uri uri) {
        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            intentBuilder.setToolbarColor(getResources().getColor(R.color.primary, getTheme()));
        } else {
            //noinspection deprecation
            intentBuilder.setToolbarColor(getResources().getColor(R.color.primary));
        }
        intentBuilder.setShowTitle(true);

        CustomTabActivityHelper.openCustomTab(this, intentBuilder.build(), uri, new WebViewFallback());
    }
}
