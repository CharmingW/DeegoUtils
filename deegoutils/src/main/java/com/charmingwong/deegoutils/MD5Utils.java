package com.charmingwong.deegoutils;

import android.support.annotation.NonNull;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * <pre>
 *     author: Charming Wong
 *     github: https://github.com/CharmingW
 *     blog  : http://www.jianshu.com/u/05686c7c92af & http://blog.csdn.net/CharmingWong
 *     公众号 ： Charming写字的地方
 *     time  : 2017/09/12
 *     desc  : MD5相关工具类
 * </pre>
 */

public class MD5Utils {

    /**
     * Digest a string to the other string.
     *
     * @param key source string
     * @return digested string
     */
    public static String generateHashKey(@NonNull final String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xFF & aByte);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * Digest a byte array to a string.
     *
     * @param bytes source byte array
     * @return digested string
     */
    public static String BytesToMD5(@NonNull final byte[] bytes) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(bytes);
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(Arrays.hashCode(bytes));
        }
        return cacheKey;
    }

}
