package com.amap.driverdemo.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.amap.driverdemo.R;
import com.amap.driverdemo.common.BaseActivity;
import com.amap.driverdemo.common.widget.TitleBar;
import com.amap.driverdemo.common.widget.TitleBar.Callback;
import com.amap.driverdemo.model.OrderModel;
import com.amap.driverdemo.module.userorderlist.UserCenterOrderListWidget;

public class UserOrderListActivity extends BaseActivity{
    private TitleBar mTitleBar;
    private UserCenterOrderListWidget mUserCenterOrderListWidget;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order_list);

        initTitle();

        initOrderListWidget();
    }

    private void initTitle(){
        mTitleBar = (TitleBar)findViewById(R.id.title_bar);
        mTitleBar.setTitle("行程订单");
        mTitleBar.setCallback(new Callback() {
            @Override
            public void onLeftIVClicked() {
                UserOrderListActivity.this.finish();
            }
        });
    }

    private void initOrderListWidget(){
        mUserCenterOrderListWidget = (UserCenterOrderListWidget)findViewById(R.id.order_list);
        List<OrderModel> list = new ArrayList<>();
        list.add(new OrderModel("04月11日 16:20", "实时", "已完成", "首开广场（东四门）附近", "五道口地铁站"));
        list.add(new OrderModel("04月10日 16:20", "实时", "已完成", "首开广场（东四门）附近", "五道口地铁站"));
        list.add(new OrderModel("04月10日 12:10", "预约", "已完成", "西单（大悦城）", "王府井"));
        list.add(new OrderModel("04月09日 05:20", "预约", "已完成", "北京航空航天大学（东门)", "海淀口腔医院"));
        list.add(new OrderModel("04月08日 21:20", "实时", "已完成", "奥体公园", "长阳奥特莱斯"));
        list.add(new OrderModel("04月08日 05:20", "实时", "已完成", "海淀博远小区", "北京西站南广场"));

        mUserCenterOrderListWidget.refreshData(list);
    }
}
