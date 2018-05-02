package com.dream.wanandroid.ui.mainpager.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.dream.wanandroid.R;
import com.dream.wanandroid.base.fragment.AbstractRootFragment;
import com.dream.wanandroid.common.MyConstant;
import com.dream.wanandroid.contract.mainpager.HomePagerContract;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.main.banner.BannerData;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleData;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleListData;
import com.dream.wanandroid.presenter.mainpager.HomePagerPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/5/2.
 */

public class HomePagerFragment extends AbstractRootFragment<HomePagerPresenter> implements HomePagerContract.View {


    @BindView(R.id.home_pager_rv)
    RecyclerView homePagerRv;
    @BindView(R.id.normal_view)
    SmartRefreshLayout normalView;



    public static HomePagerFragment getInstance(boolean params1,String params2){
        HomePagerFragment fragment = new HomePagerFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(MyConstant.ARG_PARAM1,params1);
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
        return R.layout.fragment_home_pager;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();

        initRecyclerView();
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {

    }


    @Override
    public void showCollectSucc() {

    }

    @Override
    public void showCancelCollectSucc() {

    }

    @Override
    public void showArticleList(BaseResponse<FeedArticleListData> feedArticleListDataBaseResponse, boolean isRefresh) {

    }

    @Override
    public void showArticleListFail() {

    }

    @Override
    public void showCollectArticleData(int position, FeedArticleData feedArticleData, BaseResponse<FeedArticleListData> feedArticleListResponse) {

    }

    @Override
    public void showCancelCollectArticleData(int position, FeedArticleData feedArticleData, BaseResponse<FeedArticleListData> feedArticleListResponse) {

    }

    @Override
    public void showBannerData(BaseResponse<List<BannerData>> bannerResponse) {

    }

    @Override
    public void showBannerDataFail() {

    }


    @Override
    public void showErrorMsg(String errorMsg) {

    }
}
