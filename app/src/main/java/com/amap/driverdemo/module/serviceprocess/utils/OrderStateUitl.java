package com.amap.driverdemo.module.serviceprocess.utils;

import android.content.Context;

import com.amap.driverdemo.R;
import com.amap.driverdemo.common.Constants;
import com.amap.sctx.SCTXConfig;

/**
 * Package : com.amap.driverdemo.utils
 * Author : xudong.tang
 * Date   : 18/4/17
 */

public class OrderStateUitl {
    /**
     * 获取订单描述
     * @param orderState
     * @return
     */
    public static String getOrderStateDescripter(Context context,int orderState){
        String result = "";
        if(context == null){
            return result;
        }
        switch (orderState){
            case SCTXConfig.SCTX_ORDER_STATUS_PICKUPPASSENGER:
                result = context.getResources().getString(R.string.order_state_des_pick);
                break;
            case SCTXConfig.SCTX_ORDER_STATUS_WAITPASSENGER:
                result = context.getResources().getString(R.string.order_state_des_wait);
                break;
            case SCTXConfig.SCTX_ORDER_STATUS_PASSENGERONBOARD:
                result = context.getResources().getString(R.string.order_state_des_onboard);
                break;
            default:
                break;
        }
        return result;
    }


    /**
     * 获取Title
     * @param orderState
     * @return
     */
    public static String getTitleBarDescripter(Context context,int orderState){
        String result = "";
        if(context == null){
            return result;
        }
        switch (orderState){
            case SCTXConfig.SCTX_ORDER_STATUS_PICKUPPASSENGER:
                result = context.getResources().getString(R.string.titlebar_des_pick);
                break;
            case SCTXConfig.SCTX_ORDER_STATUS_WAITPASSENGER:
                result = context.getResources().getString(R.string.titlebar_des_wait);
                break;
            case SCTXConfig.SCTX_ORDER_STATUS_PASSENGERONBOARD:
                result = context.getResources().getString(R.string.titlebar_des_onboard);
                break;
            default:
                break;
        }
        return result;
    }

    public static String getRemainDistanceTip(float mRemainDistance, int mRemainTime) {
        return String.format(Constants.SERVICE_TIP_PICKINGUP_FORMAT,mRemainDistance/1000.0,getFormatTime(mRemainTime,true));
    }

    public static String getTravelDistancTip(int mDistance, int time) {
        return String.format(Constants.SERVICE_TIP_INSERVICE_FORMAT,mDistance/1000.0,getFormatTime(time,true));
    }

    public static String getOrderDetailDistanceTip(int distance, int time) {
        return String.format(Constants.ORDERINFO_DISTANCE_AND_TIME_FORMAT,distance/1000.0,getFormatTime(time,true));
    }

    /**
     * 获取格式化的时间
     * @param second
     * @param isReturnSecond
     * @return
     */
    private static String getFormatTime(int second,boolean isReturnSecond){
        int days = second / 86400;
        second = second % 86400;
        int hours = second / 3600;
        second = second % 3600;
        int minutes = second /60;
        second = second % 60;

        StringBuilder stringBuilder = new StringBuilder();

        if(days > 0){
            stringBuilder.append(days).append("天");
        }

        if(hours > 0){
            stringBuilder.append(hours).append("时");
        }

        if(minutes > 0){
            stringBuilder.append(minutes).append("分");
        }

        if(isReturnSecond){
            stringBuilder.append(second).append("秒");
        }

        return stringBuilder.toString();
    }



}
