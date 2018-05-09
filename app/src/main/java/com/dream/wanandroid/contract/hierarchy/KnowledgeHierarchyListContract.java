package com.dream.wanandroid.contract.hierarchy;

import com.dream.wanandroid.base.presenter.IBasePresenter;
import com.dream.wanandroid.base.view.IBaseView;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleListData;

/**
 * Created by Administrator on 2018/5/9.
 */

public interface KnowledgeHierarchyListContract {
    interface View extends IBaseView{
        void showHierarchyListData(BaseResponse<FeedArticleListData> listDataBaseResponse);
        void showHierarchyListDataFailed();

        void jumpToTop();

    }

    interface Presenter extends IBasePresenter<View>{
        void getHierarchyListData(int page,int cid);
    }
}
