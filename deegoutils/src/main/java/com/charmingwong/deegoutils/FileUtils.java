package com.charmingwong.deegoutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by CharmingWong on 2017/8/24
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
    public static FileInputStream getFileInputStream(File file) {
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
    public static FileInputStream getFileInputStream(String filePath) {
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
     * @param file
     * @return boolean
     */
    public static boolean createFile(File file) {
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
