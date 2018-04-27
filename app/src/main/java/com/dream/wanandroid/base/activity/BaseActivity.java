package com.dream.wanandroid.base.activity;


import android.view.AbsSavedState;

import com.dream.wanandroid.base.presenter.IBasePresenter;
import com.dream.wanandroid.base.view.IBaseView;

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

    /**
     * 注入当前Activity所需要的依赖
     */
    protected abstract void initInject();

    @Override
    public void reload() {

    }
}
