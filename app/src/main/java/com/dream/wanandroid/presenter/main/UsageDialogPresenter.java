package com.dream.wanandroid.presenter.main;

import com.dream.wanandroid.base.presenter.BasePresenter;
import com.dream.wanandroid.contract.main.UsageDialogContract;
import com.dream.wanandroid.model.DataManager;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/4.
 */

public class UsageDialogPresenter extends BasePresenter<UsageDialogContract.View> implements UsageDialogContract.Presenter{


    @Inject
    UsageDialogPresenter(DataManager dataManager) {
        super(dataManager);
    }

}
