package com.charmingwong.deegoutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author: Charming Wong
 *     github: https://github.com/CharmingW
 *     blog  : http://www.jianshu.com/u/05686c7c92af & http://blog.csdn.net/CharmingWong
 *     公众号 ： Charming写字的地方
 *     time  : 2017/09/12
 *     desc  : Activity相关工具类
 * </pre>
 */

public class ActivityUtils {

    private static final List<Activity> mActivities = new ArrayList<>();

    private ActivityUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 添加 fragment 到 activity
     *
     * @param fragmentManager fragmentManager
     * @param fragment fragment
     * @param frameId frameId
     */
    public static void addFragmentToActivity(@NonNull final FragmentManager fragmentManager,
        @NonNull final Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.commit();
    }

    /**
     * 判断是否存在Activity
     *
     * @param packageName 包名
     * @param className activity全路径类名
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isActivityExists(@NonNull final Context context, @NonNull final String packageName,
        @NonNull final String className) {
        Intent intent = new Intent();
        intent.setClassName(packageName, className);
        return !(context.getPackageManager().resolveActivity(intent, 0) == null ||
            intent.resolveActivity(context.getPackageManager()) == null ||
            context.getPackageManager().queryIntentActivities(intent, 0).size() == 0);
    }

    /**
     * 显示键盘
     *
     * @param activity activity
     */
    public static void showInputMethod(@NonNull final Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    /**
     * 隐藏键盘
     *
     * @param activity activity
     */
    public static void hideInputMethod(@NonNull final Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 添加 Activity 进 List 集合
     *
     * @param activity activity
     */
    public static void addActivity(@NonNull final Activity activity) {
        mActivities.add(activity);
    }

    /**
     * 将 Activity 从 List 集合移除
     *
     * @param activity activity
     */
    public static void removeActivity(@NonNull final Activity activity) {
        mActivities.remove(activity);
    }

    /**
     * 销毁所有的 Activity 并从 List 移除
     */
    public static void finishAllActivities() {
        for (Activity activity : mActivities) {
            if (!activity.isFinishing()) {
                activity.finish();
                mActivities.remove(activity);
            }
        }
    }

}
