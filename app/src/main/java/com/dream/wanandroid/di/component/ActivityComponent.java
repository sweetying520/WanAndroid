package com.dream.wanandroid.di.component;

import android.app.Activity;

import com.dream.wanandroid.di.module.ActivityModule;
import com.dream.wanandroid.di.scope.ActivityScope;
import com.dream.wanandroid.ui.main.activity.ArticleDetailActivity;
import com.dream.wanandroid.ui.main.activity.LoginActivity;
import com.dream.wanandroid.ui.main.activity.SearchListActivity;
import com.dream.wanandroid.ui.main.activity.SplashActivity;
import com.dream.wanandroid.ui.main.activity.MainActivity;

import dagger.Component;

/**ActivityComponent
 * Created by Administrator on 2018/4/28.
 */

@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    /**
     * 获取Activity实例
     *
     * @return Activity Activity
     */
    Activity getActivity();

    /**
     * 注入SplashActivity所需的依赖
     *
     * @param  splashActivity splashActivity
     */
    void inject(SplashActivity splashActivity);

    /**
     * 注入SplashActivity所需的依赖
     *
     * @param   mainActivity mainActivity
     */
    void inject(MainActivity mainActivity);

    /**
     * 注入LoginActivity所需的依赖
     *
     * @param  loginActivity loginActivity
     */
    void inject(LoginActivity loginActivity);

    /**
     * 注入SearchListActivity所需的依赖
     *
     * @param  searchListActivity searchListActivity
     */
    void inject(SearchListActivity searchListActivity);

    /**
     * 注入ArticleDetailActivity所需的依赖
     *
     * @param  articleDetailActivity articleDetailActivity
     */
    void inject(ArticleDetailActivity articleDetailActivity);

}
