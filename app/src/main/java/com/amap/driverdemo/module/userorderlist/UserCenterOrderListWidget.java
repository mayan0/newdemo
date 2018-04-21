package com.amap.driverdemo.module.userorderlist;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.amap.driverdemo.R;
import com.amap.driverdemo.model.MsgModel;
import com.amap.driverdemo.model.OrderModel;
import com.amap.driverdemo.module.usercenter.widget.UserCenterMsgListWidget.Callback;

public class UserCenterOrderListWidget extends ListView {
    public UserCenterOrderListWidget(Context context) {
        super(context);
        init();
    }

    public UserCenterOrderListWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UserCenterOrderListWidget(Context context, AttributeSet attrs, int defStyleAttr) {
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

    public void refreshData(List<OrderModel> data) {
        if (mAdapter == null) {
            return;
        }

        mAdapter.refreshData(data);
        mAdapter.notifyDataSetChanged();
    }

    public class ListAdapter extends BaseAdapter {

        private Context mContext;
        private List<OrderModel> orderModels;

        public ListAdapter(Context context) {
            this.mContext = context;
        }

        public void refreshData(List<OrderModel> msgModels) {
            this.orderModels = msgModels;
        }

        @Override
        public int getCount() {
            if (this.orderModels == null) {
                return 0;
            }

            return this.orderModels.size();
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
                convertView = LayoutInflater.from(mContext).inflate(R.layout.widget_order_list_item, null);
                viewHolder.timeTV = (TextView)convertView.findViewById(R.id.time_tv);
                viewHolder.typeTV = (TextView)convertView.findViewById(R.id.type_tv);
                viewHolder.statusTV = (TextView)convertView.findViewById(R.id.status_tv);
                viewHolder.startPosTV = (TextView)convertView.findViewById(R.id.start_tv);
                viewHolder.destPosTV = (TextView)convertView.findViewById(R.id.dest_tv);

                convertView.setTag(viewHolder);
            } else {
                viewHolder
                    = (ViewHolder)convertView
                    .getTag();
            }

            int dataPos = position;

            viewHolder.timeTV.setText(orderModels.get(dataPos).timeStr);
            viewHolder.typeTV.setText(orderModels.get(dataPos).type);
            viewHolder.statusTV.setText(orderModels.get(dataPos).status);
            viewHolder.startPosTV.setText(orderModels.get(dataPos).startPos);
            viewHolder.destPosTV.setText(orderModels.get(dataPos).destPos);

            return convertView;
        }

        class ViewHolder {
            public TextView timeTV;
            public TextView typeTV;
            public TextView statusTV;
            public TextView startPosTV;
            public TextView destPosTV;
        }
    }

    public void scrollListToTop() {
        if (mAdapter == null) {
            return;
        }
        smoothScrollToPosition(0);
    }
}
