package com.charmingwong.deegoutils;

import android.support.annotation.NonNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <pre>
 *     author: Charming Wong
 *     github: https://github.com/CharmingW
 *     blog  : http://www.jianshu.com/u/05686c7c92af & http://blog.csdn.net/CharmingWong
 *     公众号 ： Charming写字的地方
 *     time  : 2017/09/12
 *     desc  : File相关工具类
 * </pre>
 */
public class FileUtils {

    private FileUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * Get a FileInputStream with a file.
     *
     * @param file to construct a FileInputStream
     * @return FileInputStream
     */
    public static FileInputStream getFileInputStream(@NonNull final File file) {
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            return fis;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get a FileInputStream with a file path.
     *
     * @param filePath to construct a FileInputStream
     * @return FileInputStream
     */
    public static FileInputStream getFileInputStream(@NonNull final String filePath) {
        FileInputStream fis;
        try {
            fis = new FileInputStream(filePath);
            return fis;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     *
     * @param file 文件对象
     * @return boolean 是否创建成功
     */
    public static boolean createFile(@NonNull final File file) {
        File parent = file.getParentFile();
        if (!parent.exists()) {
            if (parent.mkdirs()) {
                try {
                    return file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

}
