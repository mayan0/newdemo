package com.amap.driverdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.TextureMapView;
import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviPath;
import com.amap.driverdemo.R;
import com.amap.driverdemo.common.Constants;
import com.amap.driverdemo.model.OrderInfo;
import com.amap.driverdemo.module.serviceprocess.utils.OrderInfoPanelHelper;
import com.amap.driverdemo.module.serviceprocess.utils.OrderStateSlideBarHelper;
import com.amap.driverdemo.module.serviceprocess.utils.OrderStateUitl;
import com.amap.driverdemo.module.serviceprocess.utils.SCTXDriverManager;
import com.amap.driverdemo.module.serviceprocess.utils.TitleBarHelper;
import com.amap.driverdemo.module.serviceprocess.widget.SlideBar;
import com.amap.sctx.DriverRouteManager;
import com.amap.sctx.SCTXConfig;
import com.amap.sctx.WayPointInfo;

/**
 * Package : com.amap.driverdemo.activity
 * Author : xudong.tang
 * Date   : 18/4/17
 * 订单流转页面
 */

public class ServiceProcessActivity extends Activity implements View.OnClickListener {
    private TextureMapView mMapView;
    private AMap mMap;
    private int mOrderState;
    private OrderStateSlideBarHelper mOrderStateSliderBarHelper;
    private OrderInfoPanelHelper mOrderInfoPanelHelper;
    private TitleBarHelper mTitleBarHelper;
    private LinearLayout mOrderInfoBar;
    private ImageButton mBtn_navigation;
    private OrderInfo mOrderInfo;
    private TextView mTv_tip;
    private SCTXDriverManager mSCTXDriverManager;
    private Handler mMainHandler;
    private float mPrice = 12;
    private long mBeginWaitingTime = 0;
    private int mDistance = 0;
    private int mTotalDistance = 0;
    private long mBeginTravelTime = 0;
    private float mRemainDistance = 0;
    private int mRemainTime = 0;
    private boolean isNeedRecordTotalDistance;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_process);

        //获取订单信息
        mOrderInfo = getIntent().getParcelableExtra(Constants.BUNDLE_KEY_ORDERINFO);

        //初始化标题栏
        initTileBar();
        //初始化地图
        initMap(savedInstanceState);
        //初始化UI
        initWidgets();

        mMainHandler = new Handler(getMainLooper());
    }

    private void initTileBar() {
        RelativeLayout titleBar = (RelativeLayout) findViewById(R.id.sp_titlebar);
        mTitleBarHelper = new TitleBarHelper(this, titleBar);
        TextView btn_cancel = (TextView) mTitleBarHelper.getWidget(TitleBarHelper.TITLEBAR_RIGHT_BUTTON);
        btn_cancel.setText("取消订单");
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ServiceProcessActivity.this, "取消订单", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initWidgets() {
        //状态切换slidebar
        SlideBar slideBar = (SlideBar) findViewById(R.id.sp_orderstate_change_bar);
        mOrderStateSliderBarHelper = new OrderStateSlideBarHelper(this, slideBar);
        mOrderStateSliderBarHelper.setOrderState(mOrderInfo.getOrderState());
        mOrderStateSliderBarHelper.setOnOrderStateChangeListener(new MyOnOrderStateChangeListener());

        mOrderInfoBar = (LinearLayout) findViewById(R.id.sp_orderinfo_bar);
        //订单信息栏
        mOrderInfoPanelHelper = new OrderInfoPanelHelper(this, mOrderInfoBar, mOrderInfo);
        mOrderInfoPanelHelper.setOnExpandButtonClickListener(new MyOnExpandButtonClickListener());

        //导航按钮
        mBtn_navigation = (ImageButton) findViewById(R.id.sp_btn_navigation);
        mBtn_navigation.setOnClickListener(this);

        //初始化司乘同显
        mSCTXDriverManager = SCTXDriverManager.getInstance(this, this.mMap);
        mSCTXDriverManager.setOrderInfo(mOrderInfo);
        mSCTXDriverManager.setOrderState(mOrderInfo.getOrderState());
        this.mOrderState = mOrderInfo.getOrderState();
        mSCTXDriverManager.setDriverRouteCallback(new MyDriverRouteCallback());

        mTv_tip = (TextView) findViewById(R.id.tv_service_tip);
    }

    private void initMap(Bundle savedInstanceState) {
        mMapView = (TextureMapView) findViewById(R.id.mapview);
        if (mMapView != null) {
            mMapView.onCreate(savedInstanceState);
            mMap = mMapView.getMap();
            mMap.getUiSettings().setZoomControlsEnabled(false);
            mMap.getUiSettings().setRotateGesturesEnabled(false);
            mMap.getUiSettings().setTiltGesturesEnabled(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
        updateZoomSpanPadding();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        if (mSCTXDriverManager != null) {
            this.mSCTXDriverManager.destroy();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sp_btn_navigation:
                if (mSCTXDriverManager != null) {
                    mSCTXDriverManager.startNavi(this, new NaviInfoCallback());
                }
                break;
            default:
                break;

        }
    }

    private void updateZoomSpanPadding() {

        mMainHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSCTXDriverManager != null) {
                    int top = mOrderInfoBar.getBottom() + 200;
                    mSCTXDriverManager.setZoomSpanPadding(100, 100, top, 200);
                    mSCTXDriverManager.zoomToSpan();
                }
            }
        }, 300);

    }

    class MyOnOrderStateChangeListener implements OrderStateSlideBarHelper.OnOrderStateChangeListener {

        @Override
        public void onOrderStateChange(int orderState) {
            mOrderState = orderState;
            if (mSCTXDriverManager != null) {
                mSCTXDriverManager.setOrderState(orderState);

                if (mTitleBarHelper != null) {
                    mTitleBarHelper.setTitle(OrderStateUitl.getTitleBarDescripter(ServiceProcessActivity.this, orderState));
                }

                updateZoomSpanPadding();

                if (orderState == SCTXConfig.SCTX_ORDER_STATUS_ORDERCOMPLETE) {
                    mSCTXDriverManager.destroy();
                    Intent intent = new Intent(ServiceProcessActivity.this, OrderDetailActivity.class);
                    intent.putExtra(Constants.BUNDLE_KEY_ORDERINFO, mOrderInfo);
                    intent.putExtra(Constants.BUNDLE_KEY_PRICE, mPrice);
                    intent.putExtra(Constants.BUNDLE_KEY_DISTANCE, mDistance);
                    intent.putExtra(Constants.BUNDLE_KEY_TIME, (int)((System.currentTimeMillis() - mBeginTravelTime) / 1000));
                    startActivity(intent);
                    finish();
                } else if (orderState == SCTXConfig.SCTX_ORDER_STATUS_WAITPASSENGER) {
                    mBeginWaitingTime = System.currentTimeMillis();
                    mBtn_navigation.setVisibility(View.INVISIBLE);
                    mTv_tip.setText(Constants.SERVICE_TIP_WAITING_FORMAT);
                } else if (orderState == SCTXConfig.SCTX_ORDER_STATUS_PASSENGERONBOARD) {
                    isNeedRecordTotalDistance = true;
                    mBeginTravelTime = System.currentTimeMillis();
                    mBtn_navigation.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    /**
     * 更新提示信息
     */
    private void updateTip() {
        if (mOrderState == SCTXConfig.SCTX_ORDER_STATUS_PICKUPPASSENGER) {
            mTv_tip.setText(OrderStateUitl.getRemainDistanceTip(mRemainDistance, mRemainTime));
        } else if (mOrderState == SCTXConfig.SCTX_ORDER_STATUS_PASSENGERONBOARD) {
            mTv_tip.setText(OrderStateUitl.getTravelDistancTip(mDistance, (int) ((System.currentTimeMillis() - mBeginTravelTime) / 1000)));
        }
    }


    class MyOnExpandButtonClickListener implements OrderInfoPanelHelper.OnExpandButtonClickListener {

        @Override
        public void onClick(boolean isExpand) {
            updateZoomSpanPadding();
        }
    }


    class MyDriverRouteCallback implements DriverRouteManager.DriverRouteCallback {

        @Override
        public void onRouteStatusChange(float v, long l, float v1, long l1) {
            if (mOrderStateSliderBarHelper != null) {
                mPrice += 0.2;
                mOrderStateSliderBarHelper.invalidatePrice(mPrice);
                mRemainDistance = v1;
                mDistance = (int) (mTotalDistance - mRemainDistance);
                mRemainTime = (int) l1;
                updateTip();
            }

            if (isNeedRecordTotalDistance) {
                AMapNaviPath path = AMapNavi.getInstance(ServiceProcessActivity.this).getNaviPath();
                mTotalDistance = path.getAllLength();
                isNeedRecordTotalDistance = false;
            }
        }

        @Override
        public void onArriveWayPoint(WayPointInfo wayPointInfo) {

        }

        @Override
        public void onArrivePickUpPosition() {

        }

        @Override
        public void onArriveDestination() {

        }

        @Override
        public void onCalculateRouteFailure() {

        }

        @Override
        public void onError(int i, String s) {

        }
    }

    class NaviInfoCallback implements INaviInfoCallback {

        @Override
        public void onInitNaviFailure() {

        }

        @Override
        public void onGetNavigationText(String s) {

        }

        @Override
        public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

        }

        @Override
        public void onArriveDestination(boolean b) {

        }

        @Override
        public void onStartNavi(int i) {

        }

        @Override
        public void onCalculateRouteSuccess(int[] ints) {
            mPrice = 12;
            updateZoomSpanPadding();
        }

        @Override
        public void onCalculateRouteFailure(int i) {

        }

        @Override
        public void onStopSpeaking() {

        }

        @Override
        public void onReCalculateRoute(int i) {
            updateZoomSpanPadding();
        }

        @Override
        public void onExitPage(int i) {

        }

        @Override
        public void onStrategyChanged(int i) {

        }

        @Override
        public View getCustomNaviBottomView() {
            return null;
        }

        @Override
        public void onArrivedWayPoint(int i) {

        }
    }

}
