package com.dream.wanandroid.contract.main;

import com.dream.wanandroid.base.presenter.IBasePresenter;
import com.dream.wanandroid.base.view.IBaseView;

/**
 * Created by Administrator on 2018/5/1.
 */

public interface MainContract {

    interface View extends IBaseView{

    }

    interface Presenter extends IBasePresenter<View>{
        void setCurrentPage(int position);
    }
}
