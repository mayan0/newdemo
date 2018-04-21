package com.amap.driverdemo.module.usercenter.widget;

import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amap.driverdemo.R;

public class UserCenterBottomBtnWidget extends RelativeLayout implements View.OnClickListener{
    private Context mContext;

    private View mStartBtn;

    private ViewGroup mInfoBtnArea;
    private View mConfigBtn;
    private View mStopBtn;
    private UserCenterWaitingOrderBtnWidget mUserCenterWaitingOrderBtnWidget;
    private Callback mCallback;

    public static interface Callback{
        public void onStartBtnClicked();
        public void onStopBtnClicked();
        public void onConfigBtnClicked();
    }

    public UserCenterBottomBtnWidget(Context context) {
        super(context);
        init(context);
    }

    public UserCenterBottomBtnWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UserCenterBottomBtnWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        if (context == null) {
            return;
        }

        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.widget_user_center_bottom_btn, this);

        mStartBtn = findViewById(R.id.start_btn);

        mInfoBtnArea = (ViewGroup)findViewById(R.id.info_btn_area);
        mConfigBtn = findViewById(R.id.config_btn);
        mStopBtn = findViewById(R.id.stop_btn);
        mUserCenterWaitingOrderBtnWidget = (UserCenterWaitingOrderBtnWidget)findViewById(R.id.waiting_order_btn);

        mStartBtn.setOnClickListener(this);
        mConfigBtn.setOnClickListener(this);
        mStopBtn.setOnClickListener(this);
    }



    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.start_btn:
                toWatingState();
                if (mCallback != null) {
                    mCallback.onStartBtnClicked();
                }
                break;

            case R.id.stop_btn:
                toInitState();
                if (mCallback != null) {
                    mCallback.onStopBtnClicked();
                }
                break;

            case R.id.config_btn:
                if (mCallback != null) {
                    mCallback.onConfigBtnClicked();
                }
                break;
            default:
                break;
        }
    }

    public void stopListen(){
        toInitState();
    }

    private void toWatingState(){
        mStartBtn.setVisibility(View.GONE);
        mInfoBtnArea.setVisibility(View.VISIBLE);
        mUserCenterWaitingOrderBtnWidget.startAnim();
    }

    private void toInitState(){
        mStartBtn.setVisibility(View.VISIBLE);
        mInfoBtnArea.setVisibility(View.GONE);
        mUserCenterWaitingOrderBtnWidget.stopAnim();
    }
}
