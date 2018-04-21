package com.amap.driverdemo.activity.login;

import android.content.Intent;
import android.os.Bundle;
import com.amap.driverdemo.R;
import com.amap.driverdemo.module.login.PassportTitleBar.Callback;

public class UpdateMobileActivity extends RegistMobileActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideHintView();
    }

    @Override
    protected void initTitleBar() {
        mTitleBar.init(R.drawable.ic_btn_back, "输入新手机号", "");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTitleBar.setCallback(new Callback() {
            @Override
            public void onLeftClicked() {
                UpdateMobileActivity.this.finish();
            }
        });
    }

    @Override
    protected void nextCheck() {
        Intent intent =new Intent(UpdateMobileActivity.this,UpdateMobileVerifyPhoneActivity.class);
        startActivity(intent);
    }
}
