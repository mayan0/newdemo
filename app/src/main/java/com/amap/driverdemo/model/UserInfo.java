package com.amap.driverdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Package : com.amap.driverdemo.model
 * Author : xudong.tang
 * Date   : 18/4/19
 * 乘客信息
 */

public class UserInfo implements Parcelable{

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private int sex;

    /**
     * 电话号码
     */
    private String telephone;


    /**
     * 乘坐次数
     */
    private int rideCount;

    /**
     * 订单完成比率
     */
    private int orderCompleteRatio;

    public UserInfo(){}

    protected UserInfo(Parcel in) {
        name = in.readString();
        sex = in.readInt();
        telephone = in.readString();
        rideCount = in.readInt();
        orderCompleteRatio = in.readInt();
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getRideCount() {
        return rideCount;
    }

    public void setRideCount(int rideCount) {
        this.rideCount = rideCount;
    }

    public int getOrderCompleteRatio() {
        return orderCompleteRatio;
    }

    public void setOrderCompleteRatio(int orderCompleteRatio) {
        this.orderCompleteRatio = orderCompleteRatio;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(sex);
        dest.writeString(telephone);
        dest.writeInt(rideCount);
        dest.writeInt(orderCompleteRatio);
    }

    /**
     * 性别
     */
    public class Gender{
        /**
         * 男性
         */
        public static final int MALE = 0;
        /**
         * 女性
         */
        public static final int FEMALE = 1;
    }
}
