package com.charmingwong.deegoutils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

/**
 * <pre>
 *     author: Charming Wong
 *     github: https://github.com/CharmingW
 *     blog  : http://www.jianshu.com/u/05686c7c92af & http://blog.csdn.net/CharmingWong
 *     公众号 ： Charming写字的地方
 *     time  : 2017/09/12
 *     desc  : Permission相关工具类
 * </pre>
 */

public class PermissionUtils {

    private PermissionUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 当 Android 版本 > Android M 时，检查 WRITE_EXTERNAL_STORAGE 权限
     *
     * @param activity activity
     * @return 是否有权限
     */
    public static boolean isStoragePermissionGranted(@NonNull final Activity activity) {
        if (Build.VERSION.SDK_INT >= VERSION_CODES.M) {
            if (activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat
                    .requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }
}
