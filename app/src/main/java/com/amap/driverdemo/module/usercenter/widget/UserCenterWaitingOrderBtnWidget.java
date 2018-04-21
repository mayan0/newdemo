package com.amap.driverdemo.module.usercenter.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.amap.driverdemo.R;
import com.amap.driverdemo.common.widget.BaseRLWidget;

public class UserCenterWaitingOrderBtnWidget extends BaseRLWidget {

    private UserCenterPulseLayout mUserCenterPulseLayout;

    public UserCenterWaitingOrderBtnWidget(Context context) {
        super(context);
    }

    public UserCenterWaitingOrderBtnWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UserCenterWaitingOrderBtnWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.widget_user_center_waiting_order_btn;
    }

    @Override
    protected void initWhenConstruct(Context context) {
        super.initWhenConstruct(context);

        mUserCenterPulseLayout = (UserCenterPulseLayout)findViewById(R.id.pulse_rl);
    }

    public void startAnim(){
        mUserCenterPulseLayout.start();
    }

    public void stopAnim(){
        mUserCenterPulseLayout.stop();
    }
}
