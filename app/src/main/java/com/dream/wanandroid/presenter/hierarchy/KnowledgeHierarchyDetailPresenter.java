package com.dream.wanandroid.presenter.hierarchy;

import com.dream.wanandroid.base.presenter.BasePresenter;
import com.dream.wanandroid.contract.hierarchy.KnowledgeHierarchyDetailContract;
import com.dream.wanandroid.model.DataManager;

import javax.inject.Inject;

/**KnowledgeHierarchyDetailPresenter
 * Created by Administrator on 2018/5/9.
 */

public class KnowledgeHierarchyDetailPresenter extends BasePresenter<KnowledgeHierarchyDetailContract.View> implements KnowledgeHierarchyDetailContract.Presenter {

    @Inject
    KnowledgeHierarchyDetailPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
