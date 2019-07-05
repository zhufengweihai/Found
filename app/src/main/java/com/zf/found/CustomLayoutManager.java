package com.zf.found;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;

public class CustomLayoutManager extends LinearLayoutManager {
    private boolean isScrollEnabled = true;

    public CustomLayoutManager(Context context, int orientation, boolean reverseLayout, boolean isScrollEnabled) {
        super(context, orientation, reverseLayout);
        this.isScrollEnabled = isScrollEnabled;
    }

    public CustomLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, boolean isScrollEnabled) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.isScrollEnabled = isScrollEnabled;
    }

    public CustomLayoutManager(Context context) {
        super(context);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }

    @Override
    public boolean canScrollHorizontally() {
        return isScrollEnabled && super.canScrollHorizontally();
    }
}