package com.amap.driverdemo.activity.login;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.amap.driverdemo.R;
import com.amap.driverdemo.activity.UserCenterActivity;
import com.amap.driverdemo.common.PhotoTool;
import com.amap.driverdemo.common.widget.TitleBar;
import com.amap.driverdemo.module.login.PassportTitleBar;
import com.amap.driverdemo.module.login.RigistPhotoLayout;

import java.util.Calendar;
import java.util.Date;


/**
 * Created by my on 2018/4/18.
 */

public class RegistCarActivity extends Activity implements View.OnClickListener, RigistPhotoLayout.Callback {
    private TitleBar mTitleBar;
    private EditText mDriverDate, mCarDate;
    private RigistPhotoLayout mIdentityLayout;
    private RigistPhotoLayout mDriveLicenceLayout;
    private RigistPhotoLayout mCarPictureLayout;
    private RigistPhotoLayout mVehicleLicenceLayout;
    private RigistPhotoLayout mIdentityBackLayout;
    private RigistPhotoLayout mTaxiCardLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_carinfo);
        initView();
    }

    private void initView() {
        mTitleBar = (TitleBar)findViewById(R.id.title_bar);
        mTitleBar.setTitle(this.getResources().getString(R.string.passport_add_car));
        mTitleBar.setCallback(mTitleBarCallback);

        mIdentityLayout = (RigistPhotoLayout)findViewById(R.id.regist_identitycard_layout);
        mIdentityLayout.setTag(1000 + 1);
        mIdentityLayout.setCallback(this);

        mIdentityBackLayout = (RigistPhotoLayout)findViewById(R.id.regist_identitycard_layout_back);
        mIdentityBackLayout.setTag(1000+2);
        mIdentityBackLayout.setCallback(this);

        mDriveLicenceLayout = (RigistPhotoLayout)findViewById(R.id.regist_drivelicence_layout);
        mDriveLicenceLayout.setTag(1000+3);
        mDriveLicenceLayout.setCallback(this);

        mVehicleLicenceLayout = (RigistPhotoLayout)findViewById(R.id.regist_vehiclelicence_layout);
        mVehicleLicenceLayout.setTag(1000+4);
        mVehicleLicenceLayout.setCallback(this);

        mCarPictureLayout = (RigistPhotoLayout)findViewById(R.id.regist_carpicture_layout);
        mCarPictureLayout.setTag(1000+5);
        mCarPictureLayout.setCallback(this);

        mTaxiCardLayout = (RigistPhotoLayout)findViewById(R.id.regist_taxicard_layout);
        mTaxiCardLayout.setTag(1000+6);
        mTaxiCardLayout.setCallback(this);


        mDriverDate = (EditText)findViewById(R.id.et_regist_time);
        mCarDate = (EditText)findViewById(R.id.et_regist_cartime);
        mDriverDate.setOnClickListener(this);
        mCarDate.setOnClickListener(this);
        findViewById(R.id.passport_carinfo_submit).setOnClickListener(this);
    }

    public void onTakePhoto(int tag) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, tag);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap photo = PhotoTool.getTakenCamera(data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1001) {
                //TODO first
                mIdentityLayout.setImage(photo);
            } else if (requestCode == 1002) {
                //TODO second
                mIdentityBackLayout.setImage(photo);
            } else if (requestCode == 1003) {
                //TODO second
                mDriveLicenceLayout.setImage(photo);
            } else if (requestCode == 1004) {
                //TODO second
                mVehicleLicenceLayout.setImage(photo);
            } else if (requestCode == 1005) {
                //TODO second
                mCarPictureLayout.setImage(photo);
            } else if (requestCode == 1006) {
                //TODO second
                mTaxiCardLayout.setImage(photo);
            }
        }
    }

    private TitleBar.Callback mTitleBarCallback = new TitleBar.Callback(){
        @Override
        public void onLeftIVClicked() {
            back();
        }
    };

    private void back() {
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_regist_time:
                //点击按钮显示一个日期选择框
                getDriverDate();
                break;
            case R.id.et_regist_cartime:
                getCarDate();
                break;
            case R.id.passport_carinfo_submit:
                submitClick();
        }
    }

    private void submitClick() {
        //todo 提交跳转
        Intent intent = new Intent(this, UserCenterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void getCarDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);//当前年
        int month = calendar.get(Calendar.MONTH);//当前月
        int day = calendar.get(Calendar.DAY_OF_MONTH);//当前日
        //new一个日期选择对话框的对象,并设置默认显示时间为当前的年月日时间.
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int years, int monthOfYear, int dayOfMonth) {
                int month = monthOfYear+1;
                mCarDate.setText(years+"-"+month+"-"+dayOfMonth);

            }
        }, year, month, day);
        dialog.getDatePicker().setMaxDate((new Date()).getTime());
        dialog.show();
    }

    /**
     *
     */
    private void getDriverDate() {
        //获取当前年月日
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);//当前年
        int month = calendar.get(Calendar.MONTH);//当前月
        int day = calendar.get(Calendar.DAY_OF_MONTH);//当前日
        //new一个日期选择对话框的对象,并设置默认显示时间为当前的年月日时间.
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int years, int monthOfYear, int dayOfMonth) {
                int month = monthOfYear+1;
                mDriverDate.setText(years+"-"+month+"-"+dayOfMonth);
            }
        }, year, month, day);
        dialog.getDatePicker().setMaxDate((new Date()).getTime());
        dialog.setTitle(this.getResources().getString(R.string.dialog_drivedate));
        dialog.show();
    }


}
