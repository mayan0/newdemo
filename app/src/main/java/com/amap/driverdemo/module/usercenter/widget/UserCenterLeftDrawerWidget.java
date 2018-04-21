package com.amap.driverdemo.module.usercenter.widget;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.amap.driverdemo.R;
import com.amap.driverdemo.common.widget.BaseRLWidget;
import com.amap.driverdemo.model.DrawerItemModel;

public class UserCenterLeftDrawerWidget extends BaseRLWidget implements View.OnClickListener{

    public static interface Callback{
        public void onClickIcon();

        public void onClickItem(int pos);
    }

    private ListView mItemLV;
    private ListAdapter mListAdapter;
    private View mUserInfoRL;
    private Callback mCallback;

    public UserCenterLeftDrawerWidget(Context context) {
        super(context);
    }

    public UserCenterLeftDrawerWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UserCenterLeftDrawerWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.widget_user_center_left_drawer;
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    @Override
    protected void initWhenConstruct(Context context) {
        super.initWhenConstruct(context);
        mItemLV = (ListView)findViewById(R.id.item_list);
        mItemLV.setDivider(null);
        mItemLV.setDividerHeight(0);

        mUserInfoRL = findViewById(R.id.user_info);
        mUserInfoRL.setOnClickListener(this);

        mItemLV.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mCallback == null) {
                    return;
                }
                mCallback.onClickItem(position);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_info:
                if (mCallback != null) {
                    mCallback.onClickIcon();
                }
                break;
            default:
                break;
        }
    }

    public void setDrawerList(List<DrawerItemModel> items) {
        if (items == null || items.size() == 0) {
            return;
        }

        if (mListAdapter == null) {
            mListAdapter = new ListAdapter(mContext);
            mItemLV.setAdapter(mListAdapter);
        }

        mListAdapter.refreshData(items);
        mListAdapter.notifyDataSetChanged();
    }


    private class ListAdapter extends BaseAdapter{
        private Context mContext;
        private List<DrawerItemModel> drawerItems;

        public ListAdapter(Context context) {
            this.mContext = context;
        }

        public void refreshData(List<DrawerItemModel> msgModels) {
            this.drawerItems = msgModels;
        }

        @Override
        public int getCount() {
            if (this.drawerItems == null) {
                return 0;
            }

            return this.drawerItems.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.widget_drawer_list_item, null);
                viewHolder.titleTV = (TextView)convertView.findViewById(R.id.title_tv);
                viewHolder.titleIcon = (ImageView)convertView.findViewById(R.id.title_iv);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder)convertView.getTag();
            }

            int dataPos = position;

            viewHolder.titleTV.setText(drawerItems.get(dataPos).title);
            viewHolder.titleIcon.setImageResource(drawerItems.get(dataPos).icon);

            return convertView;
        }

        class ViewHolder {
            public ImageView titleIcon;
            public TextView titleTV;
        }
    }
}
