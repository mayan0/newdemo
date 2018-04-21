package com.amap.driverdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.driverdemo.R;
import com.amap.driverdemo.common.Constants;
import com.amap.driverdemo.model.OrderInfo;
import com.amap.driverdemo.module.serviceprocess.utils.OrderInfoPanelHelper;
import com.amap.driverdemo.module.serviceprocess.utils.OrderStateUitl;
import com.amap.driverdemo.module.serviceprocess.utils.TitleBarHelper;

/**
 * Package : com.amap.driverdemo.activity
 * Author : xudong.tang
 * Date   : 18/4/18
 * 订单详情页面
 */

public class OrderDetailActivity extends Activity implements View.OnClickListener {

    private OrderInfoPanelHelper mOrderInfoPanelHelper;
    private OrderInfo mOrderInfo;
//    private TextView mTv_price ;
    private TextView mTv_state;
    private TextView mTv_distance;
//    private Button mBtn_change_pay_type;
    private Button mBtn_stop_recive_order;
    private Button mBtn_goto_homepage;
    private float mPrice;
    private TitleBarHelper mTitleBarHelper;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        mOrderInfo = getIntent().getParcelableExtra(Constants.BUNDLE_KEY_ORDERINFO);
        mPrice = getIntent().getFloatExtra(Constants.BUNDLE_KEY_PRICE,12);
        //初始化标题栏
        initTileBar();
        initWidget();
    }

    private void initTileBar() {
        RelativeLayout titleBar = (RelativeLayout) findViewById(R.id.sp_titlebar);
        mTitleBarHelper = new TitleBarHelper(this,titleBar);
        mTitleBarHelper.getWidget(TitleBarHelper.TITLEBAR_LEFT_BUTTON).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderDetailActivity.this,"退出",Toast.LENGTH_LONG).show();
                finish();
            }
        });

        TextView btn_cancel = (TextView)mTitleBarHelper.getWidget(TitleBarHelper.TITLEBAR_RIGHT_BUTTON);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderDetailActivity.this,"更多",Toast.LENGTH_LONG).show();
            }
        });
        mTitleBarHelper.setTitle(getResources().getString(R.string.titlebar_des_order_detail));
    }

    private void initWidget() {
        LinearLayout orderInfoBar = (LinearLayout) findViewById(R.id.sp_orderinfo_bar);
        //订单信息栏
        mOrderInfoPanelHelper = new OrderInfoPanelHelper(this,orderInfoBar,mOrderInfo);

//        mTv_price = (TextView) findViewById(R.id.tv_order_price);
//        mTv_price.setText(String.format(Constants.ORDERINFO_PRICE_FORMAT,mPrice));

        mTv_state = (TextView) findViewById(R.id.tv_order_state);

        mTv_distance = (TextView) findViewById(R.id.tv_order_distance);
        int distance = getIntent().getIntExtra(Constants.BUNDLE_KEY_DISTANCE,0);
        int time = getIntent().getIntExtra(Constants.BUNDLE_KEY_TIME,0);
        mTv_distance.setText(OrderStateUitl.getOrderDetailDistanceTip(distance,time));
//        mBtn_change_pay_type = (Button) findViewById(R.id.btn_change_pay_type);
//        mBtn_change_pay_type.setOnClickListener(this);

        mBtn_stop_recive_order = (Button) findViewById(R.id.btn_stop_recive_order);
        mBtn_stop_recive_order.setOnClickListener(this);

        mBtn_goto_homepage = (Button) findViewById(R.id.btn_goto_homepage);
        mBtn_goto_homepage.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_goto_homepage:
                finish();
                break;
            default:
                Toast.makeText(this,((Button)v).getText() ,Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
