package com.amap.driverdemo.common;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorImpl {
    private ScheduledThreadPoolExecutor mScheduledThreadPoolExecutor;

    public ScheduledExecutorImpl() {
        this.mScheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
    }

    public void execute(Runnable runnable, int interval) {
        execute(runnable, interval, 0);
    }

    public void execute(Runnable runnable , int interval , int delay) {
        if (runnable == null) {
            return;
        }

        mScheduledThreadPoolExecutor.scheduleAtFixedRate(runnable, delay, interval, TimeUnit.MILLISECONDS);
        mScheduledThreadPoolExecutor.execute(runnable);
    }

    public void cancel(){
        if(mScheduledThreadPoolExecutor==null){
            return;
        }

        mScheduledThreadPoolExecutor.shutdown();
    }
}
