package com.charmingwong.deegoutils;

import java.io.Closeable;
import java.io.IOException;

/**
 * <pre>
 *     author: Charming Wong
 *     github: https://github.com/CharmingW
 *     blog  : http://www.jianshu.com/u/05686c7c92af & http://blog.csdn.net/CharmingWong
 *     公众号 ： Charming写字的地方
 *     time  : 17-9-13
 *     desc  : 相关工具类
 * </pre>
 */
public class CloseUtils {

    private CloseUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 关闭IO
     *
     * @param closeables closeables
     */
    public static void close(final Closeable... closeables) {

        if (closeables == null) {
            return;
        }

        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
