package com.charmingwong.deegoutils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.view.View;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <pre>
 *     author: Charming Wong
 *     github: https://github.com/CharmingW
 *     blog  : http://www.jianshu.com/u/05686c7c92af & http://blog.csdn.net/CharmingWong
 *     公众号 ： Charming写字的地方
 *     time  : 2017/09/12
 *     desc  : Bitmap相关工具类
 * </pre>
 */
public class BitmapUtils {

    private BitmapUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * @param bitmap The bitmap to be converted
     * @return byte array converted from the bitmap
     */
    public static byte[] bitmapToByteArray(@NonNull final Bitmap bitmap) {

        ByteArrayOutputStream baos;

        try {
            baos = new ByteArrayOutputStream();

            // compression is valid for baos but bitmap never changes
            boolean isCompressed = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

            if (isCompressed) {
                return baos.toByteArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @param bytes this byte array to be converted
     * @return bitmap converted from the byte array
     */
    public static Bitmap byteArrayToBitmap(@NonNull final byte[] bytes) {

        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * @param bitmap The bitmap to be converted
     * @return string encoded with Base64 from the bitmap
     */
    public static String bitmapToBase64(@NonNull final Bitmap bitmap) {

        // the string to return
        String result;
        // bitmap convert to byte array
        byte[] byteArray = bitmapToByteArray(bitmap);
        // byte array encode to string
        result = Base64.encodeToString(byteArray, Base64.DEFAULT);
        return result;
    }

    /**
     * @param base64String the Base64 string to be converted
     * @return bitmap decoded with Base64 from string
     */
    public static Bitmap base64ToBitmap(@NonNull final String base64String) {

        byte[] bytes = Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * @param bitmap The bitmap to be converted
     * @return drawable converted from the bitmap
     */
    public static Drawable bitmapToDrawable(@NonNull final Bitmap bitmap) {

        return new BitmapDrawable(Resources.getSystem(), bitmap);
    }

    /**
     * @param drawable the drawable to be converted
     * @return bitmap converted from the drawable
     */
    public static Bitmap drawableToBitmap(@NonNull final Drawable drawable) {

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        // We ask for the bounds if they have been set as they would be most
        // correct, then we check we are  > 0
        final int width = !drawable.getBounds().isEmpty() ?
                drawable.getBounds().width() : drawable.getIntrinsicWidth();

        final int height = !drawable.getBounds().isEmpty() ?
                drawable.getBounds().height() : drawable.getIntrinsicHeight();

        // Now we check we are > 0
        final Bitmap bitmap = Bitmap.createBitmap(
                width <= 0 ? 1 : width,
                height <= 0 ? 1 : height,
                Bitmap.Config.ARGB_8888
        );
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    /**
     * @param bitmap   The bitmap to be converted
     * @param filePath the path of the file to be saved
     * @return file saved from the bitmap
     */
    public static File bitmapToFile(@NonNull final Bitmap bitmap, String filePath) {

        FileOutputStream fos = null;
        File file;

        file = new File(filePath);

        try {
            if (!file.getParentFile().exists()) {
                if (!file.getParentFile().mkdirs()) {
                    return null;
                }
            }

            fos = new FileOutputStream(file);
            byte[] bytes = bitmapToByteArray(bitmap);

            if (bytes != null) {
                fos.write(bytes);
                fos.flush();
                return file;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

//    public static Bitmap fileToBitmap

    /**
     * @param view the view to be converted
     * @return bitmap converted from the view
     */
    public static Bitmap viewToBitmap(@NonNull final View view) {

        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        return view.getDrawingCache();
    }
}
