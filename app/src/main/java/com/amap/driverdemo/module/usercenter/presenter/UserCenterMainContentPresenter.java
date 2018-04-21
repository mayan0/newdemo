package com.amap.driverdemo.module.usercenter.presenter;

import java.util.List;

import com.amap.driverdemo.model.MsgModel;
import com.amap.driverdemo.model.TotalOrderInfoModel;
import com.amap.driverdemo.module.usercenter.widget.UserCenterMainContentWidget;

public class UserCenterMainContentPresenter {

    public UserCenterMainContentPresenter() {
        ;
    }

    public void refreshData(TotalOrderInfoModel orderInfoModel, List<MsgModel> msgModels) {
        //TODO
    }


    private UserCenterMainContentWidget.Callback mCallback = new UserCenterMainContentWidget.Callback(){
        @Override
        public void onMsgDeled(int pos) {
            //TODO
        }

        @Override
        public void onStartBtnClicked() {
            //TODO
        }

        @Override
        public void onStopBtnClicked() {
            //TODO
        }

        @Override
        public void onConfigBtnClicked() {
            //TODO
        }
    };

}
