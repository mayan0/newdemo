package com.amap.driverdemo.module.serviceprocess.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.driverdemo.R;
import com.amap.driverdemo.module.serviceprocess.widget.TitleBarPopupWindow;

import java.util.List;

/**
 * Package : com.amap.driverdemo.utils
 * Author : xudong.tang
 * Date   : 18/4/18
 */

public class TitleBarHelper {
    public static final int TITLEBAR_LEFT_BUTTON = 0;
    public static final int TITLEBAR_RIGHT_BUTTON = 1;
    public static final int TITLEBAR_CENTER_TEXTVIEW = 2;

    private Context mContex;
    private RelativeLayout mTitleBar;
    private TitleBarPopupWindow mPopupWindow;
    private ImageButton mBtn_back;
    private TextView mTv_title;
    private TextView mBtn_more;
    private int screenW;
    private int screenH;

    public TitleBarHelper(Context context, RelativeLayout titleBar){
        this.mContex = context;
        this.mTitleBar = titleBar;

        if(this.mContex != null){
            //获取屏幕宽高
            initScreenSize();
        }

        //初始化UI
        initWidgets();
    }

    private void initWidgets() {
        if(this.mTitleBar == null){
            return;
        }

        mBtn_back = (ImageButton) this.mTitleBar.findViewById(R.id.btn_titlebar_back);

        mTv_title = (TextView) this.mTitleBar.findViewById(R.id.tv_titlebar_title);

        mBtn_more = (TextView) this.mTitleBar.findViewById(R.id.btn_titlebar_more);
    }

    /**
     * 弹窗点击事件
     */
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            mPopupWindow.dismiss();
        }
    };

    /**
     * 获取屏幕的宽和高
     */
    private void initScreenSize() {
        DisplayMetrics metrics=  mContex.getResources().getDisplayMetrics();
        screenW = metrics.widthPixels;
        screenH = metrics.heightPixels;
    }


    /**
     * 获取单击控件
     * @param type
     */
    public View getWidget(int type){
        View result = null;
        switch (type){
            case TITLEBAR_LEFT_BUTTON:
                 result= mBtn_back;
                 break;
            case TITLEBAR_RIGHT_BUTTON:
                 result = mBtn_more;
                 break;
            case TITLEBAR_CENTER_TEXTVIEW:
                 result = mTv_title;
                 break;
            default:
                break;
        }
        return result;
    }

    public void showPopupWindow(View v,List<String> items){
        mPopupWindow = new TitleBarPopupWindow(this.mContex,screenW,screenH,null,items);
        mPopupWindow.show(v);
    }

    /**
     * 设置标题
     * @param title
     */
    public void setTitle(String title){
        if(mTv_title!=null){
            mTv_title.setText(title);
        }
    }
}
