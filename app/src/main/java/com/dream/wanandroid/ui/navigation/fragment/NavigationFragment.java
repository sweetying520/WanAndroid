package com.dream.wanandroid.ui.navigation.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dream.wanandroid.R;
import com.dream.wanandroid.base.fragment.AbstractRootFragment;
import com.dream.wanandroid.common.MyConstant;
import com.dream.wanandroid.contract.navigation.NavigationContract;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.navigation.NavigationData;
import com.dream.wanandroid.presenter.navigation.NavigationPresenter;
import com.dream.wanandroid.ui.navigation.adapter.NavigationAdapter;
import com.dream.wanandroid.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.SimpleTabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;

/**
 * 导航
 */
public class NavigationFragment extends AbstractRootFragment<NavigationPresenter> implements NavigationContract.View {


    @BindView(R.id.vertical_tab)
    VerticalTabLayout verticalTab;
    @BindView(R.id.navigation_rv)
    RecyclerView navigationRv;

    private NavigationAdapter mAdapter;
    private List<NavigationData> dataList;


    public static NavigationFragment getInstance(String params1, String params2) {
        NavigationFragment fragment = new NavigationFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MyConstant.ARG_PARAM1, params1);
        bundle.putString(MyConstant.ARG_PARAM2, params2);
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

        initRecyclerView();

        mPresenter.getNavigationListData();

        if (CommonUtils.isNetworkConnected()) {
            showLoadingView();
        }
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        dataList = new ArrayList<>();
        mAdapter = new NavigationAdapter(dataList);
        navigationRv.setLayoutManager(new LinearLayoutManager(_mActivity));
        navigationRv.setAdapter(mAdapter);
    }

    @Override
    public void showNavigationListData(BaseResponse<List<NavigationData>> listBaseResponse) {
        if (listBaseResponse == null || listBaseResponse.getData() == null) {
            showNavigationListDataFailed();
            return;
        }

        verticalTab.setTabAdapter(new SimpleTabAdapter() {
            @Override
            public int getCount() {
                return listBaseResponse.getData() == null ? 0 : listBaseResponse.getData().size();
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new ITabView.TabTitle.Builder().setContent(listBaseResponse.getData().get(position).getName())
                        .setTextColor(ContextCompat.getColor(_mActivity, R.color.shallow_green)
                                , ContextCompat.getColor(_mActivity, R.color.shallow_grey))
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return -1;
            }
        });

        mAdapter.replaceData(listBaseResponse.getData());

        showNormalView();
    }

    @Override
    public void reload() {
        if(CommonUtils.isNetworkConnected()){
            mPresenter.getNavigationListData();
        }
    }


    public void jumpToTop(){
        if(navigationRv != null){
            navigationRv.smoothScrollToPosition(0);
        }
    }

    @Override
    public void showNavigationListDataFailed() {
        CommonUtils.showSnackMessage(_mActivity, getString(R.string.failed_to_obtain_navigation_list));
    }


}
