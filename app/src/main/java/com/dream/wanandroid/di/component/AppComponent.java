package com.dream.wanandroid.di.component;

import com.dream.wanandroid.WanAndroidApp;
import com.dream.wanandroid.di.module.AppModule;
import com.dream.wanandroid.di.module.HttpModule;
import com.dream.wanandroid.model.DataManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2018/4/28.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    /**
     * 提供App的Context
     */
    WanAndroidApp getContext();

    /**
     * 数据中心
     */
    DataManager getDataManager();
}
