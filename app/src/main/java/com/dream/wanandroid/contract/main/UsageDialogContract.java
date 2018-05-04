package com.dream.wanandroid.contract.main;

import com.dream.wanandroid.base.presenter.IBasePresenter;
import com.dream.wanandroid.base.view.IBaseView;

/**
 * Created by Administrator on 2018/5/4.
 */

public interface UsageDialogContract {
    interface View extends IBaseView{
//
//        void showHotSearchData(BaseResponse<List<HotSearchData>> listBaseResponse);
//
//        void showHotSearchDataFailed();
    }

    interface Presenter extends IBasePresenter<View>{
        //void getHotSearchData();
    }
}
