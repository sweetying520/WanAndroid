package com.dream.wanandroid.presenter.navigation;

import com.dream.wanandroid.base.presenter.BasePresenter;
import com.dream.wanandroid.contract.navigation.NavigationContract;
import com.dream.wanandroid.model.DataManager;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.navigation.NavigationData;
import com.dream.wanandroid.utils.RxUtils;
import com.dream.wanandroid.widget.BaseObserver;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/2.
 */

public class NavigationPresenter extends BasePresenter<NavigationContract.View> implements NavigationContract.Presenter{

    private DataManager mDataManager;

    @Inject
     NavigationPresenter(DataManager dataManager) {
        super(dataManager);
        mDataManager = dataManager;
    }

    @Override
    public void getNavigationListData() {
        addSubscribe(mDataManager.getNavigationData()
        .compose(RxUtils.rxSchedulerHelper())
        .filter(listBaseResponse -> mView != null)
        .subscribeWith(new BaseObserver<BaseResponse<List<NavigationData>>>(mView){

            @Override
            public void onNext(BaseResponse<List<NavigationData>> listBaseResponse) {
                if(listBaseResponse.getErrorCode() == BaseResponse.SUCCESS){
                    mView.showNavigationListData(listBaseResponse);
                }else {
                    mView.showNavigationListDataFailed();
                }
            }
        }));
    }
}
