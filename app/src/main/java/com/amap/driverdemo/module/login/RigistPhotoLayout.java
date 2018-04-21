package com.amap.driverdemo.module.login;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.amap.driverdemo.R;

/**
 * Created by my on 2018/4/19.
 */

public class RigistPhotoLayout extends LinearLayout implements View.OnClickListener {
    private final int TAKE_PHOTO = 1;
    private Context mContext;
    private RelativeLayout mLayout;
    private Button mClearBtn;
    private ImageView mPhotoIv;
    private int mBackgroundResId;
    private Drawable mBackgroundDrawable;
    private boolean isPhoto = false;
    private Callback mCallback;

    public static interface Callback {
        public void onTakePhoto(int tag);
    }

    public RigistPhotoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context) {
        if (context == null) {
            return;
        }
        mContext = context;

        LayoutInflater.from(context).inflate(R.layout.widget_regist_input_carinfo_photo, this);

        mLayout = (RelativeLayout)findViewById(R.id.photo_layout);
        mClearBtn = (Button) findViewById(R.id.clear_btn);
        mPhotoIv = (ImageView)findViewById(R.id.photo_iv);
        mClearBtn.setOnClickListener(this);
        mLayout.setOnClickListener(this);
        mPhotoIv.setOnClickListener(this);
        if (mLayout != null){
            mBackgroundDrawable = mLayout.getBackground();
        }

    }

    public void setBackground(int backgroundResId){
        mBackgroundResId = backgroundResId;
        if (mLayout != null){
            mLayout.setBackgroundResource(backgroundResId);
        }
    }
    public void setImage(Bitmap backgroundBitmap){
        if (mPhotoIv != null){
            mPhotoIv.setVisibility(View.VISIBLE);
            mPhotoIv.setImageBitmap(backgroundBitmap);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.clear_btn:
                clearphoto();
            case R.id.photo_layout:
                takephoto();
        }

    }

    private void takephoto() {
        mCallback.onTakePhoto((int)getTag());
    }

    private void clearphoto() {
        if (mLayout != null){
            mLayout.setBackgroundResource(mBackgroundResId);
        }
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

}
