package com.amap.driverdemo.module.serviceprocess.utils;

import android.content.Context;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.model.Marker;
import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.driverdemo.model.OrderInfo;
import com.amap.sctx.DriverRouteManager;
import com.amap.sctx.OrderProperty;
import com.amap.sctx.RouteOverlayOptions;
import com.amap.sctx.SCTXConfig;

/**
 * Package : com.amap.driverdemo.module.serviceprocess.utils
 * Author : xudong.tang
 * Date   : 18/4/19
 * 司乘同显管理类
 */

public class SCTXDriverManager {

    private static DriverRouteManager mDriverRouteManager;
    private static SCTXDriverManager mSCTXDriverManager;
    private static RouteOverlayOptions mRouteOverlayOptions;

    public static SCTXDriverManager getInstance(Context context, AMap aMap){
        if(mSCTXDriverManager == null){
            synchronized (SCTXDriverManager.class){
                if(mSCTXDriverManager == null){
                    //初始化资源
                    initRouteOverlayOptions(context);
                    mDriverRouteManager = new DriverRouteManager(context,aMap,mRouteOverlayOptions);
                    mDriverRouteManager.setNaviType(AMapNavi.EmulatorNaviMode);

                    //关闭InfoWindow
                    mDriverRouteManager.setInfoWindowAdapter(new AMap.ImageInfoWindowAdapter() {
                        @Override
                        public long getInfoWindowUpdateTime() {
                            return 0;
                        }

                        @Override
                        public View getInfoWindow(Marker marker) {
                            return null;
                        }

                        @Override
                        public View getInfoContents(Marker marker) {
                            return null;
                        }
                    });

                    mSCTXDriverManager = new SCTXDriverManager();
                }
            }
        }
        return mSCTXDriverManager;
    }

    /**
     * 初始化资源
     * @param context
     */
    private static void initRouteOverlayOptions(Context context) {
        if(mRouteOverlayOptions == null){
            mRouteOverlayOptions = new RouteOverlayOptions();
        }
    }


    /**
     * 设置订单信息
     * @param orderInfo
     */
    public void setOrderInfo(OrderInfo orderInfo){
        if(mDriverRouteManager != null){
            OrderProperty orderProperty = new OrderProperty(SCTXConfig.SCTX_ORDER_TYPE_NORMAL,orderInfo.getOrderId());
            try {
                //设置起终点信息
                mDriverRouteManager.setOrderProperty(orderProperty,orderInfo.getPickUpPosition().getCoordinate(),orderInfo.getDestinationPosition().getCoordinate());
            } catch (AMapException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 设置订单状态
     */
    public void setOrderState(int orderState){
        if(mDriverRouteManager != null){
            mDriverRouteManager.setOrderState(orderState);
        }
    }

    /**
     * 设置缩放间距
     * @param left
     * @param right
     * @param top
     * @param bottom
     */
    public void setZoomSpanPadding(int left, int right, int top, int bottom){
        if(mDriverRouteManager != null){
            mDriverRouteManager.setNavigationLineMargin(left,right,top,bottom);
        }
    }

    /**
     * 强制刷新
     */
    public void zoomToSpan(){
        if(mDriverRouteManager != null){
            mDriverRouteManager.zoomToSpan();
        }
    }

    /**
     * 开始导航
     * @param context
     * @param iNaviInfoCallback
     */
    public void startNavi(Context context, INaviInfoCallback iNaviInfoCallback){
        if(mDriverRouteManager != null){
            mDriverRouteManager.startNavi(context,iNaviInfoCallback);
        }
    }


    /**
     * 设置司乘同显回调
     * @param driverRouteCallback
     */
    public void setDriverRouteCallback(DriverRouteManager.DriverRouteCallback driverRouteCallback){
        if(mDriverRouteManager != null){
            mDriverRouteManager.setDriverRouteCallback(driverRouteCallback);
        }
    }

    /**
     * 释放资源
     */
    public void destroy(){
        if(mDriverRouteManager != null){
            mDriverRouteManager.destroy();
            if(mRouteOverlayOptions != null){
                mRouteOverlayOptions.recycle();
                mRouteOverlayOptions = null;
            }
            mDriverRouteManager = null;
        }
        mSCTXDriverManager = null;
    }


}
