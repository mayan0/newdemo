package com.amap.driverdemo.module.serviceprocess.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.driverdemo.R;
import com.amap.driverdemo.common.Constants;
import com.amap.driverdemo.model.OrderInfo;
import com.amap.driverdemo.model.UserInfo;

/**
 * 订单信息栏控制类
 * Package : com.amap.driverdemo.utils
 * Author : xudong.tang
 * Date   : 18/4/18
 */

public class OrderInfoPanelHelper implements View.OnClickListener {

    private Context mContext;
    private LinearLayout mOrderInfoBar;
    private FrameLayout mOrderInfoPanel;
    private FrameLayout mUserInfoPanel;
    private TextView mTv_start;
    private TextView mTv_end;
    private TextView mTv_userInfo_phone;
    private TextView mTv_userInfo_message;
    private ImageButton mBtn_userInfo_phone;
    private ImageButton mBtn_orderInfo_expand;
    private LinearLayout mVc_orderInfo_start;
    private boolean isExpand = true;
    private OrderInfo mOrderInfo;
    private OnExpandButtonClickListener mOnExpandButtonClickListener;

    public OrderInfoPanelHelper(Context context,LinearLayout orderInfoBar, OrderInfo orderInfo){
        this.mContext = context;
        this.mOrderInfoBar = orderInfoBar;
        this.mOrderInfo = orderInfo;
        initView();
    }


    /**
     * 初始化UI
     */
    private void initView() {
        if(this.mContext == null || this.mOrderInfoBar == null){
            return;
        }

        mOrderInfoPanel = (FrameLayout) mOrderInfoBar.findViewById(R.id.panel_order_info);
        mUserInfoPanel = (FrameLayout) mOrderInfoBar.findViewById(R.id.panel_user_info);

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //订单信息栏
        LinearLayout widget_orderInfo = (LinearLayout) LayoutInflater.from(this.mContext).inflate(R.layout.widget_orderinfo,null);
        mOrderInfoPanel.addView(widget_orderInfo,layoutParams);

        //乘客信息栏
        RelativeLayout widget_userInfo = (RelativeLayout) LayoutInflater.from(this.mContext).inflate(R.layout.widget_userinfo,null);
        mUserInfoPanel.addView(widget_userInfo,layoutParams);

        mVc_orderInfo_start = (LinearLayout) this.mOrderInfoPanel.findViewById(R.id.vc_orderinfo_start);
        mTv_start = (TextView) this.mOrderInfoPanel.findViewById(R.id.tv_orderinfo_start);
        mTv_start.setText(mOrderInfo.getPickUpPosition().getName());
        mTv_end = (TextView) this.mOrderInfoPanel.findViewById(R.id.tv_orderinfo_end);
        mTv_end.setText(mOrderInfo.getDestinationPosition().getName());

        mTv_userInfo_message = (TextView) mUserInfoPanel.findViewById(R.id.tv_userinfo_message);
        UserInfo userInfo = mOrderInfo.getUserInfo();
        mTv_userInfo_message.setText(String.format(Constants.USERINFO_ORDER_COMPLETE_RATIO_FORMAT, userInfo.getRideCount(),userInfo.getOrderCompleteRatio()));

        mTv_userInfo_phone = (TextView) mUserInfoPanel.findViewById(R.id.tv_userinfo_phone);
        mTv_userInfo_phone.setText(userInfo.getTelephone().replaceAll(Constants.USERINFO_PHONE_NUMBER_FORMAT_REGEX,Constants.USERINFO_PHONE_NUMBER_FORMAT_REPLACEMENT));

        mBtn_userInfo_phone = (ImageButton) mUserInfoPanel.findViewById(R.id.btn_userinfo_phone);
        mBtn_userInfo_phone.setOnClickListener(this);

        mBtn_orderInfo_expand = (ImageButton) mOrderInfoBar.findViewById(R.id.btn_orderinfo_panel_expand);
        mBtn_orderInfo_expand.setOnClickListener(this);
    }

    /**
     * 设置展开按钮单击监听
     * @param onExpandButtonClickListener
     */
    public void setOnExpandButtonClickListener(OnExpandButtonClickListener onExpandButtonClickListener){
        this.mOnExpandButtonClickListener = onExpandButtonClickListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btn_userinfo_phone:
                //todo 拨打用户电话
                Toast.makeText(mContext,"正在拨通 "+mTv_userInfo_phone.getText(),Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_orderinfo_panel_expand:
                expandPanel();
                break;
            default:
                break;
        }
    }

    /**
     * 展开、收起订单信息栏
     */
    private void expandPanel() {
        isExpand = !isExpand;
        if(isExpand){
            mUserInfoPanel.setVisibility(View.VISIBLE);
            mVc_orderInfo_start.setVisibility(View.VISIBLE);
            mBtn_orderInfo_expand.setImageResource(R.drawable.bg_userinfo_panel_takeback);
        }else {
            mUserInfoPanel.setVisibility(View.GONE);
            mVc_orderInfo_start.setVisibility(View.GONE);
            mBtn_orderInfo_expand.setImageResource(R.drawable.bg_userinfo_panel_expand);
        }

        if(this.mOnExpandButtonClickListener != null){
            mOnExpandButtonClickListener.onClick(isExpand);
        }
    }

    /**
     *
     * @return
     */
    public int getBottom(){
        if(mOrderInfoBar != null){
            return mOrderInfoBar.getBottom();
        }

        return 0;
    }

    /**
     * 展开按钮是否被点击
     */
    public interface OnExpandButtonClickListener{
        void onClick(boolean isExpand);
    }
}
