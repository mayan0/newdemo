package com.amap.driverdemo.module.usercenter.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.amap.driverdemo.R;
import com.amap.driverdemo.common.ScheduledExecutorImpl;
import com.amap.driverdemo.common.UIExecutor;

public class UserCenterNewOrderDialog extends Dialog implements View.OnClickListener {
    /**
     * 抢单button
     */
    private View mRushBtn;

    /**
     * 抢单读秒TV
     */
    private TextView mCountDownTV;

    /**
     * 停止听单button
     */
    private View mStopListenBtn;

    private UserCenterNewOrderWidget mUserCenterNewOrderWidget;

    private Callback mCallback;

    private ScheduledExecutorImpl mExecutor;

    //FIXME
    private int countDown = 10;

    public static interface Callback {
        public void onRush();

        public void onStopListen();

        public void onClose();
    }

    public UserCenterNewOrderDialog(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        init(context);
    }

    private void init(Context context) {
        setContentView(R.layout.dialog_new_order);

        mRushBtn = findViewById(R.id.rush_btn_rl);
        mStopListenBtn = findViewById(R.id.stop_listen_btn);
        mUserCenterNewOrderWidget = (UserCenterNewOrderWidget)findViewById(R.id.user_content);
        mCountDownTV = (TextView)findViewById(R.id.count_tv);

        mRushBtn.setOnClickListener(this);
        mStopListenBtn.setOnClickListener(this);
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
        mUserCenterNewOrderWidget.setCallback(mCallback);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mExecutor = new ScheduledExecutorImpl();

        //开始设置读秒数
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                UIExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        if (countDown > 0) {
                            if (mCountDownTV != null) {
                                mCountDownTV.setText("" + countDown--);
                            }
                        } else {
                            mExecutor.cancel();
                            if (mCallback != null) {
                                mCallback.onClose();
                            }
                        }
                    }
                });
            }
        }, 1200, 2000);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if (mExecutor != null) {
            mExecutor.cancel();
        }
    }


    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }

        switch (v.getId()) {
            case (R.id.rush_btn_rl):
                if (mCallback != null) {
                    mCallback.onRush();
                }
                break;
            case (R.id.stop_listen_btn):
                if (mCallback != null) {
                    mCallback.onStopListen();
                }
                break;
            default:
                break;
        }
    }

}
