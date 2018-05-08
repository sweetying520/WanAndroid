package com.dream.wanandroid.presenter.main;

import com.dream.wanandroid.base.presenter.BasePresenter;
import com.dream.wanandroid.contract.main.SearchDialogContract;
import com.dream.wanandroid.model.DataManager;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.main.search.HotSearchData;
import com.dream.wanandroid.model.dao.HistoryData;
import com.dream.wanandroid.utils.RxUtils;
import com.dream.wanandroid.widget.BaseObserver;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by Administrator on 2018/5/4.
 */

public class SearchDialogPresenter extends BasePresenter<SearchDialogContract.View> implements SearchDialogContract.Presenter {

    @Inject
    SearchDialogPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void getHotSearchData() {
        addSubscribe(mDataManager.getHotSearchData()
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver<BaseResponse<List<HotSearchData>>>(mView) {

                    @Override
                    public void onNext(BaseResponse<List<HotSearchData>> listBaseResponse) {
                        if (listBaseResponse.getErrorCode() == BaseResponse.SUCCESS) {
                            mView.showHotSearchData(listBaseResponse);
                        } else {
                            mView.showHotSearchDataFailed();
                        }
                    }
                }));
    }

    @Override
    public void addHistoryData(String data) {
        addSubscribe(Observable.create((ObservableOnSubscribe<List<HistoryData>>) emitter ->
                {
                    mDataManager.addHistoryData(data);
                    emitter.onNext(mDataManager.getHistoryData());
                }
        ).compose(RxUtils.rxSchedulerHelper()).subscribe(historyDataList ->
                mView.jumpToSearchListActivity()
        ));
    }

    @Override
    public void clearHistoryData() {
        mDataManager.clearHistoryData();
    }

    @Override
    public List<HistoryData> getHistoryData() {
        return mDataManager.getHistoryData();
    }
}
