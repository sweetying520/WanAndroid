package com.dream.wanandroid.presenter.main;

import com.dream.wanandroid.base.presenter.BasePresenter;
import com.dream.wanandroid.contract.main.UsageDialogContract;
import com.dream.wanandroid.model.DataManager;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.main.often.OftenUseData;
import com.dream.wanandroid.utils.RxUtils;
import com.dream.wanandroid.widget.BaseObserver;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/4.
 */

public class UsageDialogPresenter extends BasePresenter<UsageDialogContract.View> implements UsageDialogContract.Presenter{


    @Inject
    UsageDialogPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getUsageData() {
        addSubscribe(mDataManager.getOftenUseData()
        .compose(RxUtils.rxSchedulerHelper())
        .subscribeWith(new BaseObserver<BaseResponse<List<OftenUseData>>>(mView){

            @Override
            public void onNext(BaseResponse<List<OftenUseData>> listBaseResponse) {
                if(listBaseResponse.getErrorCode() == BaseResponse.SUCCESS){
                    mView.showUsageData(listBaseResponse);
                }else {
                    mView.showUsageDataFailed();
                }
            }
        }));
    }
}
