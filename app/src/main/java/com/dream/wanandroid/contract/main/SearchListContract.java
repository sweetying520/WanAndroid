package com.dream.wanandroid.contract.main;

import com.dream.wanandroid.base.presenter.IBasePresenter;
import com.dream.wanandroid.base.view.IBaseView;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.main.search.SearchData;

/**
 * Created by Administrator on 2018/5/8.
 */

public interface SearchListContract {
    interface View extends IBaseView{

        void showSearchData(BaseResponse<SearchData> dataBaseResponse);

        void showSearchDataFailed();


    }

    interface Presenter extends IBasePresenter<View>{

        void getSearchData(int page,String key);
    }
}
