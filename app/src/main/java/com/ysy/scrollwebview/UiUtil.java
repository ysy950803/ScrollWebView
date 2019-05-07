package com.ysy.scrollwebview;

import android.graphics.Rect;
import android.view.View;

/**
 * 与各种UI适配、屏幕、View等相关工具类
 */
public class UiUtil {

    private static volatile Rect sTempViewRect = new Rect();

    /**
     * 判断View是否部分或完全可见
     *
     * @return 一点可见即返回true，完全隐藏时才返回false
     */
    public static boolean isViewShowReally(View view) {
        if (view != null && view.getVisibility() == View.VISIBLE) {
            return view.getGlobalVisibleRect(sTempViewRect);
        }
        return false;
    }

    /**
     * 判断View是否部分或完全隐藏
     *
     * @return 一点隐藏即返回true，完全可见时才返回false
     */
    public static boolean isViewHideReally(View view) {
        if (view != null && view.getVisibility() != View.VISIBLE) {
            view.getLocalVisibleRect(sTempViewRect);
            return sTempViewRect.top != 0;
        }
        return true;
    }
}
