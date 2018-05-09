package com.dream.wanandroid.ui.hierarchy.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dream.wanandroid.R;
import com.dream.wanandroid.base.fragment.AbstractRootFragment;
import com.dream.wanandroid.common.MyConstant;
import com.dream.wanandroid.contract.hierarchy.KnowledgeHierarchyListContract;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleData;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleListData;
import com.dream.wanandroid.presenter.hierarchy.KnowledgeHierarchyListPresenter;
import com.dream.wanandroid.ui.mainpager.adapter.HomePagerAdapter;
import com.dream.wanandroid.utils.CommonUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**KnowledgeHierarchyListFragment
 * Created by Administrator on 2018/5/9.
 */

public class KnowledgeHierarchyListFragment extends AbstractRootFragment<KnowledgeHierarchyListPresenter> implements KnowledgeHierarchyListContract.View {


    @BindView(R.id.knowledge_hierarchy_list_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.normal_view)
    SmartRefreshLayout smartRefreshLayout;
    private HomePagerAdapter mAdapter;
    private List<FeedArticleData> dataList;
    private boolean isRefresh;
    private int mCurrentPage = 0;
    private int cid;


    public static KnowledgeHierarchyListFragment getInstance(int id,String params){
        KnowledgeHierarchyListFragment fragment = new KnowledgeHierarchyListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(MyConstant.ARG_PARAM1,id);
        bundle.putString(MyConstant.ARG_PARAM2,params);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_knowledge_hierarchy;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();

        cid = getArguments().getInt(MyConstant.ARG_PARAM1);

        initRecyclerView();

        setRefresh();

        mPresenter.getHierarchyListData(mCurrentPage,cid);

        if(CommonUtils.isNetworkConnected()){
            showLoadingView();
        }
    }

    /**
     * 下拉刷新 上拉加载
     */
    private void setRefresh() {
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            isRefresh = true;
            mCurrentPage = 0;
            mPresenter.getHierarchyListData(mCurrentPage,cid);
            smartRefreshLayout.finishRefresh(1000);
        });

        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            isRefresh = false;
            mCurrentPage++;
            mPresenter.getHierarchyListData(mCurrentPage,cid);
            smartRefreshLayout.finishLoadMore(1000);
        });
    }

    /**
     * 初始化recyclerview
     */
    private void initRecyclerView() {
        dataList = new ArrayList<>();
        mAdapter = new HomePagerAdapter(R.layout.item_home_pager,dataList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void showHierarchyListData(BaseResponse<FeedArticleListData> listDataBaseResponse) {
        if(listDataBaseResponse == null || listDataBaseResponse.getData() == null){
            showHierarchyListDataFailed();
            return;
        }

        if(listDataBaseResponse.getData().getDatas().size() > 0){
            if(isRefresh){
                mAdapter.replaceData(listDataBaseResponse.getData().getDatas());
            }else {
                mAdapter.addData(listDataBaseResponse.getData().getDatas());
            }
        }else {
            CommonUtils.showMessage(_mActivity,getString(R.string.load_more_no_data));
        }


        showNormalView();
    }

    @Override
    public void showHierarchyListDataFailed() {
        showErrorView();
        CommonUtils.showSnackMessage(_mActivity,getString(R.string.failed_to_obtain_knowledge_data));
    }

    @Override
    public void jumpToTop() {
        mRecyclerView.smoothScrollToPosition(0);
    }
}
