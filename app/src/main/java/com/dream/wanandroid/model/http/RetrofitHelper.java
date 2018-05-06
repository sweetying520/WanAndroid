package com.dream.wanandroid.model.http;

import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.hierarchy.HierarchyData;
import com.dream.wanandroid.model.bean.main.banner.BannerData;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleListData;
import com.dream.wanandroid.model.bean.main.often.OftenUseData;
import com.dream.wanandroid.model.bean.main.search.HotSearchData;
import com.dream.wanandroid.model.bean.main.search.SearchData;
import com.dream.wanandroid.model.bean.navigation.NavigationData;
import com.dream.wanandroid.model.bean.project.ProjectListData;
import com.dream.wanandroid.model.bean.project.ProjectTabData;
import com.dream.wanandroid.model.http.api.WanAndroidApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/5/2.
 */

public class RetrofitHelper implements HttpHelper{

    private WanAndroidApi mWanAndroidApi;

    @Inject
    RetrofitHelper(WanAndroidApi wanAndroidApi){
        mWanAndroidApi = wanAndroidApi;
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getFeedArticleList(int page) {
        return mWanAndroidApi.getFeedArticleList(page);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> addCollectArticle(int id) {
        return mWanAndroidApi.addCollectArticle(id);
    }

    @Override
    public Observable<BaseResponse<List<BannerData>>> getBannerData() {
        return mWanAndroidApi.getBannerData();
    }

    @Override
    public Observable<BaseResponse<List<HierarchyData>>> getHierarchyData() {
        return mWanAndroidApi.getHierarchyData();
    }

    @Override
    public Observable<BaseResponse<List<NavigationData>>> getNavigationData() {
        return mWanAndroidApi.getNavigationData();
    }

    @Override
    public Observable<BaseResponse<List<ProjectTabData>>> getProjectTabData() {
        return mWanAndroidApi.getProjectTabData();
    }

    @Override
    public Observable<BaseResponse<ProjectListData>> getProjecListData(int page, int cid) {
        return mWanAndroidApi.getProjecListData(page,cid);
    }

    @Override
    public Observable<BaseResponse<List<HotSearchData>>> getHotSearchData() {
        return mWanAndroidApi.getHotSearchData();
    }

    @Override
    public Observable<BaseResponse<SearchData>> getSearchData(int page, String key) {
        return mWanAndroidApi.getSearchData(page,key);
    }

    @Override
    public Observable<BaseResponse<List<OftenUseData>>> getOftenUseData() {
        return mWanAndroidApi.getOftenUseData();
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getCollectList(int page) {
        return mWanAndroidApi.getCollectList(page);
    }
}
