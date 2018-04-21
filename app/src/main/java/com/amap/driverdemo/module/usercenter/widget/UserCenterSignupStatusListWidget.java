package com.amap.driverdemo.module.usercenter.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amap.driverdemo.R;
import com.amap.driverdemo.common.widget.BaseRLWidget;
import com.amap.driverdemo.model.SignupStatusModel;

public class UserCenterSignupStatusListWidget extends BaseRLWidget {

    public static final int ITEM_WIDGET_ID_PREFIX = 1000;
    public static final int ITEM_WIDGET_TITLE_ID_PREFIX = 2000;
    public static final int ITEM_WIDGET_CIRCLE_ID_PREFIX = 3000;

    private RelativeLayout contentRL;

    private List<UserCenterSignupStatusListItemWidget> mItemWidgets = null;

    private List<CircleView> mCircles = null;

    public UserCenterSignupStatusListWidget(Context context) {
        super(context);
    }

    public UserCenterSignupStatusListWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UserCenterSignupStatusListWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.widget_user_center_signup_status_list;
    }

    @Override
    protected void initWhenConstruct(Context context) {
        super.initWhenConstruct(context);
        setPadding((int)getResources().getDimension(R.dimen.status_check_list_horizontal_padding), 0,
            (int)getResources().getDimension(R.dimen.status_check_list_horizontal_padding), 0);
        contentRL = this;
    }

    public void init(List<SignupStatusModel> items) {
        if (items == null) {
            return;
        }

        if (mItemWidgets == null) {
            mItemWidgets = new ArrayList<>();
        }

        mItemWidgets.clear();

        for (int ind = 0; ind < items.size(); ind++) {
            SignupStatusModel item = items.get(ind);

            UserCenterSignupStatusListItemWidget widget = new UserCenterSignupStatusListItemWidget(mContext);
            widget.init(item);
            widget.setId(ITEM_WIDGET_ID_PREFIX + ind);

            if (widget == null) {
                return;
            }

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

            if (mItemWidgets.size() == 0) {
                contentRL.addView(widget);
                mItemWidgets.add(widget);

                addStatusCircle(widget, item);
                continue;
            }

            UserCenterSignupStatusListItemWidget lastWidget = mItemWidgets.get(ind - 1);
            if (lastWidget == null) {
                continue;
            }

            layoutParams.addRule(RelativeLayout.BELOW, lastWidget.getId());
            layoutParams.topMargin = (int)mContext.getResources().getDimension(
                R.dimen.status_item_interval);
            contentRL.addView(widget, layoutParams);
            mItemWidgets.add(widget);

            addStatusCircle(widget, item);

            addLineBetweenCircles(item);
        }
    }

    private void addStatusCircle(UserCenterSignupStatusListItemWidget curr, SignupStatusModel currModel) {
        CircleView circleView = new CircleView(mContext);
        circleView.setChecked(currModel.isChecked);
        if (mCircles == null) {
            mCircles = new ArrayList<>();
        }

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(circleView.getSelfSize(),
            circleView.getSelfSize());
        layoutParams.addRule(ALIGN_TOP, curr.getId());
        layoutParams.topMargin = (int)curr.getTitleHeight();
        contentRL.addView(circleView, layoutParams);
        circleView.setId(ITEM_WIDGET_CIRCLE_ID_PREFIX + mCircles.size());

        mCircles.add(circleView);
    }

    private void addLineBetweenCircles(SignupStatusModel currModel) {
        if (mCircles.size() <= 1) {
            return;
        }
        CircleView lastCircle = mCircles.get(mCircles.size() - 2);
        CircleView currCircle = mCircles.get(mCircles.size() - 1);

        LineView lineView = new LineView(mContext);
        lineView.setChecked(currModel.isChecked);

        RelativeLayout.LayoutParams lineLayoutParams = new RelativeLayout.LayoutParams(lineView.getSelfWidth(),
            ViewGroup.LayoutParams.MATCH_PARENT);
        lineLayoutParams.addRule(ABOVE, currCircle.getId());
        lineLayoutParams.addRule(BELOW, lastCircle.getId());
        lineLayoutParams.leftMargin = (lastCircle.getSelfSize() - lineView.getSelfWidth()) / 2;
        lineLayoutParams.bottomMargin = -50;
        contentRL.addView(lineView, lineLayoutParams);
    }

    private class LineView extends TextView {

        public LineView(Context context) {
            super(context);
        }

        public int getSelfWidth() {
            return (int)getResources().getDimension(R.dimen.statuc_circle_line_size);
        }

        public void setChecked(boolean isChecked) {
            if (isChecked) {
                setBackgroundColor(getResources().getColor(R.color.user_status_circle_color_checked));
            } else {
                setBackgroundColor(getResources().getColor(R.color.user_status_circle_color_unchecked));
            }
        }
    }

    private class CircleView extends TextView {

        public CircleView(Context context) {
            super(context);
        }

        public int getSelfSize() {
            return (int)getResources().getDimension(R.dimen.statuc_circle_size);
        }

        public void setChecked(boolean isChecked) {
            if (isChecked) {
                setBackgroundDrawable(getResources().getDrawable(R.drawable.signup_status_circle_checked));
            } else {
                setBackgroundDrawable(getResources().getDrawable(R.drawable.signup_status_circle_unchecked));
            }
        }
    }

}
