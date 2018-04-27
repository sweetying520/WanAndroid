package com.dream.wanandroid;

import android.app.Application;

/**
 * Created by Administrator on 2018/4/27.
 */

public class WanAndroidApp extends Application{
    private static WanAndroidApp mWanAndroidApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mWanAndroidApp = this;
    }

    public static WanAndroidApp getInstance(){
        return mWanAndroidApp;
    }
}
