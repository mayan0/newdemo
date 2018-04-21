package com.amap.driverdemo.common.widget;

import java.util.concurrent.atomic.AtomicBoolean;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.amap.driverdemo.R;

public class SwitchBtn extends ImageView {

    public SwitchBtn(Context context) {
        super(context);
        init();
    }

    public SwitchBtn(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwitchBtn(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private AtomicBoolean mIsOpen;

    private void init(){
        setImageResource(R.mipmap.ic_setting_nav_close);
        mIsOpen = new AtomicBoolean(false);

        setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (mIsOpen.get()) {
                    close();
                } else {
                    open();
                }
            }
        });
    }

    public void close(){
        setImageResource(R.mipmap.ic_setting_nav_close);
        mIsOpen.set(false);
    }

    public void open(){
        setImageResource(R.mipmap.ic_setting_nav_open);
        mIsOpen.set(true);
    }

    public boolean isOpen(){
        if (mIsOpen == null) {
            return false;
        }

        return true;
    }
}
