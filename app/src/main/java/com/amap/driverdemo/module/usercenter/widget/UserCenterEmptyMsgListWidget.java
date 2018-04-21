package com.amap.driverdemo.module.usercenter.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.amap.driverdemo.R;
import com.amap.driverdemo.common.widget.BaseRLWidget;

public class UserCenterEmptyMsgListWidget extends BaseRLWidget {

    public UserCenterEmptyMsgListWidget(Context context) {
        super(context);
    }

    public UserCenterEmptyMsgListWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UserCenterEmptyMsgListWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.widget_user_center_empty_list_hint;
    }
}
