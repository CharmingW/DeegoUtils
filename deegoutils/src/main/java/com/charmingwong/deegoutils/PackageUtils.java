package com.charmingwong.deegoutils;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;

/**
 * <pre>
 *     author: Charming Wong
 *     github: https://github.com/CharmingW
 *     blog  : http://www.jianshu.com/u/05686c7c92af & http://blog.csdn.net/CharmingWong
 *     公众号 ： Charming写字的地方
 *     time  : 2017/09/12
 *     desc  : Package相关工具类
 * </pre>
 */
public class PackageUtils {

    private PackageUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取包信息
     *
     * @param context the context to get PackageInfo
     * @return the PackageInfo
     */
    public static PackageInfo getPackageInfo(@NonNull final Context context) {
        try {
            return context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取版本码
     *
     * @param context the context to get system version code
     * @return system version code
     */
    public static int getVersionCode(@NonNull final Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        return packageInfo == null ? -1 : packageInfo.versionCode;
    }

    /**
     * 获取 Base 版本
     *
     * @param context context
     * @return int
     */
    public static int getBaseRevisionCode(Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1 && packageInfo != null) {
            return packageInfo.baseRevisionCode;
        }
        return -1;
    }

    /**
     * 获取版本名
     *
     * @param context the context to get system version name
     * @return system version name
     */
    public static String getVersionName(@NonNull final Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        return packageInfo == null ? "" : packageInfo.versionName;
    }

    /**
     * 获取包名
     *
     * @param context the context to get package name
     * @return the package name
     */
    public static String getPackageName(@NonNull final Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        return packageInfo == null ? "" : packageInfo.packageName;
    }

    /**
     * 获取 ShareUserId
     *
     * @param context the context to get share user id
     * @return the share user id
     */
    public static String getShareUserId(@NonNull final Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        return packageInfo == null ? "" : packageInfo.sharedUserId;
    }

    /**
     * 获取所有的 Activity 信息
     *
     * @param context the context to get all activity info
     * @return ActivityInfo[]
     */
    public static ActivityInfo[] getActivities(@NonNull final Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        return packageInfo == null ? null : packageInfo.activities;
    }
}
