package com.amap.driverdemo.module.serviceprocess.widget;

/**
 * Package : com.amap.driverdemo.widgets
 * Author : xudong.tang
 * Date   : 18/4/18
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.amap.driverdemo.R;
import com.amap.driverdemo.module.serviceprocess.widget.adapter.TitleBarPopupAdapter;

import java.util.ArrayList;
import java.util.List;

public class TitleBarPopupWindow extends PopupWindow {

    private Context mContext;
    private ListView mListView;
    private OnItemClickListener mOnItemClickListener;
    private List<String> mItems;
    private int mWidth;
    private int mHeight;

    // 判断是否需要添加或更新列表子类项
    private boolean isDirty = true;

    private LayoutInflater mInflater = null;
    private View mMenuView;

    private LinearLayout mPopupLayout;

    private TitleBarPopupAdapter mAdapter;

    public TitleBarPopupWindow(Context context) {
        // TODO Auto-generated constructor stub
    }

    public TitleBarPopupWindow(Context context, int width, int height,
                               OnItemClickListener onItemClickListener, List<String> items) {

        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = mInflater.inflate(R.layout.widget_title_popup, null);

        this.mContext = context;
        this.mItems = items;
        this.mOnItemClickListener = onItemClickListener;

        this.mWidth = width;
        this.mHeight = height;

        initWidget();
        setPopup();
    }

    /**
     * 初始化控件
     */
    private void initWidget() {
        mListView = (ListView) mMenuView.findViewById(R.id.popup_lv);
        mPopupLayout = (LinearLayout) mMenuView.findViewById(R.id.popup_layout);
        mListView.setOnItemClickListener(mOnItemClickListener);

        android.widget.RelativeLayout.LayoutParams lpPopup = (android.widget.RelativeLayout.LayoutParams) mPopupLayout
                .getLayoutParams();
        lpPopup.width = (int) (mWidth * 1.0 / 4);
        lpPopup.setMargins((int) (mWidth * 3.0 / 4), 0, 0, 0);
        mPopupLayout.setLayoutParams(lpPopup);
    }

    /**
     * 设置popup的样式
     */
    private void setPopup() {
        // 设置AccessoryPopup的view
        this.setContentView(mMenuView);
        // 设置AccessoryPopup弹出窗体的宽度
        this.setWidth(LayoutParams.MATCH_PARENT);
        // 设置AccessoryPopup弹出窗体的高度
        this.setHeight(LayoutParams.MATCH_PARENT);
        // 设置AccessoryPopup弹出窗体可点击
        this.setFocusable(true);
        // 设置AccessoryPopup弹出窗体的动画效果
        this.setAnimationStyle(R.style.AnimTop);

        mMenuView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int height = mPopupLayout.getBottom();
                int left = mPopupLayout.getLeft();
                int right = mPopupLayout.getRight();
                int y = (int) event.getY();
                int x = (int) event.getX();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y > height || x < left || x > right) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    /**
     * 显示弹窗界面
     *
     * @param view
     */
    public void show(View view) {
        if (isDirty) {
            isDirty = false;
            mAdapter = new TitleBarPopupAdapter(mContext, mItems);
            mListView.setAdapter(mAdapter);
        }

        showAsDropDown(view, 0, 0);
    }

}
