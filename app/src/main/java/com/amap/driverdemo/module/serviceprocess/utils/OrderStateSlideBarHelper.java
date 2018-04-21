package com.amap.driverdemo.module.serviceprocess.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import com.amap.driverdemo.R;
import com.amap.driverdemo.common.Constants;
import com.amap.driverdemo.module.serviceprocess.widget.SlideBar;
import com.amap.sctx.SCTXConfig;


/**
 * Package : com.amap.driverdemo.widgets
 * Author : xudong.tang
 * Date   : 18/4/17
 */

public class OrderStateSlideBarHelper {
    private Context mContext;
    private SlideBar mSlideBar;
    private int mOrderState;
    private TextView mTv_StateDes ;
    private TextView mTv_Price ;
    private Handler mHandle;
    private OnOrderStateChangeListener mOnOrderStateChangeListener;

    /**
     * 订单状态切换辅助
     * @param context
     * @param slideBar
     */
    public OrderStateSlideBarHelper(Context context,SlideBar slideBar){
        this.mContext = context;
        this.mSlideBar = slideBar;
        this.mTv_StateDes = (TextView) slideBar.findViewById(R.id.tv_orderstate);
        this.mTv_Price = (TextView) slideBar.findViewById(R.id.tv_price);
        this.mHandle = new Handler(Looper.getMainLooper());
        this.mSlideBar.setOnReleasedListener(new MyOnReleasedListener());
    }

    /**
     * 切换订单状态，修改slidebar文字描述
     * @param orderState
     */
    public void setOrderState(int orderState){
        this.mOrderState = orderState;
        invalidateState();
    }

    public void setOnOrderStateChangeListener(OnOrderStateChangeListener onOrderStateChangeListener){
        this.mOnOrderStateChangeListener = onOrderStateChangeListener;
    }

    private void invalidateState(){
        mHandle.post(new Runnable() {
            @Override
            public void run() {
                if(mTv_StateDes != null){
                    mTv_StateDes.setText(OrderStateUitl.getOrderStateDescripter(mContext,mOrderState));
                }

                if(mTv_Price!=null){
                    if(mOrderState == SCTXConfig.SCTX_ORDER_STATUS_PASSENGERONBOARD){
                        mTv_Price.setVisibility(View.VISIBLE);
                    }else {
                        mTv_Price.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
    }

    /**
     * 更新价钱
     * @param price
     */
    public void invalidatePrice(final float price){
        mHandle.post(new Runnable() {
            @Override
            public void run() {
                if(mTv_Price != null){
                    mTv_Price.setText(String.format(Constants.ORDERINFO_PRICE_FORMAT,price));
                }
            }
        });
    }

    /**
     * 状态切换
     */
    class MyOnReleasedListener implements SlideBar.OnReleasedListener{

        @Override
        public void onReleased() {
            mOrderState++;
            invalidateState();
            if(mOnOrderStateChangeListener!=null){
                mOnOrderStateChangeListener.onOrderStateChange(Math.max(0,Math.min(mOrderState, SCTXConfig.SCTX_ORDER_STATUS_ORDERCOMPLETE)));
            }
        }
    }

    /**
     * 订单状态回调
     */
    public interface OnOrderStateChangeListener {
        void onOrderStateChange(int orderState);
    }
}
