package com.dream.wanandroid.presenter.hierarchy;

import com.dream.wanandroid.base.presenter.BasePresenter;
import com.dream.wanandroid.contract.hierarchy.KnowledgeHierarchyContract;
import com.dream.wanandroid.model.DataManager;

/**
 * Created by Administrator on 2018/5/2.
 */

public class KnowledgeHierarchyPresenter extends BasePresenter<KnowledgeHierarchyContract.View> implements KnowledgeHierarchyContract.Presenter{


    public KnowledgeHierarchyPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
