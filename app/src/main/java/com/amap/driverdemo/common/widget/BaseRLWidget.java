package com.amap.driverdemo.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

public abstract class BaseRLWidget extends RelativeLayout {
    protected Context mContext;

    public BaseRLWidget(Context context) {
        super(context);
        initWhenConstruct(context);
    }

    public BaseRLWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWhenConstruct(context);
    }

    public BaseRLWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWhenConstruct(context);
    }

    protected abstract int getLayoutId();

    protected void initWhenConstruct(Context context) {
        mContext = context;

        if (mContext == null) {
            return;
        }

        LayoutInflater.from(mContext).inflate(getLayoutId(), this);
    }
}

