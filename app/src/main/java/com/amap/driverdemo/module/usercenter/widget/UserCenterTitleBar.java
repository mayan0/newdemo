package com.amap.driverdemo.module.usercenter.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.amap.driverdemo.R;
import com.amap.driverdemo.common.widget.TitleBar;

public class UserCenterTitleBar extends TitleBar {
    public UserCenterTitleBar(Context context) {
        super(context);
    }

    public UserCenterTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UserCenterTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initWhenConstruct(Context context) {
        super.initWhenConstruct(context);

        setLeftIcon(R.mipmap.ic_drawer_account);
        setTitle("司机端");
    }
}
