package com.amap.driverdemo.common;

import android.os.Handler;
import android.os.Looper;

public class UIExecutor {
    private static Handler handler = new Handler(Looper.getMainLooper());

    public static void execute(Runnable runnable) {
        execute(runnable, 0);
    }
    public static void execute(Runnable runnable, long delay) {
        if (runnable == null) {
            return;
        }

        delay = Math.max(delay, 0);

        handler.postDelayed(runnable, delay);
    }
}
