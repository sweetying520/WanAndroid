package com.dream.wanandroid.ui.mainpager.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.dream.wanandroid.R;
import com.dream.wanandroid.base.fragment.AbstractRootFragment;
import com.dream.wanandroid.common.MyConstant;
import com.dream.wanandroid.contract.mainpager.HomePagerContract;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.main.banner.BannerData;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleData;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleListData;
import com.dream.wanandroid.presenter.mainpager.HomePagerPresenter;
import com.dream.wanandroid.ui.mainpager.adapter.HomePagerAdapter;
import com.dream.wanandroid.utils.CommonUtils;
import com.dream.wanandroid.utils.GlideImageLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * home
 * Created by Administrator on 2018/5/2.
 */

public class HomePagerFragment extends AbstractRootFragment<HomePagerPresenter> implements HomePagerContract.View {


    @BindView(R.id.home_pager_rv)
    RecyclerView homePagerRv;
    @BindView(R.id.normal_view)
    SmartRefreshLayout smartRefreshLayout;

    HomePagerAdapter mHomePagerAdapter;
    private List<FeedArticleData> dataList;
    private Banner mBanner;


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

        setRefresh();
        mPresenter.autoRefresh();

        if(CommonUtils.isNetworkConnected()){
            showLoadingView();
        }

    }

    @Override
    public void reload() {
        if(CommonUtils.isNetworkConnected()){
           smartRefreshLayout.autoRefresh();
        }
    }

    private void setRefresh() {
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.autoRefresh();
            refreshLayout.finishRefresh(1000);
        });

        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPresenter.loadMore();
            refreshLayout.finishLoadMore(1000);
        });
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        dataList = new ArrayList<>();
        mHomePagerAdapter = new HomePagerAdapter(R.layout.item_home_pager, dataList);


        View headView = LayoutInflater.from(_mActivity).inflate(R.layout.item_home_banner,null);
        mBanner = headView.findViewById(R.id.banner);
        mHomePagerAdapter.addHeaderView(headView);
        mHomePagerAdapter.setOnItemClickListener((adapter, view, position) -> {
            CommonUtils.showMessage(_mActivity,"项目");
        });

        mHomePagerAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()){
                case R.id.tv_subject:
                    CommonUtils.showMessage(_mActivity,"tv_subject");
                    break;
                case R.id.iv_like:
                    CommonUtils.showMessage(_mActivity,"iv_like");
                    break;
                case R.id.tv_is_project:
                    CommonUtils.showMessage(_mActivity,"tv_is_project");
                    break;
            }
        });
        homePagerRv.setLayoutManager(new LinearLayoutManager(_mActivity));
        homePagerRv.setAdapter(mHomePagerAdapter);
    }


    @Override
    public void showCollectSucc() {

    }

    @Override
    public void showCancelCollectSucc() {

    }

    @Override
    public void showArticleList(BaseResponse<FeedArticleListData> articleData, boolean isRefresh) {
        if(articleData == null || articleData.getData() == null || articleData.getData().getDatas() == null){
            showArticleListFail();
            return;
        }

        homePagerRv.setVisibility(View.VISIBLE);
        if(isRefresh){
            dataList = articleData.getData().getDatas();
            mHomePagerAdapter.replaceData(articleData.getData().getDatas());
        }else {
            dataList.addAll(articleData.getData().getDatas());
            mHomePagerAdapter.addData(articleData.getData().getDatas());
        }
        showNormalView();
    }

    @Override
    public void showArticleListFail() {
        CommonUtils.showSnackMessage(_mActivity,getString(R.string.failed_to_obtain_article_list));
    }

    @Override
    public void showCollectArticleData(int position, FeedArticleData feedArticleData, BaseResponse<FeedArticleListData> feedArticleListResponse) {

    }

    @Override
    public void showCancelCollectArticleData(int position, FeedArticleData feedArticleData, BaseResponse<FeedArticleListData> feedArticleListResponse) {

    }

    @Override
    public void showBannerData(BaseResponse<List<BannerData>> bannerResponse) {
        if(bannerResponse == null || bannerResponse.getData() == null){
            return;
        }

        List<String> imgList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        for (BannerData bannerData : bannerResponse.getData()) {
            imgList.add(bannerData.getImagePath());
            titleList.add(bannerData.getTitle());
        }
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(imgList);
        //设置标题集合
        mBanner.setBannerTitles(titleList);
        //设置动画
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(imgList.size() * 400);
        //设置指示器的位置
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.start();


    }

    public void jumpToTop(){
        if(homePagerRv != null){
            homePagerRv.smoothScrollToPosition(0);
        }
    }

    @Override
    public void showBannerDataFail() {
        CommonUtils.showSnackMessage(_mActivity,getString(R.string.failed_to_obtain_banner_data));
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mBanner != null){
            mBanner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mBanner != null){
            mBanner.stopAutoPlay();
        }
    }
}
