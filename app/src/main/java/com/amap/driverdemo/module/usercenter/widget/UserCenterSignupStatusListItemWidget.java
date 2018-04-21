package com.amap.driverdemo.module.usercenter.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import com.amap.driverdemo.R;
import com.amap.driverdemo.common.widget.BaseRLWidget;
import com.amap.driverdemo.model.SignupStatusModel;

public class UserCenterSignupStatusListItemWidget extends BaseRLWidget {
    private TextView titleTV;
    private TextView subTitleTV;
    private ImageView statusIV;

    public UserCenterSignupStatusListItemWidget(Context context) {
        super(context);
    }

    public UserCenterSignupStatusListItemWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UserCenterSignupStatusListItemWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.widget_user_center_signup_status_item;
    }

    @Override
    protected void initWhenConstruct(Context context) {
        super.initWhenConstruct(context);

        titleTV = (TextView)findViewById(R.id.title_tv);
        subTitleTV = (TextView)findViewById(R.id.status_tv);
        statusIV = (ImageView)findViewById(R.id.status_iv);
    }

    public void init(SignupStatusModel signupStatusModel) {
        if (signupStatusModel == null || TextUtils.isEmpty(signupStatusModel.title)) {
            return;
        }
        init(signupStatusModel.title, signupStatusModel.isChecked);
    }

    public float getTitleHeight(){
        //FIXME
        return 20;
    }

    private void init(String title, boolean isChecked) {
        titleTV.setText(title);

        if (isChecked) {
            titleTV.setTextColor(mContext.getResources().getColor(R.color.user_status_title_checked_color));
            subTitleTV.setText("已审核");
            subTitleTV.setTextColor(mContext.getResources().getColor(R.color.user_status_subtitle_checked_color));
            statusIV.setImageResource(R.mipmap.yoda_slider_success);
        } else {
            titleTV.setTextColor(mContext.getResources().getColor(R.color.user_status_subtitle_unchecked_color));
            subTitleTV.setText("待审核");
            subTitleTV.setTextColor(mContext.getResources().getColor(R.color.user_status_subtitle_unchecked_color));
            statusIV.setImageResource(R.mipmap.passport_ic_check_sms_time);
        }
    }
}
