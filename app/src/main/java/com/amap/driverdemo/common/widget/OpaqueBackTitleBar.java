package com.amap.driverdemo.common.widget;

import android.content.Context;
import android.util.AttributeSet;

public class OpaqueBackTitleBar extends BackTitleBar{
    public OpaqueBackTitleBar(Context context) {
        super(context);
    }

    public OpaqueBackTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OpaqueBackTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initWhenConstruct(Context context) {
        super.initWhenConstruct(context);
        setBackgroundDrawable(null);
    }
}
