package com.dream.wanandroid.di.module;

import com.dream.wanandroid.WanAndroidApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/4/28.
 */

@Module
public class AppModule {
    private final WanAndroidApp mWanAndroidApp;

    public AppModule(WanAndroidApp mWanAndroidApp) {
        this.mWanAndroidApp = mWanAndroidApp;
    }

    @Provides
    @Singleton
    WanAndroidApp provideApplicationContext(){
        return mWanAndroidApp;
    }




}
