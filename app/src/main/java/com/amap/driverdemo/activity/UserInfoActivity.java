package com.amap.driverdemo.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import com.amap.driverdemo.R;
import com.amap.driverdemo.activity.login.UpdateMobileActivity;
import com.amap.driverdemo.activity.login.VerifyOldPhoneActivity;
import com.amap.driverdemo.activity.login.VerifyOldPhoneVerifyPhoneActivity;
import com.amap.driverdemo.common.BaseActivity;
import com.amap.driverdemo.common.widget.SettingListWidget;
import com.amap.driverdemo.common.widget.TitleBar;
import com.amap.driverdemo.common.widget.TitleBar.Callback;
import com.amap.driverdemo.model.SettingItemModel;

public class UserInfoActivity extends BaseActivity {
    private TitleBar mTitleBar;
    private SettingListWidget mSettingListWidget;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        initTitle();

        mSettingListWidget = (SettingListWidget)findViewById(R.id.setting_widget);
        initSettingListWidget();
    }

    private void initTitle(){
        mTitleBar = (TitleBar)findViewById(R.id.title_bar);
        mTitleBar.setTitle("查看个人资料");
        mTitleBar.setCallback(new Callback() {
            @Override
            public void onLeftIVClicked() {
                UserInfoActivity.this.finish();
            }
        });
    }

    private void initSettingListWidget(){
        //FIXME 修改为正式数据
        List<SettingItemModel> list = new ArrayList<>();
        list.add(new SettingItemModel("姓名", "梁超", false, false, false));
        list.add(new SettingItemModel("手机号", "183****5528", true, false, false));
        mSettingListWidget.refreshData(list);

        mSettingListWidget.setCallback(new SettingListWidget.Callback() {
            @Override
            public void onClick(int position) {
                Log.w("haha", "mSettingListWidget on click " + position);

                if (position == 1) {
                    //TODO 跳转修改电话 。
                    Intent intent = new Intent(UserInfoActivity.this, VerifyOldPhoneVerifyPhoneActivity.class);
                    UserInfoActivity.this.startActivity(intent);
                }
            }
        });
    }
}
