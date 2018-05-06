package com.dream.wanandroid.presenter.main;

import com.dream.wanandroid.base.presenter.BasePresenter;
import com.dream.wanandroid.contract.main.LoginContract;
import com.dream.wanandroid.model.DataManager;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/6.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    @Inject
    LoginPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
