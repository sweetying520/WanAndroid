package com.dream.wanandroid.presenter.project;

import com.dream.wanandroid.base.presenter.BasePresenter;
import com.dream.wanandroid.contract.project.ProjectContract;
import com.dream.wanandroid.model.DataManager;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.project.ProjectTabData;
import com.dream.wanandroid.utils.RxUtils;
import com.dream.wanandroid.widget.BaseObserver;

import java.util.List;

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


    @Override
    public void getProjectTagData() {
        addSubscribe(mDataManager.getProjectTabData()
        .compose(RxUtils.rxSchedulerHelper())
        .filter(listBaseResponse -> mView != null)
        .subscribeWith(new BaseObserver<BaseResponse<List<ProjectTabData>>>(mView){
            @Override
            public void onNext(BaseResponse<List<ProjectTabData>> listBaseResponse) {
                if(listBaseResponse.getErrorCode() == BaseResponse.SUCCESS){
                    mView.showProjectTabData(listBaseResponse);
                }else {
                    mView.showProjectTabDataFailed();
                }
            }
        }));
    }
}
