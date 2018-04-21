package com.amap.driverdemo.module.usercenter.widget;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
import com.amap.driverdemo.R;
import com.amap.driverdemo.common.widget.BaseRLWidget;
import com.amap.driverdemo.model.MsgModel;

public class UserCenterMsgListWidget extends BaseRLWidget {
    private ListView mMsgLV;
    private UserContentMsgListAdapter mListAdapter;

    public static interface Callback{
        public void onMsgDeled(int pos);
    }

    public UserCenterMsgListWidget(Context context) {
        super(context);
    }

    public UserCenterMsgListWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UserCenterMsgListWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.widget_user_center_msg_list_widget;
    }

    @Override
    protected void initWhenConstruct(Context context) {
        super.initWhenConstruct(context);

        mListAdapter = new UserContentMsgListAdapter(context);
        mMsgLV = (ListView)findViewById(R.id.msg_lv);
        mMsgLV.setAdapter(mListAdapter);
        mMsgLV.setDividerHeight(0);
        mMsgLV.setDivider(null);
    }

    public void setCallback(Callback callback) {
        mListAdapter.setCallback(callback);
    }

    public void refreshData(List<MsgModel> msgModels) {
        if (msgModels != null && msgModels.size() > 0) {
            mListAdapter.refreshData(msgModels);
        }

        mListAdapter.notifyDataSetChanged();
    }
}
