package com.amap.driverdemo.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.amap.driverdemo.R;
import com.amap.driverdemo.common.BaseActivity;
import com.amap.driverdemo.common.widget.SettingListWidget;
import com.amap.driverdemo.common.widget.TitleBar;
import com.amap.driverdemo.common.widget.TitleBar.Callback;
import com.amap.driverdemo.model.SettingItemModel;

public class OrderSettingActivity extends BaseActivity {
    private TitleBar mTitleBar;
    private SettingListWidget mSettingListWidget;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_setting);

        initTitle();
        initSettingList();
    }

    private void initTitle() {
        mTitleBar = (TitleBar)findViewById(R.id.title_bar);
        mTitleBar.setTitle("订单偏好设置");
        mTitleBar.setCallback(new Callback() {
            @Override
            public void onLeftIVClicked() {
                OrderSettingActivity.this.finish();
            }
        });
    }

    private void initSettingList() {
        mSettingListWidget = (SettingListWidget)findViewById(R.id.setting_list_widget);

        List<SettingItemModel> list = new ArrayList<>();
        list.add(new SettingItemModel("只听收车单", "", true, false, true));
        list.add(new SettingItemModel("只听出车预约单", "", true, false, true));

        mSettingListWidget.refreshData(list);
    }
}
