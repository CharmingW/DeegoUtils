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
     * 将Bitmap转换成字节数据
     *
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
     * 将字节数据转换成Bitmap
     *
     * @param bytes this byte array to be converted
     * @return bitmap converted from the byte array
     */
    public static Bitmap byteArrayToBitmap(@NonNull final byte[] bytes) {

        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * 将Bitmap编码成base64字符串
     *
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
     * 将base64字符串译码成Bitmap
     *
     * @param base64String the Base64 string to be converted
     * @return bitmap decoded with Base64 from string
     */
    public static Bitmap base64ToBitmap(@NonNull final String base64String) {

        byte[] bytes = Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * 将Bitmap转换成Drawable
     *
     * @param bitmap The bitmap to be converted
     * @return drawable converted from the bitmap
     */
    public static Drawable bitmapToDrawable(@NonNull final Bitmap bitmap) {

        return new BitmapDrawable(Resources.getSystem(), bitmap);
    }

    /**
     * 将Drawable转换成Bitmap
     *
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
     * 将Bitmap保存到文件中
     *
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
     * 将view呈现的视图转换为Bitmap图像
     *
     * @param view the view to be converted
     * @return bitmap converted from the view
     */
    public static Bitmap viewToBitmap(@NonNull final View view) {

        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        return view.getDrawingCache();
    }

    /**
     * 根据图片的宽高和ImageView的宽高信息，计算出合适的inSampleSize
     *
     * @param options BitmapFactory decode bitmap传入的options
     * @param reqWidth ImageView的width
     * @param reqHeight ImageView的height
     * @return 合适的options.inSampleSize
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,
        int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }
}


