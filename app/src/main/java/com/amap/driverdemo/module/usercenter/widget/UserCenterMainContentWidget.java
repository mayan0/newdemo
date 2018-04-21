package com.amap.driverdemo.module.usercenter.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.amap.driverdemo.R;
import com.amap.driverdemo.common.widget.BaseRLWidget;
import com.amap.driverdemo.model.MsgModel;
import com.amap.driverdemo.model.OrderModel;
import com.amap.driverdemo.model.TotalOrderInfoModel;
import com.amap.driverdemo.module.userorderlist.UserCenterOrderListWidget;

public class UserCenterMainContentWidget extends BaseRLWidget {

    private UserCenterHeadContentWidget mUserCenterHeadContentWidget;

    private UserCenterOrderListWidget mUserCenterOrderListWidget;

    private UserCenterBottomBtnWidget mUserCenterBottomBtnWidget;

    private UserCenterEmptyMsgListWidget mUserCenterEmptyMsgListWidget;

    private Callback mCallback;

    private UserCenterMsgListWidget.Callback mListCallback;

    private UserCenterBottomBtnWidget.Callback mBottomBtnCallback;

    public static interface Callback{
        public void onMsgDeled(int pos);
        public void onStartBtnClicked();
        public void onStopBtnClicked();
        public void onConfigBtnClicked();
    }

    public UserCenterMainContentWidget(Context context) {
        super(context);
    }

    public UserCenterMainContentWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UserCenterMainContentWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.widget_user_center_main_content;
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    @Override
    protected void initWhenConstruct(Context context) {
        super.initWhenConstruct(context);

        mUserCenterHeadContentWidget = (UserCenterHeadContentWidget)findViewById(R.id.head_info_area);
        mUserCenterOrderListWidget = (UserCenterOrderListWidget)findViewById(R.id.order_list);
        mUserCenterBottomBtnWidget = (UserCenterBottomBtnWidget)findViewById(R.id.bottom_btn_area);
        mUserCenterEmptyMsgListWidget = (UserCenterEmptyMsgListWidget)findViewById(R.id.empty_hint);

        initCallback();

        //FIXME
        refreshOrderInfo(getFakeData1());

        initOrderListWidget();
    }

    public void scrollListToTop(){
        mUserCenterOrderListWidget.scrollListToTop();
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

    /**
     * 设置回调
     */
    private void initCallback(){
        mListCallback = new UserCenterMsgListWidget.Callback(){

            @Override
            public void onMsgDeled(int pos) {
                //TODO
                if (mCallback != null) {
                    mCallback.onMsgDeled(pos);
                }
            }
        };

        mBottomBtnCallback = new UserCenterBottomBtnWidget.Callback(){

            @Override
            public void onStartBtnClicked() {
                //TODO
                if (mCallback != null) {
                    mCallback.onStartBtnClicked();
                }
            }

            @Override
            public void onStopBtnClicked() {
                //TODO
                if (mCallback != null) {
                    mCallback.onStopBtnClicked();
                }
            }

            @Override
            public void onConfigBtnClicked() {
                //TODO
                if (mCallback != null) {
                    mCallback.onConfigBtnClicked();
                }
            }
        };

        mUserCenterBottomBtnWidget.setCallback(mBottomBtnCallback);
    }

    //FIXME
    private TotalOrderInfoModel getFakeData1(){
        TotalOrderInfoModel orderInfoModel = new TotalOrderInfoModel();
        orderInfoModel.orderIncomeStr = "22.88";
        orderInfoModel.orderNumStr = "23";
        return orderInfoModel;
    }

    //FIXME
    private List<MsgModel> getFakeData(){
        MsgModel msgModel = new MsgModel();
        msgModel.title = "车费入账";
        msgModel.subTitle="您有12.89元车费入账，您可到账户余额查看收入。";
        msgModel.timeStamp = 112233;
        ArrayList<MsgModel> models = new ArrayList<>();
        models.add(msgModel);
        models.add(msgModel);
        models.add(msgModel);
        models.add(msgModel);
        models.add(msgModel);
        return models;
    }

    public void refreshOrderInfo(TotalOrderInfoModel orderInfoModel) {
        mUserCenterHeadContentWidget.refreshData(orderInfoModel);
    }

    public void refreshOrderList(List<OrderModel> orderModels) {
        if (orderModels == null || orderModels.size() == 0) {
            mUserCenterOrderListWidget.setVisibility(View.GONE);
            mUserCenterEmptyMsgListWidget.setVisibility(View.VISIBLE);
        } else {
            mUserCenterOrderListWidget.setVisibility(View.VISIBLE);
            mUserCenterEmptyMsgListWidget.setVisibility(View.GONE);
        }
    }

    public void stopListen(){
        mUserCenterBottomBtnWidget.stopListen();
    }
}
