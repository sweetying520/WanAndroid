package com.dream.wanandroid.contract.main;

import com.dream.wanandroid.base.presenter.IBasePresenter;
import com.dream.wanandroid.base.view.IBaseView;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.main.search.HotSearchData;
import com.dream.wanandroid.model.dao.HistoryData;

import java.util.List;

/**
 * Created by Administrator on 2018/5/4.
 */

public interface SearchDialogContract {
    interface View extends IBaseView{

        void showHotSearchData(BaseResponse<List<HotSearchData>> listBaseResponse);

        void showHotSearchDataFailed();

        void jumpToSearchListActivity();

        void showHistoryData(List<HistoryData> historyDataList);
    }

    interface Presenter extends IBasePresenter<View>{
        void getHotSearchData();

        /**
         * 增加历史记录
         * @param data 历史记录
         */
        void addHistoryData(String data);

        /**
         * 清楚历史记录
         */
        void clearHistoryData();

        /**
         * 获取历史记录
         * @return 获取历史记录
         */
        List<HistoryData> getHistoryData();


    }
}
