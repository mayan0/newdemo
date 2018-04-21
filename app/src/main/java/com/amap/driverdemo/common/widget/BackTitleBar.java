package com.amap.driverdemo.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.amap.driverdemo.R;

public class BackTitleBar extends TitleBar{
    public BackTitleBar(Context context) {
        super(context);
    }

    public BackTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BackTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initWhenConstruct(Context context) {
        super.initWhenConstruct(context);

        setLeftIcon(R.drawable.ic_btn_back);
    }



}
