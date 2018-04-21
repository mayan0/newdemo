package com.amap.driverdemo.module.usercenter.widget;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.amap.driverdemo.R;
import com.amap.driverdemo.model.MsgModel;
import com.amap.driverdemo.module.usercenter.widget.UserCenterMsgListWidget.Callback;

public class UserContentMsgListAdapter extends BaseAdapter implements View.OnClickListener{

    private Context mContext;
    private List<MsgModel> msgModels;
    private Callback mCallback;

    public UserContentMsgListAdapter(Context context) {
        this.mContext = context;
    }

    public void refreshData(List<MsgModel> msgModels) {
        this.msgModels = msgModels;
    }

    @Override
    public int getCount() {
        if (this.msgModels == null) {
            return 0;
        }

        return this.msgModels.size();
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.widget_user_center_msg_list_item, null);
            viewHolder.titleTV = (TextView)convertView.findViewById(R.id.title_tv);
            viewHolder.timeTV = (TextView)convertView.findViewById(R.id.time_tv);
            viewHolder.subTitleTV = (TextView)convertView.findViewById(R.id.subtitle_tv);
            viewHolder.closeIV = (ImageView)convertView.findViewById(R.id.close_iv);
            viewHolder.closeIV.setOnClickListener(this);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        int dataPos = position;

        viewHolder.titleTV.setText(msgModels.get(dataPos).title);
        viewHolder.subTitleTV.setText(msgModels.get(dataPos).subTitle);
        viewHolder.timeTV.setText(msgModels.get(dataPos).getTimeStr());

        viewHolder.closeIV.setTag(dataPos);

        return convertView;
    }



    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onClick(View v) {
        if (mCallback == null) {
            return;
        }

        try {
            mCallback.onMsgDeled((int)v.getTag());
        } catch (Throwable throwable) {
            ; //in case
        }
    }

    static class ViewHolder {
        public TextView titleTV;
        public TextView timeTV;
        public TextView subTitleTV;
        public ImageView closeIV;
    }
}
