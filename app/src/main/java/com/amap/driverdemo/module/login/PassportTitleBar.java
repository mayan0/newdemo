package com.amap.driverdemo.module.login;

import android.content.Context;

import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.driverdemo.R;

/**
 * Created by my on 2018/4/18.
 */

public class PassportTitleBar extends RelativeLayout implements View.OnClickListener{
    private Context mContext;
    private ImageView mLeftIV;
    private TextView mTitleTV, mLeftTV;
    private Callback mCallback;

    public static interface Callback {
        public void onLeftClicked();
    }

    public PassportTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        if (context == null) {
            return;
        }

        LayoutInflater.from(context).inflate(R.layout.widget_regist_title_bar, this);

        mContext = context;

        mLeftIV = (ImageView)findViewById(R.id.leftIV);
        mTitleTV = (TextView)findViewById(R.id.titleTV);
        mLeftTV = (TextView)findViewById(R.id.leftTV);

        setBackgroundColor(mContext.getResources().getColor(R.color.passport_title_bar_bg_color));
    }

    public void setLeftTitle(String leftTitle) {
        if (!TextUtils.isEmpty(leftTitle)){
            mLeftTV.setText(leftTitle);
            mLeftIV.setVisibility(VISIBLE);
        }
    }

    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)){
            mTitleTV.setText(title);
        }
    }

    public void init(int leftResId, String title, String lefttitle) {
        if (mLeftTV == null|| mTitleTV == null) {
            return;
        }

        setTitle(title);

        setLeftTitle(lefttitle);

        if (mLeftIV == null || leftResId == 0) {
            return;
        }

        mLeftIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallback == null) {
                    return;
                }

                mCallback.onLeftClicked();
            }
        });

        mLeftIV.setImageResource(leftResId);
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onClick(View v) {
        if (v == null || mCallback == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.leftIV:
                mCallback.onLeftClicked();
                return;
            default:
                return;
        }
    }
}
