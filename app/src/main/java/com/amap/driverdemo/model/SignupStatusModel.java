package com.amap.driverdemo.model;

public class SignupStatusModel extends BaseModel{
    public String title;
    public boolean isChecked;

    public SignupStatusModel(String title, boolean isChecked) {
        this.title = title;
        this.isChecked = isChecked;
    }
}
