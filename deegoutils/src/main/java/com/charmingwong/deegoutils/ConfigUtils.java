package com.charmingwong.deegoutils;

import android.os.StrictMode;

/**
 * <pre>
 *     author: Charming Wong
 *     github: https://github.com/CharmingW
 *     blog  : http://www.jianshu.com/u/05686c7c92af & http://blog.csdn.net/CharmingWong
 *     公众号 ： Charming写字的地方
 *     time  : 17-9-13
 *     desc  : Config 相关工具类
 * </pre>
 */
public class ConfigUtils {

    private ConfigUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 设置 StrictMode
     */
    public static void setStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
            .detectDiskReads()
            .detectDiskWrites()
            .detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects()
            .detectLeakedClosableObjects()
            .penaltyLog()
            .penaltyDeath()
            .build());
    }

}
