package com.amap.driverdemo.module.usercenter.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import com.amap.driverdemo.R;
import com.amap.driverdemo.common.widget.BaseRLWidget;
import com.amap.driverdemo.model.SignupStatusModel;

public class UserCenterSignupStatusWidget extends BaseRLWidget {
    private UserCenterSignupStatusListWidget mUserCenterSignupStatusListWidget;

    public UserCenterSignupStatusWidget(Context context) {
        super(context);
    }

    public UserCenterSignupStatusWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UserCenterSignupStatusWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.widget_user_center_signup_status;
    }

    @Override
    protected void initWhenConstruct(Context context) {
        super.initWhenConstruct(context);
        mUserCenterSignupStatusListWidget = (UserCenterSignupStatusListWidget)findViewById(R.id.status_list);
        mUserCenterSignupStatusListWidget.init(getFakeData());
    }

    private List<SignupStatusModel> getFakeData(){
        List<SignupStatusModel> list = new ArrayList<>();

        list.add(new SignupStatusModel("提交注册材料", true));
        list.add(new SignupStatusModel("审核司机信息:身份证、驾驶证", false));
        list.add(new SignupStatusModel("审核车辆信息:行驶证", false));
        list.add(new SignupStatusModel("背景调查", false));

        return list;
    }
}


