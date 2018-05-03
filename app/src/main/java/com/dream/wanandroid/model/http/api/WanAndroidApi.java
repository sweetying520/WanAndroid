package com.dream.wanandroid.model.http.api;

import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.hierarchy.HierarchyData;
import com.dream.wanandroid.model.bean.main.banner.BannerData;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleListData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2018/4/28.
 */

public interface WanAndroidApi {

    String BASE_URL = "http://www.wanandroid.com/";

    /**
     * 获取feed文章列表
     * @param page 页数
     * @return feed文章数据列表
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponse<FeedArticleListData>> getFeedArticleList(@Path("page") int page);

    /**
     *
     * @return 首页Banner数据
     */
    @GET("banner/json")
    Observable<BaseResponse<List<BannerData>>> getBannerData();

    /**
     * 收藏站内文章
     * http://www.wanandroid.com/lg/collect/1165/json
     *
     * @param id article id
     * @return 注册数据
     */
    @POST("lg/collect/{id}/json")
    Observable<BaseResponse<FeedArticleListData>> addCollectArticle(@Path("id") int id);

    @GET("tree/json")
    Observable<BaseResponse<List<HierarchyData>>> getHierarchyData();



}
