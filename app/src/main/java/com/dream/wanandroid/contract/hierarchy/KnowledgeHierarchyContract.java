package com.dream.wanandroid.contract.hierarchy;

import com.dream.wanandroid.base.presenter.IBasePresenter;
import com.dream.wanandroid.base.view.IBaseView;

/**
 * Created by Administrator on 2018/5/2.
 */

public interface KnowledgeHierarchyContract {

    interface View extends IBaseView{

    }

    interface Presenter extends IBasePresenter<View>{

    }
}