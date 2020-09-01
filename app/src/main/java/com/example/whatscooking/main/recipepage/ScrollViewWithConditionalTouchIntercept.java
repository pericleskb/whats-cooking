package com.example.whatscooking.main.recipepage;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import androidx.navigation.fragment.NavHostFragment;

public class ScrollViewWithConditionalTouchIntercept extends ScrollView {

    TouchInterceptChecker interceptChecker = null;
    final float scrollThreshold = 100;
    float xStart = 0;
    float yStart = 0;
    float scrollViewStartPositionY = 0;

    public ScrollViewWithConditionalTouchIntercept(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return interceptChecker.shouldInterceptTouch((int) event.getX(), (int) event.getY());
    }

    public void setInterceptChecker(TouchInterceptChecker interceptChecker) {
        this.interceptChecker = interceptChecker;
    }
}
