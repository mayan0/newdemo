package com.amap.driverdemo.module.useraccount.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.amap.driverdemo.R;
import com.amap.driverdemo.common.widget.BaseRLWidget;

public class UserAccountMoneyWidget extends BaseRLWidget{
    public UserAccountMoneyWidget(Context context) {
        super(context);
    }

    public UserAccountMoneyWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UserAccountMoneyWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.widget_user_account_money_widget;
    }
}
