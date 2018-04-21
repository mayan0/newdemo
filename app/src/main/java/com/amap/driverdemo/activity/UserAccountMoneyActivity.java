package com.amap.driverdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.amap.driverdemo.R;
import com.amap.driverdemo.common.BaseActivity;
import com.amap.driverdemo.common.widget.TitleBar;
import com.amap.driverdemo.common.widget.TitleBar.Callback;

public class UserAccountMoneyActivity extends BaseActivity{
    private TitleBar mTitleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account_money);

        initTitle();
    }

    private void initTitle(){
        mTitleBar = (TitleBar)findViewById(R.id.title_bar);
        mTitleBar.setTitle("账户余额");
        mTitleBar.setCallback(new Callback() {
            @Override
            public void onLeftIVClicked() {
                UserAccountMoneyActivity.this.finish();
            }
        });
    }

}
