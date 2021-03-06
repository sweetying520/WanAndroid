package com.dream.wanandroid.model.http.api;

import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.hierarchy.HierarchyData;
import com.dream.wanandroid.model.bean.main.banner.BannerData;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleListData;
import com.dream.wanandroid.model.bean.main.login.LoginData;
import com.dream.wanandroid.model.bean.main.often.OftenUseData;
import com.dream.wanandroid.model.bean.main.search.HotSearchData;
import com.dream.wanandroid.model.bean.main.search.SearchData;
import com.dream.wanandroid.model.bean.navigation.NavigationData;
import com.dream.wanandroid.model.bean.project.ProjectListData;
import com.dream.wanandroid.model.bean.project.ProjectTabData;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    /**
     * 知识体系
     * @return 知识体系的数据
     */
    @GET("tree/json")
    Observable<BaseResponse<List<HierarchyData>>> getHierarchyData();

    /**
     *导航数据
     * @return 导航数据
     */
    @GET("navi/json")
    Observable<BaseResponse<List<NavigationData>>> getNavigationData();

    /**
     * 项目tab数据
     * @return 项目tab数据
     */
    @GET("project/tree/json")
    Observable<BaseResponse<List<ProjectTabData>>> getProjectTabData();


    /**
     * 项目列表数据
     * @return 项目列表数据
     */
    @GET("project/list/{page}/json")
    Observable<BaseResponse<ProjectListData>> getProjecListData(@Path("page") int page, @Query("cid") int cid);


    /**
     * 获取热搜数据
     * @return 热搜数据
     */
    @GET("hotkey/json")
    Observable<BaseResponse<List<HotSearchData>>> getHotSearchData();

    /**
     * 获取搜索到的数据列表
     * @param page 页数
     * @param key 关键词
     * @return 搜索的数据列表
     */
    @FormUrlEncoded
    @POST("article/query/{page}/json")
    Observable<BaseResponse<SearchData>> getSearchData(@Path("page") int page, @Field("k") String key);


    /**
     * 常用网站数据
     * @return 常用网站
     */
    @GET("friend/json")
    Observable<BaseResponse<List<OftenUseData>>> getOftenUseData();

    /**
     * 获取收藏列表
     * http://www.wanandroid.com/lg/collect/list/0/json
     *
     * @param page page number
     * @return 收藏列表数据
     */
    @GET("lg/collect/list/{page}/json")
    Observable<BaseResponse<FeedArticleListData>> getCollectList(@Path("page") int page);

    /**
     * 登录
     * @param loginParams loginParams
     * @return 登录后的数据
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseResponse<LoginData>> getLoginData(@FieldMap Map<String,String> loginParams);

    /**
     * 注册
     * @param registerParams registerParams
     * @return 注册
     */
    @FormUrlEncoded
    @POST("user/register")
    Observable<BaseResponse<LoginData>> getRegisterData(@FieldMap Map<String,String> registerParams);

    /**
     *
     * @param page 页数
     * @param cid cid
     * @return 体系三级分类数据
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponse<FeedArticleListData>> getHierarchyListData(@Path("page") int page,@Query("cid") int cid);
}
