package com.dream.wanandroid.presenter.main;

import com.dream.wanandroid.base.presenter.BasePresenter;
import com.dream.wanandroid.contract.main.MainContract;
import com.dream.wanandroid.model.DataManager;

/**
 * Created by Administrator on 2018/5/1.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter{

    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void attachView(MainContract.View mView) {
        super.attachView(mView);
    }
}
