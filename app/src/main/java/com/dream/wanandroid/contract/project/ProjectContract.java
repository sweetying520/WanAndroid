package com.dream.wanandroid.contract.project;

import com.dream.wanandroid.base.presenter.IBasePresenter;
import com.dream.wanandroid.base.view.IBaseView;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.project.ProjectTabData;

import java.util.List;

/**
 * Created by Administrator on 2018/5/2.
 */

public interface ProjectContract {

    interface View extends IBaseView{
        void showProjectTabData(BaseResponse<List<ProjectTabData>> listBaseResponse);

        void showProjectTabDataFailed();

    }

    interface Presenter extends IBasePresenter<View>{
        void getProjectTagData();


    }
}
