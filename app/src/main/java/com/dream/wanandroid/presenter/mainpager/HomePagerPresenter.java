package com.dream.wanandroid.presenter.mainpager;

import com.dream.wanandroid.base.presenter.BasePresenter;
import com.dream.wanandroid.contract.mainpager.HomePagerContract;
import com.dream.wanandroid.model.DataManager;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.main.banner.BannerData;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleData;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleListData;
import com.dream.wanandroid.model.event.CollectEvent;
import com.dream.wanandroid.model.event.LoginEvent;
import com.dream.wanandroid.utils.RxBus;
import com.dream.wanandroid.utils.RxUtils;
import com.dream.wanandroid.widget.BaseObserver;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/2.
 */

public class HomePagerPresenter extends BasePresenter<HomePagerContract.View> implements HomePagerContract.Presenter {

    private DataManager mDataManager;
    private boolean isRefresh = true;
    private int mCurrentPage;

    @Inject
    HomePagerPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(HomePagerContract.View mView) {
        super.attachView(mView);
        registerEvent();
    }

    /**
     * 注册事件
     */
    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(CollectEvent.class)
                .filter(collectEvent -> !collectEvent.isCancelCollectSuccess())
                .subscribe(collectEvent -> mView.showCollectSucc()));

        addSubscribe(RxBus.getDefault().toFlowable(CollectEvent.class)
                .filter(CollectEvent::isCancelCollectSuccess)
                .subscribe(collectEvent -> mView.showCancelCollectSucc()));

        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
                .filter(LoginEvent::isLogin)
                .subscribe(loginEvent -> mView.showLoginView()));

        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
                .filter(loginEvent -> !loginEvent.isLogin())
                .subscribe(loginEvent -> mView.showLogoutView()));
    }

    @Override
    public String getLoginPassword() {
        return null;
    }

    @Override
    public void loadMainPagerData() {

    }

    @Override
    public void getFeedArticleList() {
        addSubscribe(mDataManager.getFeedArticleList(mCurrentPage)
                .compose(RxUtils.rxSchedulerHelper())
                .filter(feedArticleListDataBaseResponse -> mView != null)
                .subscribeWith(new BaseObserver<BaseResponse<FeedArticleListData>>(mView) {

                    @Override
                    public void onNext(BaseResponse<FeedArticleListData> feedArticleListDataBaseResponse) {
                        if(feedArticleListDataBaseResponse.getErrorCode() == BaseResponse.SUCCESS){
                            mView.showArticleList(feedArticleListDataBaseResponse,isRefresh);
                        }else {
                            mView.showArticleListFail();
                        }
                    }
                }));
    }

    @Override
    public void getBannerData() {
        addSubscribe(mDataManager.getBannerData()
        .compose(RxUtils.rxSchedulerHelper())
        .subscribeWith(new BaseObserver<BaseResponse<List<BannerData>>>(mView){

            @Override
            public void onNext(BaseResponse<List<BannerData>> listBaseResponse) {
                if(listBaseResponse.getErrorCode() == BaseResponse.SUCCESS){
                    mView.showBannerData(listBaseResponse);
                }else {
                    mView.showBannerDataFail();
                }
            }
        }));
    }

    @Override
    public void autoRefresh() {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void addCollectArticle(int position, FeedArticleData feedArticleData) {
        addSubscribe(mDataManager.addCollectArticle(feedArticleData.getId())
        .compose(RxUtils.rxSchedulerHelper())
        .subscribeWith(new BaseObserver<BaseResponse<FeedArticleListData>>(mView){

            @Override
            public void onNext(BaseResponse<FeedArticleListData> feedArticleListDataBaseResponse) {
                if(feedArticleListDataBaseResponse.getErrorCode() == BaseResponse.SUCCESS){
                    feedArticleData.setCollect(true);
                    mView.showCollectArticleData(position,feedArticleData,feedArticleListDataBaseResponse);
                }else {
                    mView.showCollectFail();
                }
            }
        }));
    }
}
