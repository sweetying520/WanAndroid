package com.dream.wanandroid.model.http;

import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.hierarchy.HierarchyData;
import com.dream.wanandroid.model.bean.main.banner.BannerData;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleListData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/5/2.
 */

public interface HttpHelper {

    /**
     * 获取feed文章列表
     * @param page 页数
     * @return feed文章列表数据
     */
    Observable<BaseResponse<FeedArticleListData>> getFeedArticleList(int page);


    Observable<BaseResponse<FeedArticleListData>> addCollectArticle(int id);

    /**
     * 获取首页Banner数据
     * @return
     */
    Observable<BaseResponse<List<BannerData>>> getBannerData();

    /**
     * 获取知识体系
     */
    Observable<BaseResponse<List<HierarchyData>>> getHierarchyData();

}
