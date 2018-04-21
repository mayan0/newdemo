package com.amap.driverdemo.module.serviceprocess.widget.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.amap.driverdemo.R;

import java.util.List;

/**
 * Package : com.amap.driverdemo.module.serviceprocess.widget.adapter
 * Author : xudong.tang
 * Date   : 18/4/18
 */
public class TitleBarPopupAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<String> mItems;

    public TitleBarPopupAdapter(Context context, List<String> items) {
        this.mContext = context;
        this.mItems = items;
        mInflater = LayoutInflater.from(mContext);

    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public String getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PopupHolder holder = null;
        if (convertView == null) {
            holder = new PopupHolder();
            convertView = mInflater.inflate(R.layout.widget_title_popup_item, null);
            holder.itemTv = (TextView) convertView
                    .findViewById(R.id.popup_tv);
            holder.itemTv.setGravity(Gravity.LEFT);
            convertView.setTag(holder);
        } else {
            holder = (PopupHolder) convertView.getTag();
        }
        String itemName = getItem(position);
        holder.itemTv.setText(itemName);
        return convertView;
    }

    private class PopupHolder {
        TextView itemTv;
    }

}