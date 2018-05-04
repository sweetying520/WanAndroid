package com.dream.wanandroid.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dream.wanandroid.WanAndroidApp;
import com.dream.wanandroid.base.presenter.IBasePresenter;
import com.dream.wanandroid.base.view.IBaseView;
import com.dream.wanandroid.di.component.DaggerFragmentComponent;
import com.dream.wanandroid.di.component.FragmentComponent;
import com.dream.wanandroid.di.module.FragmentModule;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/4.
 */

public abstract class BaseDialogFragment<P extends IBasePresenter> extends AbstractSimpleDialogFragmnet implements IBaseView{

    @Inject
    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInject();
        if(mPresenter != null){
            mPresenter.attachView(this);
        }
    }


    @Override
    public void onDestroyView() {
        if(mPresenter != null){
            mPresenter.detachView();
        }
        super.onDestroyView();
    }


    protected FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder()
                .appComponent(WanAndroidApp.getAppComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
    }

    /**
     * 依赖注入
     */
    protected abstract void initInject();

    @Override
    public void showErrorMsg(String errorMsg) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showCollectFail() {

    }

    @Override
    public void showLogoutView() {

    }

    @Override
    public void showLoginView() {

    }

    @Override
    public void showNormalView() {

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void reload() {

    }
}
