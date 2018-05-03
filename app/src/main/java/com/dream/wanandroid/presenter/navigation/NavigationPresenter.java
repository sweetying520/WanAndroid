package com.dream.wanandroid.presenter.navigation;

import com.dream.wanandroid.base.presenter.BasePresenter;
import com.dream.wanandroid.contract.navigation.NavigationContract;
import com.dream.wanandroid.model.DataManager;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/2.
 */

public class NavigationPresenter extends BasePresenter<NavigationContract.View> implements NavigationContract.Presenter{


    @Inject
     NavigationPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
