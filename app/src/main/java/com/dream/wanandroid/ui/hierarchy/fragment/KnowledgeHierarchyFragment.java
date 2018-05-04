package com.dream.wanandroid.ui.hierarchy.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dream.wanandroid.R;
import com.dream.wanandroid.base.fragment.AbstractRootFragment;
import com.dream.wanandroid.common.MyConstant;
import com.dream.wanandroid.contract.hierarchy.KnowledgeHierarchyContract;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.hierarchy.HierarchyData;
import com.dream.wanandroid.presenter.hierarchy.KnowledgeHierarchyPresenter;
import com.dream.wanandroid.ui.hierarchy.adapter.KnowledgeHierarchyAdapter;
import com.dream.wanandroid.utils.CommonUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 知识体系
 */
public class KnowledgeHierarchyFragment extends AbstractRootFragment<KnowledgeHierarchyPresenter> implements KnowledgeHierarchyContract.View {


    @BindView(R.id.knowledge_hierarchy_rv)
    RecyclerView knowledgeHierarchyRv;
    @BindView(R.id.normal_view)
    SmartRefreshLayout smartRefreshLayout;
    KnowledgeHierarchyAdapter mAdapter;
    private List<HierarchyData> dataList;
    private boolean isRefresh;


    public static KnowledgeHierarchyFragment getInstance(String params1, String params2) {
        KnowledgeHierarchyFragment fragment = new KnowledgeHierarchyFragment();
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
        return R.layout.fragment_hierarchy_knowledge;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();

        initRecyclerView();

        setRefresh();
        mPresenter.getHierarchyData();
        if (CommonUtils.isNetworkConnected()) {
            showLoadingView();
        }
    }

    private void initRecyclerView() {
        dataList = new ArrayList<>();
        mAdapter = new KnowledgeHierarchyAdapter(dataList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {

        });
        knowledgeHierarchyRv.setLayoutManager(new LinearLayoutManager(_mActivity));
        knowledgeHierarchyRv.setAdapter(mAdapter);
    }


    @Override
    public void showHierarchyDataList(BaseResponse<List<HierarchyData>> listResponse) {
        if (listResponse == null || listResponse.getData() == null) {
            showHierarchyDataListFailed();
            return;
        }

        if(mAdapter.getData().size() < listResponse.getData().size()){
            dataList = listResponse.getData();
            mAdapter.replaceData(dataList);
        }else {
            if(!isRefresh){
                CommonUtils.showMessage(_mActivity,getString(R.string.load_more_no_data));
            }
        }
        showNormalView();
    }

    @Override
    public void showHierarchyDataListFailed() {
        CommonUtils.showSnackMessage(_mActivity, getString(R.string.failed_to_obtain_knowledge_data));
    }

    @Override
    public void reload() {
        if (CommonUtils.isNetworkConnected()) {
            smartRefreshLayout.autoRefresh();
        }
    }

    private void setRefresh() {
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            isRefresh = true;
            mPresenter.getHierarchyData();
            refreshLayout.finishRefresh(1000);
        });

        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            isRefresh = false;
            mPresenter.getHierarchyData();
            refreshLayout.finishLoadMore(1000);
        });
    }

    public void jumpToTop(){
        if(knowledgeHierarchyRv != null){
            knowledgeHierarchyRv.smoothScrollToPosition(0);
        }
    }
}
