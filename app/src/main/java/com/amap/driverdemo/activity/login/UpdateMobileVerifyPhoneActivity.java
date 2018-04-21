package com.amap.driverdemo.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.amap.driverdemo.activity.UserCenterActivity;
import com.amap.driverdemo.activity.VerifyPhoneActivity;

public class UpdateMobileVerifyPhoneActivity extends VerifyPhoneActivity{
    //FIXME
    @Override
    protected void onInputFinish() {
        Intent intent = new Intent(UpdateMobileVerifyPhoneActivity.this, UserCenterActivity.class);
        UpdateMobileVerifyPhoneActivity.this.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPhoneText("139 1962 1988");
    }
}
