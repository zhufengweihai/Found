package com.zf.found;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

class CardLayout extends LinearLayout {

    public CardLayout(Context context) {
        super(context);
    }

    public CardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);

        // measure 一下 card header
        measureChild(getChildAt(0), widthMeasureSpec, heightMeasureSpec);
        // measure 一下 card footer
        measureChild(getChildAt(2), widthMeasureSpec, heightMeasureSpec);

        // 计算 card content 部分最大可占用的高度
        int cardMaxHeight = h - getChildAt(0).getMeasuredHeight() - getChildAt(2).getMeasuredHeight();

        // measure 一下 card content
        measureChild(getChildAt(1), getChildMeasureSpec(widthMeasureSpec, 0, w), getChildMeasureSpec(MeasureSpec.makeMeasureSpec(cardMaxHeight, MeasureSpec.AT_MOST), 0, cardMaxHeight));

        // 整个 card 的高度
        setMeasuredDimension(w, getChildAt(0).getMeasuredHeight() + getChildAt(1).getMeasuredHeight() + getChildAt(2).getMeasuredHeight());

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 将三个 child 分别 layout
        getChildAt(0).layout(0, 0, getChildAt(0).getMeasuredWidth(), getChildAt(0).getMeasuredHeight());
        getChildAt(1).layout(0, getChildAt(0).getBottom(), getChildAt(1).getMeasuredWidth(), getChildAt(0).getBottom() + getChildAt(1).getMeasuredHeight());
        getChildAt(2).layout(0, getChildAt(1).getBottom(), getChildAt(2).getMeasuredWidth(), getChildAt(1).getBottom() + getChildAt(2).getMeasuredHeight());
    }

}