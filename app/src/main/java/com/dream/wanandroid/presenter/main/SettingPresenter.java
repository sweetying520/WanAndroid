package com.dream.wanandroid.presenter.main;

import com.dream.wanandroid.base.presenter.BasePresenter;
import com.dream.wanandroid.contract.main.SettingContract;
import com.dream.wanandroid.model.DataManager;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/9.
 */

public class SettingPresenter extends BasePresenter<SettingContract.View> implements SettingContract.Presenter {

    @Inject
    SettingPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void setAutoCacheState(boolean isAutoCache) {
        mDataManager.setAutoCacheState(isAutoCache);
    }

    @Override
    public void setNoImageState(boolean isNoImageState) {
        mDataManager.setNoImageState(isNoImageState);
    }

    @Override
    public void setNightMode(boolean isNightMode) {
        mDataManager.setNightModeState(isNightMode);
    }
}
