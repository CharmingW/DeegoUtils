package com.charmingwong.deegoutils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by CharmingWong on 2016/12/10.
 */

public class NetworkUtils {

    /**
     * Judge network state.
     *
     * @param context to get ConnectivityManager
     * @return the result if network available
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable();
    }
}
