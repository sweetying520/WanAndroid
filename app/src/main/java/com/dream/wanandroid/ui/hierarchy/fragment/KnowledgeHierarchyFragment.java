package com.dream.wanandroid.ui.hierarchy.fragment;


import android.os.Bundle;

import com.dream.wanandroid.R;
import com.dream.wanandroid.base.fragment.AbstractRootFragment;
import com.dream.wanandroid.common.MyConstant;
import com.dream.wanandroid.contract.hierarchy.KnowledgeHierarchyContract;
import com.dream.wanandroid.presenter.hierarchy.KnowledgeHierarchyPresenter;

/**
 * 知识体系
 */
public class KnowledgeHierarchyFragment extends AbstractRootFragment<KnowledgeHierarchyPresenter> implements KnowledgeHierarchyContract.View{



    public static KnowledgeHierarchyFragment getInstance(String params1, String params2){
        KnowledgeHierarchyFragment fragment = new KnowledgeHierarchyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MyConstant.ARG_PARAM1,params1);
        bundle.putString(MyConstant.ARG_PARAM2,params2);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hierarchy_knowledge;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
    }
}
