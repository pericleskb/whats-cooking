package com.example.whatscooking.main.recipepage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class ScrollViewWithConditionalTouchIntercept extends ScrollView {

    TouchInterceptChecker interceptChecker = null;

    public ScrollViewWithConditionalTouchIntercept(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (interceptChecker != null) {
            return interceptChecker.shouldInterceptTouch((int) event.getX(), (int) event.getY());
        }
        return false;
    }

    public void setInterceptChecker(TouchInterceptChecker interceptChecker) {
        this.interceptChecker = interceptChecker;
    }
}
