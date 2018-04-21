package com.amap.driverdemo.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.amap.driverdemo.activity.VerifyPhoneActivity;

public class VerifyOldPhoneVerifyPhoneActivity extends VerifyPhoneActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("更换手机号");
    }

    @Override
    protected void onInputFinish() {
        Intent intent = new Intent(VerifyOldPhoneVerifyPhoneActivity.this, UpdateMobileActivity.class);
        VerifyOldPhoneVerifyPhoneActivity.this.startActivity(intent);
    }
}
