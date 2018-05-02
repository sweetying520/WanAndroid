package com.dream.wanandroid.contract.mainpager;

import com.dream.wanandroid.base.presenter.IBasePresenter;
import com.dream.wanandroid.base.view.IBaseView;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.main.banner.BannerData;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleData;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleListData;

import java.util.List;

/**
 * Created by Administrator on 2018/5/2.
 */

public interface HomePagerContract {
    interface View extends IBaseView{
        void showCollectSucc();

        void showCancelCollectSucc();


        void showArticleList(BaseResponse<FeedArticleListData> feedArticleListDataBaseResponse,boolean isRefresh);

        void showArticleListFail();

        /**
         * Show collect article data
         *
         * @param position Position
         * @param feedArticleData FeedArticleData
         * @param feedArticleListResponse BaseResponse<FeedArticleListData>
         */
        void showCollectArticleData(int position, FeedArticleData feedArticleData, BaseResponse<FeedArticleListData> feedArticleListResponse);

        /**
         * Show cancel collect article data
         *
         * @param position Position
         * @param feedArticleData FeedArticleData
         * @param feedArticleListResponse BaseResponse<FeedArticleListData>
         */
        void showCancelCollectArticleData(int position, FeedArticleData feedArticleData, BaseResponse<FeedArticleListData> feedArticleListResponse);


        /**
         * Show banner data
         *
         * @param bannerResponse BaseResponse<List<BannerData>>
         */
        void showBannerData(BaseResponse<List<BannerData>> bannerResponse);



        /**
         * Show banner data fail
         */
        void showBannerDataFail();
    }

    interface Presenter extends IBasePresenter<View>{
        /**
         * Get Login password
         * @return login password
         */
        String getLoginPassword();

        /**
         * Load main pager data
         */
        void loadMainPagerData();

        /**
         * Get feed article list
         */
        void getFeedArticleList();

        /**
         * Get banner data
         */
        void getBannerData();

        /**
         * Auto refresh
         */
        void autoRefresh();

        /**
         * Load more
         */
        void loadMore();

        void addCollectArticle(int position, FeedArticleData feedArticleData);
    }
}
