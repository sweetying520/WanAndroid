package com.dream.wanandroid.presenter.project;

import com.dream.wanandroid.base.presenter.BasePresenter;
import com.dream.wanandroid.contract.project.ProjectListContract;
import com.dream.wanandroid.model.DataManager;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.project.ProjectListData;
import com.dream.wanandroid.model.event.JumpToTopEvent;
import com.dream.wanandroid.utils.RxBus;
import com.dream.wanandroid.utils.RxUtils;
import com.dream.wanandroid.widget.BaseObserver;

import javax.inject.Inject;

/**
 * ProjectListPresenter
 * Created by Administrator on 2018/5/4.
 */

public class ProjectListPresenter extends BasePresenter<ProjectListContract.View> implements ProjectListContract.Presenter {

    @Inject
    ProjectListPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void attachView(ProjectListContract.View mView) {
        super.attachView(mView);
        registerEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(JumpToTopEvent.class)
        .subscribe(jumpToTopEvent -> mView.jumpToTop()));
    }

    @Override
    public void getProjectListData(int page, int cid) {
        addSubscribe(mDataManager.getProjecListData(page, cid)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver<BaseResponse<ProjectListData>>(mView) {

                    @Override
                    public void onNext(BaseResponse<ProjectListData> projectListDataBaseResponse) {
                        if(projectListDataBaseResponse.getErrorCode() == BaseResponse.SUCCESS){
                            mView.showProjectListData(projectListDataBaseResponse);
                        }else {
                            mView.showProjectListDataFailed();
                        }
                    }
                }));
    }
}
