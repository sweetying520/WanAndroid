package com.dream.wanandroid.presenter.project;

import com.dream.wanandroid.base.presenter.BasePresenter;
import com.dream.wanandroid.contract.project.ProjectContract;
import com.dream.wanandroid.model.DataManager;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/2.
 */

public class ProjectPresenter extends BasePresenter<ProjectContract.View> implements ProjectContract.Presenter{

    @Inject
     ProjectPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void attachView(ProjectContract.View mView) {
        super.attachView(mView);
    }
}
