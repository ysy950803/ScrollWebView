package com.ysy.scrollwebview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.core.widget.NestedScrollView;

public class MyScrollView extends NestedScrollView {

    private boolean mIsWebViewOnBottom;
    private boolean mIsRecLayoutShow;
    private float mDownY;

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float y = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = y;
                break;
            case MotionEvent.ACTION_MOVE: {
                float dy = y - mDownY;
                if (dy < 0) { // 手指向上滑
                    if (!mIsWebViewOnBottom) {
                        // 网页未到底，不拦截事件
                        return false;
                    }
                } else { // 手指向下滑
                    if (!mIsRecLayoutShow) {
                        // 相关推荐区域完全隐藏，不拦截事件
                        return false;
                    }
                }
                break;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    public void setIsWebViewOnBottom(boolean onBottom) {
        this.mIsWebViewOnBottom = onBottom;
    }

    public void setIsRecLayoutShow(boolean isRecLayoutShow) {
        this.mIsRecLayoutShow = isRecLayoutShow;
    }
}
