package com.charmingwong.deegoutils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import java.io.File;

/**
 * <pre>
 *     author: Charming Wong
 *     github: https://github.com/CharmingW
 *     blog  : http://www.jianshu.com/u/05686c7c92af & http://blog.csdn.net/CharmingWong
 *     公众号 ： Charming写字的地方
 *     time  : 2017/09/12
 *     desc  : App相关工具类
 * </pre>
 */

public class AppUtils {

    /**
     * Get external cache directory firstly. If not, get internal cache directory.
     *
     * @param context to get cache directory
     * @param uniqueName custom cache directory
     */
    public static File getDiskCacheDir(@NonNull final Context context, @NonNull final String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
            || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        File cacheDir = new File(cachePath + File.separator + uniqueName);
        if (!cacheDir.exists()) {
            if (cacheDir.mkdirs()) {
                return cacheDir;
            }
        }
        return null;
    }

    /**
     * 获取 App 名字
     *
     * @param context
     * @return
     */
    public static String getAppName(@NonNull final Context context) {
        PackageInfo packageInfo = PackageUtils.getPackageInfo(context);
        return packageInfo == null ? "" : packageInfo.applicationInfo.name;
    }

    /**
     * 获取 App 图标
     *
     * @param context
     * @return
     */
    public static Drawable getAppIcon(@NonNull final Context context) {
        PackageInfo packageInfo = PackageUtils.getPackageInfo(context);
        return packageInfo == null ? null : context.getDrawable(packageInfo.applicationInfo.icon);
    }



    /**
     * 跳转到 App 详情的设置页面
     *
     * @param context context
     */
    public static void openAppDetailsSettings(@NonNull final Context context) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        context.startActivity(intent);
    }
}
