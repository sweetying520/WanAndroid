package com.dream.wanandroid.contract.main;

import com.dream.wanandroid.base.presenter.IBasePresenter;
import com.dream.wanandroid.base.view.IBaseView;


public interface SplashContract {

     interface View extends IBaseView{
        void jumpToMain();
    }

     interface Presenter extends IBasePresenter<View>{

    }
}
