package com.dream.wanandroid.presenter.like;

import com.dream.wanandroid.base.presenter.BasePresenter;
import com.dream.wanandroid.contract.like.LikeContract;
import com.dream.wanandroid.model.DataManager;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleListData;
import com.dream.wanandroid.utils.RxUtils;
import com.dream.wanandroid.widget.BaseObserver;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/6.
 */

public class LikePresenter extends BasePresenter<LikeContract.View> implements LikeContract.Presenter{

    @Inject
     LikePresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void getCollectList(int page) {
        addSubscribe(mDataManager.getCollectList(page)
        .compose(RxUtils.rxSchedulerHelper())
        .subscribeWith(new BaseObserver<BaseResponse<FeedArticleListData>>(mView){

            @Override
            public void onNext(BaseResponse<FeedArticleListData> feedArticleListDataBaseResponse) {
                if(feedArticleListDataBaseResponse.getErrorCode() == BaseResponse.SUCCESS){
                    mView.showCollectList(feedArticleListDataBaseResponse);
                }else {
                    mView.showCollectListFailed();
                }
            }
        }));
    }
}
