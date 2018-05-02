package com.dream.wanandroid.contract.navigation;

import com.dream.wanandroid.base.presenter.IBasePresenter;
import com.dream.wanandroid.base.view.IBaseView;

/**
 * Created by Administrator on 2018/5/2.
 */

public interface NavigationContract {

    interface View extends IBaseView{

    }

    interface Presenter extends IBasePresenter<View>{

    }
}
