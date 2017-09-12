package com.charmingwong.deegoutils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by CharmingWong on 17-9-12.
 */

public class ActivityUtils {

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
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
        @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.commit();
    }

    /**
     * 显示键盘
     *
     * @param activity activity
     */
    public static void showInputMethod(Activity activity) {
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
    public static void hideInputMethod(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
