package com.amap.driverdemo.model;

public class OrderModel extends BaseModel {
    public String timeStr;
    public String type;
    public String status;
    public String startPos;
    public String destPos;

    public OrderModel(String timeStr, String type, String status, String startPos, String endPos) {
        this.timeStr = timeStr;
        this.type = type;
        this.status = status;
        this.startPos = startPos;
        this.destPos = endPos;
    }
}
