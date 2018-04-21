package com.amap.driverdemo.module.statecheck.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import com.amap.driverdemo.R;
import com.amap.driverdemo.common.widget.BaseRLWidget;

public class StateCheckResItemWidget extends BaseRLWidget {
    private ImageView typeIV;
    private TextView titleTV;
    private TextView subTitleTV;

    public StateCheckResItemWidget(Context context) {
        super(context);
    }

    public StateCheckResItemWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StateCheckResItemWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.widget_state_check_item;
    }

    @Override
    protected void initWhenConstruct(Context context) {
        super.initWhenConstruct(context);

        typeIV = (ImageView)findViewById(R.id.item_iv);
        titleTV = (TextView)findViewById(R.id.title_tv);
        subTitleTV = (TextView)findViewById(R.id.subtitle_tv);
    }

    public void initContnet(int iv, String title, String subTitle) {
        typeIV.setImageResource(iv);
        titleTV.setText(title);
        subTitleTV.setText(subTitle);
    }
}
