package com.amap.driverdemo.module.usermsglist.widget;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.amap.driverdemo.R;
import com.amap.driverdemo.model.InfoMsgModel;
import com.amap.driverdemo.model.OrderModel;

public class UserMsgListWidget  extends ListView {
    public UserMsgListWidget(Context context) {
        super(context);
        init();
    }

    public UserMsgListWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UserMsgListWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private ListAdapter mAdapter;
    private void init() {
        setDivider(null);
        setDividerHeight(0);

        if (mAdapter == null) {
            mAdapter = new ListAdapter(getContext());
            setAdapter(mAdapter);
        }
    }

    public void refreshData(List<InfoMsgModel> data) {
        if (mAdapter == null) {
            return;
        }

        mAdapter.refreshData(data);
        mAdapter.notifyDataSetChanged();
    }

    public class ListAdapter extends BaseAdapter {

        private Context mContext;
        private List<InfoMsgModel> data;

        public ListAdapter(Context context) {
            this.mContext = context;
        }

        public void refreshData(List<InfoMsgModel> msgModels) {
            this.data = msgModels;
        }

        @Override
        public int getCount() {
            if (this.data == null) {
                return 0;
            }

            return this.data.size();
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
                convertView = LayoutInflater.from(mContext).inflate(R.layout.widget_msg_list_item, null);
                viewHolder.titleTV = (TextView)convertView.findViewById(R.id.title_tv);
                viewHolder.timeTV = (TextView)convertView.findViewById(R.id.time_tv);

                convertView.setTag(viewHolder);
            } else {
                viewHolder
                    = (ViewHolder)convertView
                    .getTag();
            }

            int dataPos = position;

            viewHolder.titleTV.setText(data.get(dataPos).title);
            viewHolder.timeTV.setText(data.get(dataPos).timeStr);

            return convertView;
        }

        class ViewHolder {
            public TextView titleTV;
            public TextView timeTV;
        }
    }

}
