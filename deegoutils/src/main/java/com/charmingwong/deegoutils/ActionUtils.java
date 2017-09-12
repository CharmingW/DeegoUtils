package com.charmingwong.deegoutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by CharmingWong on 2017/8/24
 */
public class ActionUtils {

    private ActionUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * A method help you back to home.
     *
     * @param context the context to start Activity
     */
    public static void backToHome(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        context.startActivity(intent);
    }

    /**
     * Simulate a click event at a specified view by specifying duration.
     *
     * @param view the view you simulate click
     * @param x the clicked x-coordinate
     * @param y the clicked y-coordinate
     * @param duration the duration of click
     */
    public static void simulateClick(View view, float x, float y, long duration) {
        long downTime = SystemClock.uptimeMillis();
        MotionEvent event = MotionEvent.obtain(downTime, downTime, MotionEvent.ACTION_DOWN, x, y, 0);
        view.onTouchEvent(event);
        event = MotionEvent.obtain(downTime + duration, downTime + duration, MotionEvent.ACTION_UP, x, y, 0);
        view.onTouchEvent(event);
        event.recycle();
    }

    /**
     * Simulate a scroll action at a specified view by specifying duration.
     *
     * @param view the view you simulate scroll
     * @param startX the start x-coordinate of finger
     * @param startY the start y-coordinate of finger
     * @param endX the end x-coordinate of finger
     * @param endY the end y-coordinate of finger
     * @param duration the duration of scroll
     */
    public static void simulateFingerScroll(final View view, final int startX, final int startY, final int endX, final int endY, final int duration) {
        final long downTime = SystemClock.uptimeMillis();
        final MotionEvent event = MotionEvent.obtain(downTime, downTime, MotionEvent.ACTION_DOWN, startX, startY, 0);
        view.onTouchEvent(event);

        final int deltaX = endX - startX;
        final int deltaY = endY - startY;

        new Thread(new Runnable() {
            @Override
            public void run() {
                int tempX, tempY;
                int count = duration / 5;

                for (int i = 1; i < count; i++) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    tempX = (startX + i * deltaX / count);
                    tempY = (startY + i * deltaY / count);
                    final MotionEvent event = MotionEvent.obtain(downTime + i * 5, downTime + i * 5, MotionEvent.ACTION_MOVE, tempX, tempY, 0);

                    ((Activity) view.getContext()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            view.onTouchEvent(event);
                        }
                    });
                }

                ((Activity) view.getContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final MotionEvent event = MotionEvent.obtain(downTime + duration, downTime + duration, MotionEvent.ACTION_UP, endX, endY, 0);
                        view.onTouchEvent(event);
                    }
                });
            }
        }).start();

        event.recycle();
    }
}
