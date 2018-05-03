package com.dream.wanandroid.presenter.hierarchy;

import com.dream.wanandroid.base.presenter.BasePresenter;
import com.dream.wanandroid.contract.hierarchy.KnowledgeHierarchyContract;
import com.dream.wanandroid.model.DataManager;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.hierarchy.HierarchyData;
import com.dream.wanandroid.utils.RxUtils;
import com.dream.wanandroid.widget.BaseObserver;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/2.
 */

public class KnowledgeHierarchyPresenter extends BasePresenter<KnowledgeHierarchyContract.View> implements KnowledgeHierarchyContract.Presenter{


    private DataManager dataManager;

    @Inject
     KnowledgeHierarchyPresenter(DataManager dataManager) {
        super(dataManager);
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(KnowledgeHierarchyContract.View mView) {
        super.attachView(mView);
    }



    @Override
    public void getHierarchyData() {
        addSubscribe(dataManager.getHierarchyData()
        .compose(RxUtils.rxSchedulerHelper())
        .filter(listBaseResponse -> mView != null)
        .subscribeWith(new BaseObserver<BaseResponse<List<HierarchyData>>>(mView){

            @Override
            public void onNext(BaseResponse<List<HierarchyData>> listBaseResponse) {
                if(listBaseResponse.getErrorCode() == BaseResponse.SUCCESS){
                    mView.showHierarchyDataList(listBaseResponse);
                }else {
                    mView.showHierarchyDataListFailed();
                }
            }
        }));
    }


}
