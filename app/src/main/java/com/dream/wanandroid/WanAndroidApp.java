package com.dream.wanandroid;

import android.app.Application;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;

import com.bumptech.glide.Glide;
import com.dream.wanandroid.di.component.AppComponent;
import com.dream.wanandroid.di.component.DaggerAppComponent;
import com.dream.wanandroid.di.module.AppModule;
import com.dream.wanandroid.di.module.HttpModule;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;

/**
 * Created by Administrator on 2018/4/27.
 */

public class WanAndroidApp extends Application{
    private static WanAndroidApp mWanAndroidApp;
    private static volatile AppComponent appComponent;
    public static boolean isFirstRun;

    //static 代码段可以防止内存泄露, 全局设置刷新头部及尾部，优先级最低
    static {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);
        SmartRefreshLayout.setDefaultRefreshHeaderCreater((context, refreshLayout) -> {
            //全局设置主题颜色
            refreshLayout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            //指定为Delivery Header，默认是贝塞尔雷达Header
            return new DeliveryHeader(context);
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreater((context, layout) -> {
            //默认是 BallPulseFooter
            return new BallPulseFooter(context).setAnimatingColor(ContextCompat.getColor(context, R.color.colorPrimary));
        });
    }

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
