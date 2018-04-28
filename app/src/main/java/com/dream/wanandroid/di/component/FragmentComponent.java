package com.dream.wanandroid.di.component;

import android.app.Activity;

import com.dream.wanandroid.di.module.FragmentModule;
import com.dream.wanandroid.di.scope.FragmentScope;

import dagger.Component;

/**
 * Created by Administrator on 2018/4/28.
 */

@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {

    /**
     * 获取Activity的实例
     */
    Activity getActivity();
}
