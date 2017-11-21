package com.charmingwong.deegoutils;

import java.io.File;

/**
 * <pre>
 *     author: Charming Wong
 *     github: https://github.com/CharmingW
 *     blog  : http://www.jianshu.com/u/05686c7c92af & http://blog.csdn.net/CharmingWong
 *     公众号 ： Charming写字的地方
 *     time  : 17-10-26
 *     desc  :
 * </pre>
 */
public class PhoneUtils {

    public PhoneUtils() {
        throw new UnsupportedOperationException("cannot instantiated");
    }

    /**
     * 判断手机是否拥有Root权限
     *
     * @return 有root权限返回true，否则返回false。
     */
    public boolean isRoot() {
        boolean bool = false;
        try {
            bool = new File("/system/bin/su").exists() || new File("/system/xbin/su").exists();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool;
    }
}
