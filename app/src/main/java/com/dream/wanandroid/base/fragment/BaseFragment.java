package com.dream.wanandroid.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dream.wanandroid.base.presenter.IBasePresenter;
import com.dream.wanandroid.base.view.IBaseView;

/**
 * Created by Administrator on 2018/4/27.
 */

public abstract class BaseFragment<P extends IBasePresenter> extends AbstractSimpleFragment implements IBaseView{
    protected P mPresenter;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        if(mPresenter != null){
            mPresenter.attachView(this);
        }
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onDestroyView() {
        if(mPresenter != null){
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    /**
     * 注入当前fragment需要的依赖
     */
    protected abstract void initInject();
}
