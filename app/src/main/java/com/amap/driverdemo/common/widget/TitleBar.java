package com.amap.driverdemo.common.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amap.driverdemo.R;

public class TitleBar extends BaseRLWidget implements View.OnClickListener {
    private Context mContext;
    private ImageView mLeftIV;
    private TextView mTitleTV;
    private Callback mCallback;

    public static interface Callback {
        public void onLeftIVClicked();
    }

    public TitleBar(Context context) {
        super(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.widget_title_bar;
    }

    @Override
    protected void initWhenConstruct(Context context) {
        super.initWhenConstruct(context);
        mContext = context;

        mLeftIV = (ImageView)findViewById(R.id.leftIV);
        mTitleTV = (TextView)findViewById(R.id.titleTV);
        setBackgroundColor(mContext.getResources().getColor(R.color.title_bar_bg_color));

        mLeftIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallback == null) {
                    return;
                }

                mCallback.onLeftIVClicked();
            }
        });
    }

    public void setTitle(String title) {
        if (TextUtils.isEmpty(title) || mTitleTV == null) {
            return;
        }

        mTitleTV.setText(title);
    }

    protected void setLeftIcon(int leftResId) {
        if (mLeftIV == null) {
            return;
        }

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
                mCallback.onLeftIVClicked();
                return;
            default:
                return;
        }
    }
}
