package com.amap.driverdemo.model;

public class SettingItemModel {
    public String title;
    public String subTitle;
    public boolean isClickable;
    public boolean isVoid;
    public boolean isCheckBtn;

    public SettingItemModel(String title, String subTitle, boolean isClickable, boolean isVoid) {
        this(title, subTitle, isClickable, isVoid, false);
    }

    public SettingItemModel(String title, String subTitle, boolean isClickable, boolean isVoid, boolean isCheckBtn) {
        this.title = title;
        this.subTitle = subTitle;
        this.isClickable = isClickable;
        this.isVoid = isVoid;
        this.isCheckBtn = isCheckBtn;
    }
}
