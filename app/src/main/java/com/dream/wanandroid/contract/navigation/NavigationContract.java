package com.dream.wanandroid.contract.navigation;

import com.dream.wanandroid.base.presenter.IBasePresenter;
import com.dream.wanandroid.base.view.IBaseView;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.navigation.NavigationData;

import java.util.List;

/**
 * Created by Administrator on 2018/5/2.
 */

public interface NavigationContract {

    interface View extends IBaseView{
        void showNavigationListData(BaseResponse<List<NavigationData>> listBaseResponse);

        void showNavigationListDataFailed();
    }

    interface Presenter extends IBasePresenter<View>{
        void getNavigationListData();
    }
}
