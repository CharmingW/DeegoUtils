package com.charmingwong.deegoutils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import java.io.File;

/**
 * <pre>
 *     author: Charming Wong
 *     github: https://github.com/CharmingW
 *     blog  : http://www.jianshu.com/u/05686c7c92af & http://blog.csdn.net/CharmingWong
 *     公众号 ： Charming写字的地方
 *     time  : 2017/09/12
 *     desc  : Image相关工具类
 * </pre>
 */
public class ImageUtils {

    private ImageUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * Get external content uri from external file.
     *
     * @param context context
     * @param imageFile imageFile
     * @return Uri
     */
    public static Uri getImageExternalContentUri(@NonNull final Context context, @NonNull final File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            new String[]{MediaStore.Images.Media._ID},
            MediaStore.Images.Media.DATA + "=? ",
            new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            cursor.close();
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    /**
     * Get internal content uri from internal file.
     *
     * @param context context
     * @param imageFile imageFile
     * @return Uri
     */
    public static Uri getImageInternalContentUri(@NonNull final Context context, @NonNull final File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
            MediaStore.Images.Media.INTERNAL_CONTENT_URI,
            new String[]{MediaStore.Images.Media._ID},
            MediaStore.Images.Media.DATA + "=? ",
            new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/internal/images/media");
            cursor.close();
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    /**
     * 获取图片url后缀
     *
     * @param url 图片url
     * @return 图片url后缀
     */
    public static String getImageUrlSuffix(@NonNull final String url) {

        String newUrl  = url.toLowerCase();

        if (newUrl.endsWith(".jpg")
            || newUrl.endsWith("jpeg")
            || newUrl.endsWith("png")
            || newUrl.endsWith("gif")
            || newUrl.endsWith("webp")) {
            return newUrl.substring(newUrl.lastIndexOf('.'), newUrl.length());
        }
        return "";
    }
}
