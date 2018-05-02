package com.dream.wanandroid.ui.project.fragment;

import android.os.Bundle;

import com.dream.wanandroid.R;
import com.dream.wanandroid.base.fragment.AbstractRootFragment;
import com.dream.wanandroid.common.MyConstant;
import com.dream.wanandroid.contract.project.ProjectContract;
import com.dream.wanandroid.presenter.project.ProjectPresenter;

/**
 * Created by Administrator on 2018/5/2.
 */

public class ProjectFragment extends AbstractRootFragment<ProjectPresenter> implements ProjectContract.View{



    public static ProjectFragment getInstance(String params1, String params2){
        ProjectFragment fragment = new ProjectFragment();
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
        return R.layout.fragment_project;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
    }
}
