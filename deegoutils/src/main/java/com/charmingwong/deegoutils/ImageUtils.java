package com.charmingwong.deegoutils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by CharmingWong on 17-9-12.
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
    public static Uri getImageExternalContentUri(Context context, java.io.File imageFile) {
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
    public static Uri getImageInternalContentUri(Context context, java.io.File imageFile) {
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
    public static String getImageUrlSuffix(String url) {

        if (url.endsWith(".jpg")
            || url.endsWith("jpeg")
            || url.endsWith("png")
            || url.endsWith("gif")
            || url.endsWith("webp")) {
            return url.substring(url.lastIndexOf('.'), url.length());
        }
        return "";
    }
}
