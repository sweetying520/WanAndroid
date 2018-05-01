package com.dream.wanandroid.base.activity;


import android.view.AbsSavedState;

import com.dream.wanandroid.WanAndroidApp;
import com.dream.wanandroid.base.presenter.IBasePresenter;
import com.dream.wanandroid.base.view.IBaseView;
import com.dream.wanandroid.di.component.ActivityComponent;
import com.dream.wanandroid.di.component.DaggerActivityComponent;
import com.dream.wanandroid.di.module.ActivityModule;

/**
 * Created by Administrator on 2018/4/27.
 */

public abstract class BaseActivity <P extends IBasePresenter> extends AbstractSimpleActivity implements IBaseView{

    protected P mPresenter;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.detachView();
        }
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if(mPresenter != null){
            mPresenter.attachView(this);
        }
        
    }

    protected ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder()
                .appComponent(WanAndroidApp.getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    /**
     * 注入当前Activity所需要的依赖
     */
    protected abstract void initInject();

    @Override
    public void reload() {

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void showNormalView() {

    }
}
