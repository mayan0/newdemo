package com.amap.driverdemo.activity.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.amap.driverdemo.R;
import com.amap.driverdemo.activity.VerifyPhoneActivity;
import com.amap.driverdemo.module.login.PassportTitleBar;

/**
 * Created by my on 2018/4/18.
 */

public class RegistMobileActivity extends Activity implements View.OnClickListener {
    private EditText login_phone_et;
    protected PassportTitleBar mTitleBar;
    private String phoneNum = "";
    private View contractHintView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_mobile);
        initview();
    }

    protected void initTitleBar(){
        mTitleBar.init(0, null , this.getResources().getString(R.string.app_title));
    }

    private void initview() {
        mTitleBar = (PassportTitleBar)findViewById(R.id.title_bar);
        initTitleBar();

        login_phone_et = (EditText)findViewById(R.id.passport_mobile_phone);

        findViewById(R.id.passport_mobile_next).setOnClickListener(this);

        contractHintView = findViewById(R.id.passport_container_term_argee);
    }

    protected void hideHintView(){
        contractHintView.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.passport_mobile_next:
                nextCheck();
                break;
        }
    }

    protected void nextCheck() {
        phoneNum = login_phone_et.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNum)){
        } else {
            //TODO 检查手机号是否有效，请求服务发送验证码
        }
        Intent intent =new Intent(RegistMobileActivity.this,VerifyPhoneActivity.class);
        startActivity(intent);
    }
}
