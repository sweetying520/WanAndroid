package com.dream.wanandroid.ui.navigation.fragment;

import android.os.Bundle;

import com.dream.wanandroid.R;
import com.dream.wanandroid.base.fragment.AbstractRootFragment;
import com.dream.wanandroid.common.MyConstant;
import com.dream.wanandroid.contract.navigation.NavigationContract;
import com.dream.wanandroid.presenter.navigation.NavigationPresenter;

/**
 * 导航
 */
public class NavigationFragment extends AbstractRootFragment<NavigationPresenter> implements NavigationContract.View{


    public static NavigationFragment getInstance(String params1, String params2){
        NavigationFragment fragment = new NavigationFragment();
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
        return R.layout.fragment_navigation;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
    }
}
