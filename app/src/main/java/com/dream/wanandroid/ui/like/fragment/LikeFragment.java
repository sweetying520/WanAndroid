package com.dream.wanandroid.ui.like.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dream.wanandroid.R;
import com.dream.wanandroid.base.fragment.AbstractRootFragment;
import com.dream.wanandroid.common.MyConstant;
import com.dream.wanandroid.contract.like.LikeContract;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleData;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleListData;
import com.dream.wanandroid.presenter.like.LikePresenter;
import com.dream.wanandroid.ui.mainpager.adapter.HomePagerAdapter;
import com.dream.wanandroid.utils.CommonUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/5/6.
 */

public class LikeFragment extends AbstractRootFragment<LikePresenter> implements LikeContract.View{


    @BindView(R.id.normal_view)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.like_rv)
    RecyclerView mRecyclerView;
    private HomePagerAdapter mAdapter;
    private List<FeedArticleData> dataList;

    private boolean isRefresh = true;
    private int mCurrentPage = 1;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    public static LikeFragment getInstance(String params1, String params2){
        LikeFragment fragment = new LikeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MyConstant.ARG_PARAM1,params1);
        bundle.putString(MyConstant.ARG_PARAM2,params2);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_like;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();

        initRecyclerView();

        setRefresh();
        mPresenter.getCollectList(mCurrentPage);

        if(CommonUtils.isNetworkConnected()){
            showLoadingView();
        }
    }

    private void initRecyclerView() {
        dataList = new ArrayList<>();
        mAdapter = new HomePagerAdapter(R.layout.item_home_pager, dataList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showCollectList(BaseResponse<FeedArticleListData> listDataBaseResponse) {
        if(listDataBaseResponse == null || listDataBaseResponse.getData() == null){
            showCollectListFailed();
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
    public void showCollectListFailed() {
        showErrorView();
        CommonUtils.showSnackMessage(_mActivity,getString(R.string.failed_to_obtain_article_list));
    }

    private void setRefresh(){
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            isRefresh = true;
            mCurrentPage = 1;
            mPresenter.getCollectList(mCurrentPage);
        });

        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            isRefresh = false;
            mCurrentPage++;
            mPresenter.getCollectList(mCurrentPage);
        });
    }
}
