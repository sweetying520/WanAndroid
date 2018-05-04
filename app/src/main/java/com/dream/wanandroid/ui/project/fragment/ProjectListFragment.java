package com.dream.wanandroid.ui.project.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dream.wanandroid.R;
import com.dream.wanandroid.base.fragment.AbstractRootFragment;
import com.dream.wanandroid.common.MyConstant;
import com.dream.wanandroid.contract.project.ProjectListContract;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleData;
import com.dream.wanandroid.model.bean.project.ProjectListData;
import com.dream.wanandroid.presenter.project.ProjectListPresenter;
import com.dream.wanandroid.ui.project.adapter.ProjectListAdapter;
import com.dream.wanandroid.utils.CommonUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**ProjectListFragment
 * Created by Administrator on 2018/5/4.
 */

public class ProjectListFragment extends AbstractRootFragment<ProjectListPresenter> implements ProjectListContract.View {


    @BindView(R.id.project_list_rv)
    RecyclerView projectRv;
    @BindView(R.id.normal_view)
    SmartRefreshLayout smartRefreshLayout;
    private int cid;
    private int mCurrentPage = 1;
    private boolean isRefresh = true;
    private ProjectListAdapter mAdapter;
    private List<FeedArticleData> dataList;

    public static ProjectListFragment getInstance(int cid,String params){
        ProjectListFragment fragment = new ProjectListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(MyConstant.ARG_PARAM1,cid);
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
        return R.layout.fragment_list_project;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        cid = getArguments().getInt(MyConstant.ARG_PARAM1);

        initRecyclerView();

        setRefresh();

        mPresenter.getProjectListData(mCurrentPage,cid);

        if(CommonUtils.isNetworkConnected()){
            showLoadingView();
        }
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        dataList = new ArrayList<>();
        mAdapter = new ProjectListAdapter(dataList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {

        });

        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()){
                case R.id.item_project_list_install_tv:
                    break;
            }
        });
        projectRv.setLayoutManager(new LinearLayoutManager(_mActivity));
        projectRv.setAdapter(mAdapter);
    }


    @Override
    public void showProjectListData(BaseResponse<ProjectListData> listBaseResponse) {
        if (listBaseResponse == null || listBaseResponse.getData() == null) {
            showProjectListDataFailed();
            return;
        }
        if(listBaseResponse.getData().getCurPage() > listBaseResponse.getData().getPageCount()){
            CommonUtils.showSnackMessage(_mActivity,getString(R.string.load_more_no_data));
        }else {
            if(isRefresh){
                dataList = listBaseResponse.getData().getDatas();
                mAdapter.replaceData(listBaseResponse.getData().getDatas());
            }else {
                dataList.addAll(listBaseResponse.getData().getDatas());
                mAdapter.addData(listBaseResponse.getData().getDatas());
            }
        }


        showNormalView();
    }

    @Override
    public void showProjectListDataFailed() {
        CommonUtils.showSnackMessage(_mActivity, getString(R.string.failed_to_obtain_project_list));
    }

    @Override
    public void jumpToTop() {
        if(projectRv != null){
            projectRv.smoothScrollToPosition(0);
        }
    }

    @Override
    public void reload() {
        if(CommonUtils.isNetworkConnected()){
            smartRefreshLayout.autoRefresh();
        }
    }

    private void setRefresh(){
        mCurrentPage = 1;
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            isRefresh = true;
            mCurrentPage = 1;
            mPresenter.getProjectListData(mCurrentPage,cid);
            refreshLayout.finishRefresh(1000);
        });

        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            isRefresh = false;
            mCurrentPage++;
            mPresenter.getProjectListData(mCurrentPage,cid);
            refreshLayout.finishLoadMore(1000);
        });
    }


}
