package com.dream.wanandroid.contract.main;

import com.dream.wanandroid.base.presenter.IBasePresenter;
import com.dream.wanandroid.base.view.IBaseView;

/**
 * Created by Administrator on 2018/5/9.
 */

public interface SettingContract {
    interface View extends IBaseView{

    }

    interface Presenter extends IBasePresenter<View>{
        void setAutoCacheState(boolean isAutoCache);
        void setNoImageState(boolean isNoImageState);
        void setNightMode(boolean isNightMode);
    }
}
