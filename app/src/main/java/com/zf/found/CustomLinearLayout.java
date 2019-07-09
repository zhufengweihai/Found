package com.zf.found;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;

class CustomLinearLayout extends ViewGroup {

    public CustomLinearLayout(Context context) {
        super(context);
    }

    public CustomLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);

        measureChild(getChildAt(1), widthMeasureSpec, heightMeasureSpec);

        int child0MaxHeight = h - getChildAt(1).getMeasuredHeight();
        //int photoHeight = ((ImageView) getChildAt(0)).getDrawable().getIntrinsicHeight();
        //child0MaxHeight = child0MaxHeight > photoHeight ? photoHeight : child0MaxHeight;

        int child0MeasureSpec = getChildMeasureSpec(MeasureSpec.makeMeasureSpec(child0MaxHeight, MeasureSpec.AT_MOST), 0, child0MaxHeight);
        measureChild(getChildAt(0), getChildMeasureSpec(widthMeasureSpec, 0, w), child0MeasureSpec);

        int child2MaxHeight = h - getChildAt(0).getMeasuredHeight() - getChildAt(1).getMeasuredHeight();
        int child2MeasureSpec = getChildMeasureSpec(MeasureSpec.makeMeasureSpec(child2MaxHeight, MeasureSpec.AT_MOST), 0, child2MaxHeight);
        measureChild(getChildAt(2), getChildMeasureSpec(widthMeasureSpec, 0, w), child2MeasureSpec);

        setMeasuredDimension(w, getChildAt(0).getMeasuredHeight() + getChildAt(1).getMeasuredHeight() + getChildAt(2).getMeasuredHeight());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        getChildAt(0).layout(0, 0, getChildAt(0).getMeasuredWidth(), getChildAt(0).getMeasuredHeight());
        getChildAt(1).layout(0, getChildAt(0).getBottom(), getChildAt(1).getMeasuredWidth(), getChildAt(0).getBottom() + getChildAt(1).getMeasuredHeight());
        getChildAt(2).layout(0, getChildAt(1).getBottom(), getChildAt(2).getMeasuredWidth(), getChildAt(1).getBottom() + getChildAt(2).getMeasuredHeight());
    }
}