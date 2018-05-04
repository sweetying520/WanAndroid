package com.dream.wanandroid.contract.project;

import com.dream.wanandroid.base.presenter.IBasePresenter;
import com.dream.wanandroid.base.view.IBaseView;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.project.ProjectListData;

/**
 * ProjectListContract
 * Created by Administrator on 2018/5/4.
 */

public interface ProjectListContract {
    interface View extends IBaseView{

        void showProjectListData(BaseResponse<ProjectListData> listBaseResponse);

        void showProjectListDataFailed();

        void jumpToTop();
    }

    interface Presenter extends IBasePresenter<View>{
        void getProjectListData(int page,int cid);
    }
}
