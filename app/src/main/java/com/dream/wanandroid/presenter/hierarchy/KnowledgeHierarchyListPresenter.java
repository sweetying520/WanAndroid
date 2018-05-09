package com.dream.wanandroid.presenter.hierarchy;

import com.dream.wanandroid.base.presenter.BasePresenter;
import com.dream.wanandroid.contract.hierarchy.KnowledgeHierarchyListContract;
import com.dream.wanandroid.model.DataManager;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleListData;
import com.dream.wanandroid.model.event.JumpToTopEvent;
import com.dream.wanandroid.utils.RxBus;
import com.dream.wanandroid.utils.RxUtils;
import com.dream.wanandroid.widget.BaseObserver;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/9.
 */

public class KnowledgeHierarchyListPresenter extends BasePresenter<KnowledgeHierarchyListContract.View> implements KnowledgeHierarchyListContract.Presenter {


    @Inject
    KnowledgeHierarchyListPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void attachView(KnowledgeHierarchyListContract.View mView) {
        super.attachView(mView);
        registerEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(JumpToTopEvent.class)
        .subscribe(jumpToTopEvent -> mView.jumpToTop()));
    }

    @Override
    public void getHierarchyListData(int page, int cid) {
        addSubscribe(mDataManager.getHierarchyListData(page,cid)
        .compose(RxUtils.rxSchedulerHelper())
        .subscribeWith(new BaseObserver<BaseResponse<FeedArticleListData>>(mView){

            @Override
            public void onNext(BaseResponse<FeedArticleListData> feedArticleListDataBaseResponse) {
                if(feedArticleListDataBaseResponse.getErrorCode() == BaseResponse.SUCCESS){
                    mView.showHierarchyListData(feedArticleListDataBaseResponse);
                }else {
                    mView.showHierarchyListDataFailed();
                }
            }
        }));
    }
}
