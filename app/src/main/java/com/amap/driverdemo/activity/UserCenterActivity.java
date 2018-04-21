package com.amap.driverdemo.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.driverdemo.R;
import com.amap.driverdemo.common.BaseActivity;
import com.amap.driverdemo.common.Constants;
import com.amap.driverdemo.common.widget.TitleBar;
import com.amap.driverdemo.model.DrawerItemModel;
import com.amap.driverdemo.model.OrderInfo;
import com.amap.driverdemo.model.UserInfo;
import com.amap.driverdemo.module.usercenter.widget.UserCenterLeftDrawerWidget;
import com.amap.driverdemo.module.usercenter.widget.UserCenterMainContentWidget;
import com.amap.driverdemo.module.usercenter.widget.UserCenterNewOrderDialog;
import com.amap.driverdemo.module.usercenter.widget.UserCenterNewOrderDialog.Callback;
import com.amap.sctx.SCTXConfig;

/**
 * 个人中心页面
 */
public class UserCenterActivity extends BaseActivity {
    private TitleBar mTitleBar;
    private UserCenterMainContentWidget mUserContentWidget;
    private UserCenterNewOrderDialog mUserCenterNewOrderDialog;

    private DrawerLayout mDrawerLayout;
    private UserCenterLeftDrawerWidget mUserCenterLeftDrawerWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);

        init();
    }

    private void init(){
        mTitleBar = (TitleBar)findViewById(R.id.title_bar);
        mTitleBar.setCallback(mTitleBarCallback);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mUserCenterLeftDrawerWidget = (UserCenterLeftDrawerWidget) findViewById(R.id.left_drawer);
        mUserCenterLeftDrawerWidget.setCallback(mLeftDrawerCallback);

        //init drawer
        List<DrawerItemModel> drawerItems = new ArrayList<>();
        drawerItems.add(new DrawerItemModel(R.mipmap.ic_drawer_history_order, "我的行程"));
        drawerItems.add(new DrawerItemModel(R.mipmap.ic_drawer_setting, "设置"));
        mUserCenterLeftDrawerWidget.setDrawerList(drawerItems);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mUserContentWidget = (UserCenterMainContentWidget)findViewById(R.id.user_content_widget);
        mUserContentWidget.setCallback(mUserCenterWidgetCallback);
        //TODO 设置widget的presenter
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mUserCenterNewOrderDialog != null) {
            mUserCenterNewOrderDialog.cancel();
        }

        mUserContentWidget.scrollListToTop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1111) {
                mUserCenterNewOrderDialog = new UserCenterNewOrderDialog(UserCenterActivity.this);
                mUserCenterNewOrderDialog.setCallback(mDialogCallback);
                mUserCenterNewOrderDialog.show();
            }
        }
    };

    private void showDialog(){
        handler.removeMessages(1111);
        Message msg = Message.obtain();
        msg.what=1111;
        handler.sendMessageDelayed(msg, 5 * 1000);
    }

    private TitleBar.Callback mTitleBarCallback = new TitleBar.Callback(){
        @Override
        public void onLeftIVClicked() {
            showDrawer();
        }
    };

    private UserCenterNewOrderDialog.Callback mDialogCallback = new Callback() {
        @Override
        public void onRush() {
            //TODO 跳转订单详情页
            onAcceptOrder(null);
        }

        @Override
        public void onStopListen() {
            mUserContentWidget.stopListen();
            mUserCenterNewOrderDialog.cancel();
        }

        @Override
        public void onClose() {
            if (mUserCenterNewOrderDialog != null) {
                mUserCenterNewOrderDialog.cancel();
            }
        }
    };

    private UserCenterMainContentWidget.Callback mUserCenterWidgetCallback = new UserCenterMainContentWidget.Callback
        () {
        @Override
        public void onMsgDeled(int pos) {

        }

        @Override
        public void onStartBtnClicked() {
            showDialog();
        }

        @Override
        public void onStopBtnClicked() {

        }

        @Override
        public void onConfigBtnClicked() {
            Intent intent = new Intent(UserCenterActivity.this, OrderSettingActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            UserCenterActivity.this.startActivity(intent);
        }
    };

    private void showDrawer() {
        if (mDrawerLayout == null || mUserCenterLeftDrawerWidget == null) {
            return;
        }
        mDrawerLayout.openDrawer(mUserCenterLeftDrawerWidget);
    }

    private void closeDrawer() {
        if (mDrawerLayout == null || mUserCenterLeftDrawerWidget == null) {
            return;
        }
        mDrawerLayout.closeDrawer(mUserCenterLeftDrawerWidget);
    }

    private UserCenterLeftDrawerWidget.Callback mLeftDrawerCallback = new UserCenterLeftDrawerWidget.Callback() {
        @Override
        public void onClickIcon() {
            closeDrawer();
            Intent intent = new Intent(UserCenterActivity.this, UserInfoActivity.class);
            startActivity(intent);
        }

        @Override
        public void onClickItem(int pos) {
            closeDrawer();
            Intent intent;
            switch (pos) {
                case 0:
                    intent = new Intent(UserCenterActivity.this, UserOrderListActivity.class);
                    startActivity(intent);
                    break;
                case 1:
                    intent = new Intent(UserCenterActivity.this, UserSettingActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };


    public void onAcceptOrder(View view) {
        OrderInfo orderInfo = new OrderInfo("P110111000");
        orderInfo.setOrderState(SCTXConfig.SCTX_ORDER_STATUS_PICKUPPASSENGER);
        orderInfo.setPickUpPosition(new Poi("和平西桥",new LatLng(39.968497,116.418074),""));
        orderInfo.setDestinationPosition(new Poi("朝阳大悦城",new LatLng(39.924525,116.518646),""));

        UserInfo userInfo = new UserInfo();
        userInfo.setName("王小利");
        userInfo.setTelephone("18233333333");
        userInfo.setSex(UserInfo.Gender.MALE);
        userInfo.setRideCount(110);
        userInfo.setOrderCompleteRatio(96);
        orderInfo.setUserInfo(userInfo);

        Intent intent = new Intent(this,ServiceProcessActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BUNDLE_KEY_ORDERINFO,orderInfo);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
