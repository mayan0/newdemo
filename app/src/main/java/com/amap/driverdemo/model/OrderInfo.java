package com.amap.driverdemo.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.amap.api.maps.model.Poi;

/**
 * Package : com.amap.driverdemo.model
 * Author : xudong.tang
 * Date   : 18/4/19
 * 订单信息
 */

public class OrderInfo implements Parcelable{

    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 上车点信息
     */
    private Poi pickUpPosition;

    /**
     * 目的地
     */
    private Poi destinationPosition;


    /**
     * 乘客信息
     */
    private UserInfo userInfo;


    /**
     * 订单状态
     */
    private int orderState;

    /**
     * 订单信息
     * @param orderId 订单id
     */
    public OrderInfo(@NonNull String orderId){
        this.orderId = orderId;
    }

    protected OrderInfo(Parcel in) {
        orderId = in.readString();
        pickUpPosition = in.readParcelable(Poi.class.getClassLoader());
        destinationPosition = in.readParcelable(Poi.class.getClassLoader());
        userInfo = in.readParcelable(UserInfo.class.getClassLoader());
        orderState = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(orderId);
        dest.writeParcelable(pickUpPosition, flags);
        dest.writeParcelable(destinationPosition, flags);
        dest.writeParcelable(userInfo, flags);
        dest.writeInt(orderState);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OrderInfo> CREATOR = new Creator<OrderInfo>() {
        @Override
        public OrderInfo createFromParcel(Parcel in) {
            return new OrderInfo(in);
        }

        @Override
        public OrderInfo[] newArray(int size) {
            return new OrderInfo[size];
        }
    };

    public Poi getPickUpPosition() {
        return pickUpPosition;
    }

    public void setPickUpPosition(Poi pickUpPosition) {
        this.pickUpPosition = pickUpPosition;
    }

    public Poi getDestinationPosition() {
        return destinationPosition;
    }

    public void setDestinationPosition(Poi destinationPosition) {
        this.destinationPosition = destinationPosition;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
