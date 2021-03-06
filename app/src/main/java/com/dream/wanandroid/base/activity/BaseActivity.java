package com.dream.wanandroid.base.activity;


import com.dream.wanandroid.WanAndroidApp;
import com.dream.wanandroid.base.presenter.IBasePresenter;
import com.dream.wanandroid.base.view.IBaseView;
import com.dream.wanandroid.di.component.ActivityComponent;
import com.dream.wanandroid.di.component.DaggerActivityComponent;
import com.dream.wanandroid.di.module.ActivityModule;
import com.dream.wanandroid.utils.CommonUtils;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/4/27.
 */

public abstract class BaseActivity <P extends IBasePresenter> extends AbstractSimpleActivity implements IBaseView{

    @Inject
    protected P mPresenter;


    @Override
    protected void onDestroy() {
        if(mPresenter != null){
            mPresenter.detachView();
        }
        super.onDestroy();

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

    @Override
    public void showLoginView() {

    }

    @Override
    public void showLogoutView() {

    }

    @Override
    public void showCollectFail() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showErrorMsg(String errorMsg) {
        CommonUtils.showSnackMessage(mActivity,errorMsg);
    }
}
