package com.dream.wanandroid.presenter.main;

import com.dream.wanandroid.base.presenter.BasePresenter;
import com.dream.wanandroid.contract.main.SearchDialogContract;
import com.dream.wanandroid.model.DataManager;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/4.
 */

public class SearchDialogPresenter extends BasePresenter<SearchDialogContract.View> implements SearchDialogContract.Presenter{

    @Inject
    SearchDialogPresenter(DataManager dataManager) {
        super(dataManager);
    }



    @Override
    public void getHotSearchData() {

    }
}
