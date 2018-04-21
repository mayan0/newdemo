package com.amap.driverdemo.common.widget;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.amap.driverdemo.model.SettingItemModel;

public class SettingListWidget extends BaseRLWidget {

    public static interface Callback{
        public void onClick(int position);
    }

    private ListView mListView;
    private ListAdapter mLVAdapter;
    private Callback mCallback;

    public SettingListWidget(Context context) {
        super(context);
    }

    public SettingListWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SettingListWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.widget_setting_list_widget;
    }

    @Override
    protected void initWhenConstruct(Context context) {
        super.initWhenConstruct(context);

        mListView = (ListView)findViewById(R.id.list_view);
        mLVAdapter = new ListAdapter(mContext);
        mListView.setAdapter(mLVAdapter);
        mListView.setDividerHeight(0);
        mListView.setDivider(null);

        mListView.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mCallback != null) {
                    mCallback.onClick(position);
                }
            }
        });
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public void refreshData(List<SettingItemModel> msgModels) {
        mLVAdapter.refreshData(msgModels);
        mLVAdapter.notifyDataSetChanged();
    }


    private class ListAdapter extends BaseAdapter {

        private Context mContext;
        private List<SettingItemModel> itemModels;

        private  int TYPE_COMMON  = 0;
        private int TYPE_VOID = 1;

        public ListAdapter(Context context) {
            this.mContext = context;
        }

        public void refreshData(List<SettingItemModel> msgModels) {
            this.itemModels = msgModels;
        }

        @Override
        public int getCount() {
            if (this.itemModels == null) {
                return 0;
            }

            return this.itemModels.size();
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            int dataPos = position;
            if (itemModels.get(dataPos).isVoid) {
                return TYPE_VOID;
            }

            return TYPE_COMMON;
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

            if (getItemViewType(position) == TYPE_VOID) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.widget_setting_list_void_item, null);
                }
                convertView.setClickable(false);
                return convertView;
            }

            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.widget_setting_list_item, null);
                viewHolder.titleTV = (TextView)convertView.findViewById(R.id.title_tv);
                viewHolder.subTitleTV = (TextView)convertView.findViewById(R.id.subtitle_tv);
                viewHolder.arrowIV = (ImageView)convertView.findViewById(R.id.click_icon);
                viewHolder.dividerView = convertView.findViewById(R.id.divider);
                viewHolder.switchView = convertView.findViewById(R.id.switchBtn);
                convertView.setTag(viewHolder);
            } else {
                viewHolder
                    = (ViewHolder)convertView.getTag();
            }

            int dataPos = position;

            viewHolder.titleTV.setText(itemModels.get(dataPos).title);
            viewHolder.subTitleTV.setText(itemModels.get(dataPos).subTitle);

            if (itemModels.get(dataPos).isClickable) {
                viewHolder.arrowIV.setVisibility(View.VISIBLE);
            } else {
                viewHolder.arrowIV.setVisibility(View.GONE);
            }

            int nextPos = dataPos + 1;
            if (nextPos >= itemModels.size() || itemModels.get(nextPos).isVoid) {
                viewHolder.dividerView.setVisibility(View.GONE);
            } else {
                viewHolder.dividerView.setVisibility(View.VISIBLE);
            }

            if (itemModels.get(dataPos).isCheckBtn) {
                viewHolder.switchView.setVisibility(View.VISIBLE);
                viewHolder.arrowIV.setVisibility(View.GONE);
            } else {
                viewHolder.switchView.setVisibility(View.GONE);
            }

            return convertView;
        }

        class ViewHolder {
            public TextView titleTV;
            public TextView subTitleTV;
            public ImageView arrowIV;
            public View dividerView;
            public View switchView;
        }
    }
}
