package com.dream.wanandroid.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;

import com.dream.wanandroid.R;
import com.dream.wanandroid.base.activity.AbstractRootActivity;
import com.dream.wanandroid.common.MyConstant;
import com.dream.wanandroid.contract.main.SearchListContract;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleData;
import com.dream.wanandroid.model.bean.main.search.SearchData;
import com.dream.wanandroid.presenter.main.SearchListPresenter;
import com.dream.wanandroid.ui.mainpager.adapter.HomePagerAdapter;
import com.dream.wanandroid.utils.CommonUtils;
import com.dream.wanandroid.utils.StatusBarUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**SearchListActivity
 * Created by Administrator on 2018/5/8.
 */

public class SearchListActivity extends AbstractRootActivity<SearchListPresenter> implements SearchListContract.View {

    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.normal_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    @BindView(R.id.fab_search_list)
    FloatingActionButton fabSearchList;
    private Context mContext;
    private int mCurrentPage = 0;
    private boolean isRefresh = true;
    private String key;
    private HomePagerAdapter mAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_list_search;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mContext = this;

        initToolbar();
        initRecyclerView();
        fabSearchList.setOnClickListener(v -> mRecyclerView.smoothScrollToPosition(0));
        setRefresh();

        mPresenter.getSearchData(mCurrentPage, key);

        if(CommonUtils.isNetworkConnected()){
            showLoadingView();
        }
    }

    private void initToolbar() {
        key = getIntent().getStringExtra(MyConstant.SEARCH_PARAMS);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        StatusBarUtils.setStatusColor(getWindow(), ContextCompat.getColor(mContext,R.color.search_status_bar_white),1f);
        StatusBarUtils.setStatusDarkColor(getWindow());
        if(!TextUtils.isEmpty(key)){
            tvToolbarTitle.setText(key);
        }
        tvToolbarTitle.setTextColor(ContextCompat.getColor(mContext,R.color.title_black));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_grey_24dp);
        toolbar.setBackgroundColor(ContextCompat.getColor(mContext,R.color.white));
        toolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        List<FeedArticleData> dataList = new ArrayList<>();
        mAdapter = new HomePagerAdapter(R.layout.item_home_pager, dataList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void showSearchData(BaseResponse<SearchData> dataBaseResponse) {
        if (dataBaseResponse == null || dataBaseResponse.getData() == null) {
            showSearchDataFailed();
            return;
        }

        if(dataBaseResponse.getData().getDatas().size() > 0){
            if(isRefresh){
                 mAdapter.replaceData(dataBaseResponse.getData().getDatas());
            }else {
                mAdapter.addData(dataBaseResponse.getData().getDatas());
            }
        }else {
            CommonUtils.showSnackMessage(this,getString(R.string.load_more_no_data));
        }


        showNormalView();
    }

    @Override
    public void showSearchDataFailed() {
        showErrorView();
        CommonUtils.showSnackMessage(mActivity, getString(R.string.failed_to_obtain_search_data_list));
    }

    public static void start(Context mContext,String key){
        Intent intent = new Intent(mContext, SearchListActivity.class);
        intent.putExtra(MyConstant.SEARCH_PARAMS,key);
        mContext.startActivity(intent);
    }

    private void setRefresh(){
        smart.setOnRefreshListener(refreshLayout -> {
            isRefresh = true;
            mCurrentPage = 0;
            mPresenter.getSearchData(mCurrentPage,key);
            refreshLayout.finishRefresh(1000);
        });

        smart.setOnLoadMoreListener(refreshLayout -> {
            isRefresh = false;
            mCurrentPage++;
            mPresenter.getSearchData(mCurrentPage,key);
            refreshLayout.finishLoadMore(1000);
        });
    }

    @Override
    public void reload() {
        smart.autoRefresh();
    }
}
