package com.amap.driverdemo.module.usercenter.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import com.amap.driverdemo.R;
import com.amap.driverdemo.common.widget.BaseRLWidget;
import com.amap.driverdemo.model.TotalOrderInfoModel;

public class UserCenterHeadContentWidget extends BaseRLWidget{
    private TextView orderIncomeTV;
    private TextView orderNumTV;

    public UserCenterHeadContentWidget(Context context) {
        super(context);
    }

    public UserCenterHeadContentWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UserCenterHeadContentWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.widget_user_center_msg_list_header_widget;
    }

    @Override
    protected void initWhenConstruct(Context context) {
        super.initWhenConstruct(context);

        orderIncomeTV = (TextView)findViewById(R.id.income_tv);
        orderNumTV = (TextView)findViewById(R.id.order_num_tv);
    }

    public void refreshData(TotalOrderInfoModel orderInfoModel) {
        if (orderInfoModel == null || TextUtils.isEmpty(orderInfoModel.orderIncomeStr) || TextUtils.isEmpty(
            orderInfoModel.orderNumStr)) {
            return;
        }

        orderIncomeTV.setText(orderInfoModel.orderIncomeStr);
        orderNumTV.setText(orderInfoModel.orderNumStr);
    }
}
