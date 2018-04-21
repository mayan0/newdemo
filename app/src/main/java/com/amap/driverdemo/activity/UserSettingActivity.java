package com.amap.driverdemo.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.amap.driverdemo.R;
import com.amap.driverdemo.activity.login.RegistMobileActivity;
import com.amap.driverdemo.common.BaseActivity;
import com.amap.driverdemo.common.widget.SettingListWidget;
import com.amap.driverdemo.common.widget.TitleBar;
import com.amap.driverdemo.common.widget.TitleBar.Callback;
import com.amap.driverdemo.model.SettingItemModel;

public class UserSettingActivity extends BaseActivity {
    private TitleBar mTitleBar;
    private SettingListWidget mSettingListWidget;
    private View mCancelTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);
        initTitle();

        mCancelTV = findViewById(R.id.cancel_tv);
        mCancelTV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 退出 跳转到登录页面
                Intent intent = new Intent(UserSettingActivity.this, RegistMobileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                UserSettingActivity.this.startActivity(intent);
            }
        });

        initSettingList();

    }

    private void initTitle() {
        mTitleBar = (TitleBar)findViewById(R.id.title_bar);
        mTitleBar.setTitle("设置");
        mTitleBar.setCallback(new Callback() {
            @Override
            public void onLeftIVClicked() {
                UserSettingActivity.this.finish();
            }
        });
    }

    private void initSettingList() {
        mSettingListWidget = (SettingListWidget)findViewById(R.id.setting_list_widget);
        List<SettingItemModel> list = new ArrayList<>();
        list.add(new SettingItemModel("查看个人资料", "", true, false));
        list.add(new SettingItemModel("状态异常检测", "", true, false));
        list.add(new SettingItemModel("", "", false, true));
        list.add(new SettingItemModel("意见反馈", "", true, false));
        list.add(new SettingItemModel("联系我们", "", true, false));
        list.add(new SettingItemModel("关于我们", "", true, false));

        mSettingListWidget.refreshData(list);
        mSettingListWidget.setCallback(new SettingListWidget.Callback() {
            @Override
            public void onClick(int position) {
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(UserSettingActivity.this, UserInfoActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(UserSettingActivity.this, StateCheckActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                    case 4:
                    case 5:
                        Toast.makeText(UserSettingActivity.this, "功能完善中，敬请期待!~", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });

    }
}
