package com.dream.wanandroid.contract.like;

import com.dream.wanandroid.base.presenter.IBasePresenter;
import com.dream.wanandroid.base.view.IBaseView;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleListData;

/**LikeContract
 * Created by Administrator on 2018/5/6.
 */

public interface LikeContract {

    interface View extends IBaseView{
        void showCollectList(BaseResponse<FeedArticleListData> listDataBaseResponse);

        void showCollectListFailed();
    }

    interface Presenter extends IBasePresenter<View>{
        void  getCollectList(int page);
    }
}
