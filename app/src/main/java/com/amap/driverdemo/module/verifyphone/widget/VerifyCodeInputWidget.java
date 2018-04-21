package com.amap.driverdemo.module.verifyphone.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.amap.driverdemo.R;
import com.amap.driverdemo.common.ScheduledExecutorImpl;
import com.amap.driverdemo.common.UIExecutor;
import com.amap.driverdemo.common.widget.BaseRLWidget;
import com.amap.driverdemo.module.verifyphone.widget.VerifyCodeInputETWidget.OnInputFinishListener;

public class VerifyCodeInputWidget extends BaseRLWidget implements View.OnClickListener {

    public static interface Callback {
        public void onInputFinished(String text);

        public void onResend();
    }

    private int mCountVal = 60;

    private TextView mPhoneTV;

    private View mCountLL;
    private TextView mCountTV;
    private TextView mResendTV;

    private Callback mCallback;

    private VerifyCodeInputETWidget mVerifyCodeInputETWidget;

    public VerifyCodeInputWidget(Context context) {
        super(context);
    }

    public VerifyCodeInputWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerifyCodeInputWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.widget_verify_code_input;
    }

    @Override
    protected void initWhenConstruct(Context context) {
        super.initWhenConstruct(context);

        mPhoneTV = (TextView)findViewById(R.id.phone_tv);
        mCountLL = findViewById(R.id.hint_ll);
        mCountTV = (TextView)findViewById(R.id.count_tv);
        mResendTV = (TextView)findViewById(R.id.resend_tv);
        mVerifyCodeInputETWidget = (VerifyCodeInputETWidget)findViewById(R.id.verify_code_input);

        mResendTV.setOnClickListener(this);

        mVerifyCodeInputETWidget.setOnCodeFinishListener(new OnInputFinishListener() {
            @Override
            public void onComplete(String content) {
                if (mCallback != null) {
                    mCallback.onInputFinished(content);
                }
            }
        });
    }

    public void setStartCount(int val) {
        this.mCountVal = val;
    }

    private ScheduledExecutorImpl mExecutor;

    private void showResendBtn() {
        mResendTV.setVisibility(View.VISIBLE);
        mCountTV.setVisibility(View.GONE);
    }

    private void closeResendBtn() {
        mResendTV.setVisibility(View.GONE);
        mCountTV.setVisibility(View.VISIBLE);
    }


    public void setPhoneText(String phoneText) {
        if (TextUtils.isEmpty(phoneText)) {
            return;
        }

        mPhoneTV.setText(phoneText);
    }

    public void startCount(){
        if (mExecutor != null) {
            mExecutor.cancel();
        }

        mExecutor = new ScheduledExecutorImpl();

        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                UIExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        if (mCountVal <= 0) {
                            mExecutor.cancel();
                            mCountVal = 60;

                            showResendBtn();
                            return;
                        }

                        mCountTV.setText("" + (mCountVal--) + "秒后重新获取");
                    }
                });

            }
        }, 1000);
    }

    public void stopCount(){
        if (mExecutor != null) {
            mExecutor.cancel();
        }
    }

    @Override
    public void onClick(View v) {
        if (v != null && R.id.resend_tv == v.getId()) {
            if (mCountTV.getVisibility() == View.VISIBLE) {
                return;
            }

            closeResendBtn();
            startCount();

            if (mCallback != null) {
                mCallback.onResend();
            }
        }
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }
}
