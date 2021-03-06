package com.dream.wanandroid.contract.main;

import com.dream.wanandroid.base.presenter.IBasePresenter;
import com.dream.wanandroid.base.view.IBaseView;
import com.tbruyelle.rxpermissions2.RxPermissions;

/**
 * Created by Administrator on 2018/5/8.
 */

public interface ArticleDetailContract {
    interface View extends IBaseView{
        void shareEvent();

        void shareFailed();

    }

    interface Presenter extends IBasePresenter<View>{
        void shareWithPermission(RxPermissions rxPermissions);
    }
}
