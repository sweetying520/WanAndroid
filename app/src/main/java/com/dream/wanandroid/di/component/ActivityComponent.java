package com.dream.wanandroid.di.component;

import android.app.Activity;

import com.dream.wanandroid.di.module.ActivityModule;
import com.dream.wanandroid.di.scope.ActivityScope;
import com.dream.wanandroid.ui.mainpager.activity.MainActivity;

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
     * 注入MAinActivity所需的依赖
     *
     * @param mainActivity MainActivity
     */
    void inject(MainActivity mainActivity);

}
