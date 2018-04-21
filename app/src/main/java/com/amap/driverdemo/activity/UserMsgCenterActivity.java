package com.amap.driverdemo.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.amap.driverdemo.R;
import com.amap.driverdemo.common.BaseActivity;
import com.amap.driverdemo.common.widget.TitleBar;
import com.amap.driverdemo.common.widget.TitleBar.Callback;
import com.amap.driverdemo.model.InfoMsgModel;
import com.amap.driverdemo.module.usermsglist.widget.UserMsgListWidget;

public class UserMsgCenterActivity extends BaseActivity{
    private TitleBar mTitleBar;
    private UserMsgListWidget mUserMsgListWidget;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_msg_center);

        initTitle();
        initMsgList();
    }

    private void initTitle(){
        mTitleBar = (TitleBar)findViewById(R.id.title_bar);
        mTitleBar.setTitle("消息中心");
        mTitleBar.setCallback(new Callback() {
            @Override
            public void onLeftIVClicked() {
                UserMsgCenterActivity.this.finish();
            }
        });
    }

    private void initMsgList() {
        mUserMsgListWidget = (UserMsgListWidget)findViewById(R.id.msg_lv);

        List<InfoMsgModel> data = new ArrayList<>();
        data.add(new InfoMsgModel("恭喜您 ，您的信用已升级！", "2018-04-11 12:30"));
        data.add(new InfoMsgModel("恭喜您 ，您已经完成了80单，奖励已经自动发放！", "2018-04-10 09:30"));
        data.add(new InfoMsgModel("恭喜您 ，用户已经成功付款！", "2018-04-10 23:30"));
        data.add(new InfoMsgModel("恭喜您 ，用户已经成功付款！", "2018-04-10 22:30"));
        data.add(new InfoMsgModel("恭喜您 ，用户已经成功付款！", "2018-04-10 21:30"));
        data.add(new InfoMsgModel("恭喜您 ，用户已经成功付款！", "2018-04-10 20:30"));
        data.add(new InfoMsgModel("恭喜您 ，用户已经成功付款！", "2018-04-10 19:30"));
        data.add(new InfoMsgModel("恭喜您 ，用户已经成功付款！", "2018-04-10 18:30"));
        data.add(new InfoMsgModel("恭喜您 ，用户已经成功付款！", "2018-04-10 17:30"));
        data.add(new InfoMsgModel("恭喜您 ，用户已经成功付款！", "2018-04-10 16:30"));
        data.add(new InfoMsgModel("恭喜您 ，您已经注册成功！", "2018-03-29 10:30"));


        mUserMsgListWidget.refreshData(data);

    }
}
