package com.dream.wanandroid.contract.main;

import com.dream.wanandroid.base.presenter.IBasePresenter;
import com.dream.wanandroid.base.view.IBaseView;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.main.often.OftenUseData;

import java.util.List;

/**
 * Created by Administrator on 2018/5/4.
 */

public interface UsageDialogContract {
    interface View extends IBaseView{
        void showUsageData(BaseResponse<List<OftenUseData>> listBaseResponse);

        void showUsageDataFailed();
    }

    interface Presenter extends IBasePresenter<View>{
        void getUsageData();
    }
}
