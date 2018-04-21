package com.amap.driverdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.amap.driverdemo.R;
import com.amap.driverdemo.activity.login.RegistMobileActivity;
import com.amap.driverdemo.common.BaseActivity;

/**
 * 广告页面
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, RegistMobileActivity.class);
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
            }
        }, 1500);
    }
}
