package com.dream.wanandroid;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.dream.wanandroid.di.component.AppComponent;
import com.dream.wanandroid.di.component.DaggerAppComponent;
import com.dream.wanandroid.di.module.AppModule;
import com.dream.wanandroid.di.module.HttpModule;

/**
 * Created by Administrator on 2018/4/27.
 */

public class WanAndroidApp extends Application{
    private static WanAndroidApp mWanAndroidApp;
    private static volatile AppComponent appComponent;
    public static boolean isFirstRun;

    @Override
    public void onCreate() {
        super.onCreate();
        mWanAndroidApp = this;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if(level == TRIM_MEMORY_UI_HIDDEN){
            Glide.get(this).clearMemory();
        }
        Glide.get(this).trimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(this).clearMemory();
    }

    public static WanAndroidApp getInstance(){
        return mWanAndroidApp;
    }

    public static synchronized AppComponent getAppComponent(){
        if(appComponent == null){
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(mWanAndroidApp))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }
}
