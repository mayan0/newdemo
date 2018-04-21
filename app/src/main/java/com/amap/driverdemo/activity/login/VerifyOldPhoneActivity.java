package com.amap.driverdemo.activity.login;

import android.content.Intent;
import android.os.Bundle;
import com.amap.driverdemo.R;
import com.amap.driverdemo.module.login.PassportTitleBar.Callback;

public class VerifyOldPhoneActivity extends RegistMobileActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initTitleBar() {
        mTitleBar.init(R.drawable.ic_btn_back, "验证当前手机号", "");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTitleBar.setCallback(new Callback() {
            @Override
            public void onLeftClicked() {
                VerifyOldPhoneActivity.this.finish();
            }
        });
        hideHintView();
    }

    @Override
    protected void nextCheck() {
        Intent intent =new Intent(VerifyOldPhoneActivity.this,VerifyOldPhoneVerifyPhoneActivity.class);
        startActivity(intent);
    }
}
