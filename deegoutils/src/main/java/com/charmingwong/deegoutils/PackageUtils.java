package com.charmingwong.deegoutils;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * Created by CharmingWong on 2017/8/22
 */
public class PackageUtils {

    private PackageUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * @param context the context to get PackageInfo
     * @return the PackageInfo
     */
    public static PackageInfo getPackageInfo(Context context) {
        try {
            return context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param context the context to get system version code
     * @return system version code
     */
    public static int getVersionCode(Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        return packageInfo == null ? -1 : packageInfo.versionCode;
    }

    public static int getBaseRevisionCode(Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1 && packageInfo != null) {
            return packageInfo.baseRevisionCode;
        }
        return -1;
    }

    /**
     * @param context the context to get system version name
     * @return system version name
     */
    public static String getVersionName(Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        return packageInfo == null ? "" : packageInfo.versionName;
    }

    /**
     * @param context the context to get package name
     * @return the package name
     */
    public static String getPackageName(Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        return packageInfo == null ? "" : packageInfo.packageName;
    }

    /**
     * @param context the context to get share user id
     * @return the share user id
     */
    public static String getShareUserId(Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        return packageInfo == null ? "" : packageInfo.sharedUserId;
    }

    /**
     * @param context the context to get all activity info
     * @return
     */
    public static ActivityInfo[] getActivities(Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        return packageInfo == null ? null : packageInfo.activities;
    }
}
