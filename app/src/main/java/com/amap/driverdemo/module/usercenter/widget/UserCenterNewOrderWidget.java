package com.amap.driverdemo.module.usercenter.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.amap.driverdemo.R;
import com.amap.driverdemo.common.widget.BaseRLWidget;
import com.amap.driverdemo.module.usercenter.widget.UserCenterNewOrderDialog.Callback;

public class UserCenterNewOrderWidget extends BaseRLWidget implements View.OnClickListener {

    /**
     * 实时or预约
     */
    private TextView mOrderTypeTV;

    /**
     * 关闭按钮
     */
    private ImageView mCloseIV;

    /**
     * 距离乘客距离
     */
    private TextView mPassengerDisTV;

    /**
     * 订单全程
     */
    private TextView mOrderDisTV;

    /**
     * 开始点
     */
    private TextView mStartTV;

    /**
     * 目的地
     */
    private TextView mDestTV;

    private Callback mCallback;

    public UserCenterNewOrderWidget(Context context) {
        super(context);
    }

    public UserCenterNewOrderWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UserCenterNewOrderWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_new_order_main_content;
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    @Override
    protected void initWhenConstruct(Context context) {
        super.initWhenConstruct(context);

        mOrderTypeTV = (TextView)findViewById(R.id.order_type_tv);
        mCloseIV = (ImageView)findViewById(R.id.close_iv);
        mPassengerDisTV = (TextView)findViewById(R.id.passenger_distance_tv);
        mOrderDisTV = (TextView)findViewById(R.id.order_distance_tv);
        mStartTV = (TextView)findViewById(R.id.start_tv);
        mDestTV = (TextView)findViewById(R.id.dest_tv);

        mCloseIV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.close_iv:
                if (mCallback != null) {
                    mCallback.onClose();
                }
                break;
            default:
                break;
        }
    }
}
