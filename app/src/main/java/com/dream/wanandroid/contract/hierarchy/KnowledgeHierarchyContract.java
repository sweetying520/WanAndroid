package com.dream.wanandroid.contract.hierarchy;

import com.dream.wanandroid.base.presenter.IBasePresenter;
import com.dream.wanandroid.base.view.IBaseView;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.hierarchy.HierarchyData;

import java.util.List;

/**
 * Created by Administrator on 2018/5/2.
 */

public interface KnowledgeHierarchyContract {

    interface View extends IBaseView{

        void showHierarchyDataList(BaseResponse<List<HierarchyData>> listResponse);

        void showHierarchyDataListFailed();

    }

    interface Presenter extends IBasePresenter<View>{
        void getHierarchyData();


    }
}
