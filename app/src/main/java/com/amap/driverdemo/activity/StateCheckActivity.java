package com.amap.driverdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.amap.driverdemo.R;
import com.amap.driverdemo.common.widget.TitleBar;
import com.amap.driverdemo.common.widget.TitleBar.Callback;
import com.amap.driverdemo.module.statecheck.widget.StateCheckResItemWidget;

public class StateCheckActivity extends Activity {
    private TitleBar mTitleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_check);

        initTitle();
        initItems();
    }

    private void initTitle(){
        mTitleBar = (TitleBar)findViewById(R.id.title_bar);
        mTitleBar.setTitle("状态异常检测");
        mTitleBar.setCallback(new Callback() {
            @Override
            public void onLeftIVClicked() {
                StateCheckActivity.this.finish();
            }
        });
    }

    //FIXME
    private void initItems(){
        StateCheckResItemWidget item1 = (StateCheckResItemWidget)findViewById(R.id.item1);
        StateCheckResItemWidget item2 = (StateCheckResItemWidget)findViewById(R.id.item2);
        StateCheckResItemWidget item3 = (StateCheckResItemWidget)findViewById(R.id.item3);

        item1.initContnet(R.mipmap.ic_anomaly_network, "网络连接", "无异常");
        item2.initContnet(R.mipmap.ic_anomaly_location, "定位", "无异常");
        item3.initContnet(R.mipmap.ic_anomaly_listen, "听单状态", "无异常");
    }

}
