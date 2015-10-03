package com.fasteque.playground.helpers;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by danielealtomare on 03/10/15.
 * Project: Playground
 */
public class WebviewFallback implements CustomTabActivityHelper.CustomTabFallback {
    @Override
    public void openUri(Activity activity, Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        activity.startActivity(intent);
    }
}
