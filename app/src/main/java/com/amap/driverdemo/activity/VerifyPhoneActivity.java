package com.amap.driverdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import com.amap.driverdemo.R;
import com.amap.driverdemo.activity.login.RegistCarActivity;
import com.amap.driverdemo.common.widget.TitleBar;
import com.amap.driverdemo.common.widget.TitleBar.Callback;
import com.amap.driverdemo.module.verifyphone.widget.VerifyCodeInputWidget;

public class VerifyPhoneActivity extends Activity{
    private TitleBar mTitleBar;
    private VerifyCodeInputWidget mVerifyCodeInputWidget;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);

        initTitle();

        initVerifyWidget();
    }

    private void initTitle() {
        mTitleBar = (TitleBar)findViewById(R.id.title_bar);
        mTitleBar.setTitle("手机验证");
        mTitleBar.setCallback(new Callback() {
            @Override
            public void onLeftIVClicked() {
                VerifyPhoneActivity.this.finish();
            }
        });
    }

    protected void setTitle(String title){
        mTitleBar.setTitle(title);
    }

    protected void onInputFinish(){
        Intent intent = new Intent(VerifyPhoneActivity.this, RegistCarActivity.class);
        VerifyPhoneActivity.this.startActivity(intent);
    }

    private void initVerifyWidget(){
        mVerifyCodeInputWidget = (VerifyCodeInputWidget)findViewById(R.id.input_widget);
        mVerifyCodeInputWidget.setPhoneText("183 1100 5528");
        mVerifyCodeInputWidget.startCount();
        mVerifyCodeInputWidget.setCallback(new VerifyCodeInputWidget.Callback() {
            @Override
            public void onInputFinished(String text) {
                //TODO
                onInputFinish();
            }

            @Override
            public void onResend() {
                //TODO
                Log.w("haha", "on resend");
            }
        });
    }

    protected void setPhoneText(String phoneText) {
        mVerifyCodeInputWidget.setPhoneText(phoneText);
    }

    //TODO 获取验证码
    private void fetchCode(){

    }
}
