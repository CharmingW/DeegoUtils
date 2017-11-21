package com.charmingwong.deegoutils;

import static android.graphics.Bitmap.createBitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

/**
 * @author : Charming Wong
 * @time : 17-11-21
 * @desc :
 */
public class BlurBitmapUtils {
    //图片缩放比例
    private static final float BITMAP_SCALE = 0.4f;

    /**
     * 模糊图片的具体方法
     *
     * @param context 上下文对象
     * @param sourceBitmap   需要模糊的图片
     * @return 模糊处理后的图片
     */
    public static Bitmap blurBitmap(Context context, Bitmap sourceBitmap,float blurRadius) {
        // 计算图片缩小后的长宽
        int width = Math.round(sourceBitmap.getWidth() * BITMAP_SCALE);
        int height = Math.round(sourceBitmap.getHeight() * BITMAP_SCALE);

        // 将缩小后的图片做为预渲染的图片
        Bitmap inputBitmap = Bitmap.createScaledBitmap(sourceBitmap, width, height, false);
        // 创建一张渲染后的输出图片
        Bitmap outputBitmap = createBitmap(inputBitmap);

        // 创建RenderScript内核对象
        RenderScript rs = RenderScript.create(context);
        // 创建一个模糊效果的RenderScript的工具对象
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间
        // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);

        // 设置渲染的模糊程度, 25f是最大模糊度
        blurScript.setRadius(blurRadius);
        // 设置blurScript对象的输入内存
        blurScript.setInput(tmpIn);
        // 将输出数据保存到输出内存中
        blurScript.forEach(tmpOut);

        // 将数据填充到Allocation中
        tmpOut.copyTo(outputBitmap);

        return outputBitmap;
    }

    /**
     *
     * @param context 上下文对象
     * @param sourceBitmap 需要模糊的图片
     * @param x 裁剪起始点的x坐标
     * @param y 裁剪起始点的y坐标
     * @param width 需要裁剪的宽度
     * @param height 需要裁剪的高度
     * @param blurRadius 模糊半径
     * @return
     */
    public static Bitmap blurBitmap(Context context, Bitmap sourceBitmap, int x, int y, int width, int height, float blurRadius) {

        // 根据参数裁剪Bitmap
        Bitmap cutBitmap = Bitmap.createBitmap(sourceBitmap, x, y, width, height);
        // 模糊化处理
        return blurBitmap(context, cutBitmap, blurRadius);
    }
}