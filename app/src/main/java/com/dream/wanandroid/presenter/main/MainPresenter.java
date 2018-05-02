package com.dream.wanandroid.presenter.main;

import com.dream.wanandroid.base.presenter.BasePresenter;
import com.dream.wanandroid.contract.main.MainContract;
import com.dream.wanandroid.model.DataManager;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/1.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter{

    private DataManager mdaDataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        super(dataManager);
        mdaDataManager = dataManager;
    }

    @Override
    public void attachView(MainContract.View mView) {
        super.attachView(mView);
        registerEvent();
    }

    private void registerEvent() {

    }
}
