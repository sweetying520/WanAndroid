package com.dream.wanandroid.presenter.main;

import com.dream.wanandroid.base.presenter.BasePresenter;
import com.dream.wanandroid.contract.main.SearchListContract;
import com.dream.wanandroid.model.DataManager;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.main.search.SearchData;
import com.dream.wanandroid.utils.RxUtils;
import com.dream.wanandroid.widget.BaseObserver;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/8.
 */

public class SearchListPresenter extends BasePresenter<SearchListContract.View> implements SearchListContract.Presenter {


    @Inject
    SearchListPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void getSearchData(int page, String key) {
        addSubscribe(mDataManager.getSearchData(page, key)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver<BaseResponse<SearchData>>(mView) {

                    @Override
                    public void onNext(BaseResponse<SearchData> dataBaseResponse) {
                        if(dataBaseResponse.getErrorCode() == BaseResponse.SUCCESS){
                            mView.showSearchData(dataBaseResponse);
                        }else {
                            mView.showSearchDataFailed();
                        }
                    }
                }));
    }
}
