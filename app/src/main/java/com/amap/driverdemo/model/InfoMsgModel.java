package com.amap.driverdemo.model;

public class InfoMsgModel extends MsgModel {
    public String timeStr;

    public InfoMsgModel(String title, String timeStr) {
        super();
        this.title = title;
        this.timeStr = timeStr;
    }
}
