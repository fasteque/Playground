package com.fasteque.playground.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by danielealtomare on 01/07/15.
 * Project: Playground
 */
public class ConnectionUtils {

    /**
     * A simple utility method to know if the device is connected to WiFi.
     * @param context
     *          Application context.
     * @return
     *          true if the device is connected to Wifi, false otherwise.
     */
    public static boolean isConnectedToWiFi(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting() && activeNetwork.getType() ==
                ConnectivityManager.TYPE_WIFI;
    }
}
