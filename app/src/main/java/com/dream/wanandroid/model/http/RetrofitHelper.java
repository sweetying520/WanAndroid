package com.dream.wanandroid.model.http;

import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.hierarchy.HierarchyData;
import com.dream.wanandroid.model.bean.main.banner.BannerData;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleListData;
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
}
