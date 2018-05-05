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

import java.util.List;

import io.reactivex.Observable;

/**
 * HttpHelper
 * Created by Administrator on 2018/5/2.
 */

public interface HttpHelper {

    /**
     * 获取feed文章列表
     *
     * @param page 页数
     * @return feed文章列表数据
     */
    Observable<BaseResponse<FeedArticleListData>> getFeedArticleList(int page);


    Observable<BaseResponse<FeedArticleListData>> addCollectArticle(int id);

    /**
     * 获取首页Banner数据
     *
     * @return banner
     */
    Observable<BaseResponse<List<BannerData>>> getBannerData();

    /**
     * 获取知识体系
     */
    Observable<BaseResponse<List<HierarchyData>>> getHierarchyData();

    /**
     * 获取
     */
    Observable<BaseResponse<List<NavigationData>>> getNavigationData();

    /**
     * 项目分类
     *
     * @return 项目Tab
     */
    Observable<BaseResponse<List<ProjectTabData>>> getProjectTabData();

    /**
     * 项目列表
     *
     * @param page 页数
     * @param cid  分类id
     * @return 项目列表数据
     */
    Observable<BaseResponse<ProjectListData>> getProjecListData(int page, int cid);

    /**
     * 获取热搜数据
     *
     * @return 热搜数据
     */
    Observable<BaseResponse<List<HotSearchData>>> getHotSearchData();

    /**
     * 获取搜索到的数据列表
     *
     * @param page 页数
     * @param key  关键词
     * @return 搜索的数据列表
     */

    Observable<BaseResponse<SearchData>> getSearchData(int page, String key);

    /**
     * 常用网站数据
     * @return 常用网站
     */
    Observable<BaseResponse<List<OftenUseData>>> getOftenUseData();
}
