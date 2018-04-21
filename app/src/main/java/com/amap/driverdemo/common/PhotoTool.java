package com.amap.driverdemo.common;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

/**
 * Created by my on 2018/4/19.
 */

public class PhotoTool {

    public static Bitmap getTakenCamera(Intent intent) {
        if (intent == null) {
            return null;
        }

        try {
            Bundle extras = intent.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            return imageBitmap;
        } catch (Throwable throwable) {
            return null;
        }

    }
}
