package com.dream.wanandroid.di.component;

import android.app.Activity;

import com.dream.wanandroid.di.module.ActivityModule;
import com.dream.wanandroid.di.scope.ActivityScope;
import com.dream.wanandroid.ui.main.activity.SplashActivity;
import com.dream.wanandroid.ui.main.activity.MainActivity;

import dagger.Component;

/**
 * Created by Administrator on 2018/4/28.
 */

@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    /**
     * 获取Activity实例
     *
     * @return Activity
     */
    Activity getActivity();

    /**
     * 注入SplashActivity所需的依赖
     *
     * @param  splashActivity
     */
    void inject(SplashActivity splashActivity);

    /**
     * 注入SplashActivity所需的依赖
     *
     * @param  mainActivity
     */
    void inject(MainActivity mainActivity);

}
