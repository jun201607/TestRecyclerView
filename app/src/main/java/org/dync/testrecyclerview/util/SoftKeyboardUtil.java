package org.dync.testrecyclerview.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by xiongxuesong-pc on 2016/6/14.
 */
public class SoftKeyboardUtil {


    private static ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
    private static InputMethodManager imm;
    private static int keyboardHeight;// 软键盘的高度
    private static boolean isOpen;
    private static boolean isTouch = true;

    /**
     * 监听键盘高度和键盘时候处于打开状态，在调用的Activity中的onDestroy()方法中调用
     * 该类中的removeGlobalOnLayoutListener()方法来移除监听
     *
     * @param activity
     * @param listener
     */
    public static void observeSoftKeyboard(Activity activity, final OnSoftKeyboardChangeListener listener) {
        final View decorView = activity.getWindow().getDecorView();
        final int statusBarHeight = getStatusBarHeight(activity);// 状态栏的高度
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            private boolean isShowKeyboard;// 软键盘的显示状态

            @Override
            public void onGlobalLayout() {
                onGlobalLayoutListener = this;

                // 应用可以显示的区域。此处包括应用占用的区域，
                // 以及ActionBar和状态栏，但不含设备底部的虚拟按键。
                Rect r = new Rect();
                decorView.getWindowVisibleDisplayFrame(r);
                // 屏幕高度。这个高度不含虚拟按键的高度
                int screenHeight = decorView.getRootView().getHeight();
                int heightDiff = screenHeight - (r.bottom - r.top);
                // 在不显示软键盘时，heightDiff等于状态栏的高度
                // 在显示软键盘时，heightDiff会变大，等于软键盘加状态栏的高度。
                // 所以heightDiff大于状态栏高度时表示软键盘出现了，
                // 这时可算出软键盘的高度，即heightDiff减去状态栏的高度
                if (keyboardHeight == 0 && heightDiff > statusBarHeight) {
                    keyboardHeight = heightDiff - statusBarHeight;
                }
                if (isShowKeyboard) {
                    // 如果软键盘是弹出的状态，并且heightDiff小于等于状态栏高度，
                    // 说明这时软键盘已经收起
                    if (heightDiff <= statusBarHeight) {
                        isShowKeyboard = false;
                        listener.onSoftKeyBoardChange(keyboardHeight, isShowKeyboard);
                    }
                } else {
                    // 如果软键盘是收起的状态，并且heightDiff大于状态栏高度，
                    // 说明这时软键盘已经弹出
                    if (heightDiff > statusBarHeight) {
                        isShowKeyboard = true;
                        listener.onSoftKeyBoardChange(keyboardHeight, isShowKeyboard);
                    }
                }
            }
        });
    }

    public interface OnSoftKeyboardChangeListener {
        void onSoftKeyBoardChange(int softKeybardHeight, boolean isShow);
    }

    public static void removeGlobalOnLayoutListener(Activity activity) {
        final View decorView = activity.getWindow().getDecorView();
        if (SoftKeyboardUtil.onGlobalLayoutListener != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                decorView.getViewTreeObserver().removeGlobalOnLayoutListener(SoftKeyboardUtil.onGlobalLayoutListener);
            } else {
                decorView.getViewTreeObserver().removeOnGlobalLayoutListener(SoftKeyboardUtil.onGlobalLayoutListener);
            }
        }
    }

    /**
     * 获得状态栏高度
     */
    private static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    // 获取 NavigationBar 的高度
    public static int getNavigationBarHeight(Activity activity) {
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    public static void hideKeyboard(Context context, View view) {
        view.requestFocus();
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view != null) {
            imm.hideSoftInputFromWindow(view.getApplicationWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void hideKeyboard(Activity activity) {
        imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus != null) {
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void showKeyboard(Context context, View view) {
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
    }
}