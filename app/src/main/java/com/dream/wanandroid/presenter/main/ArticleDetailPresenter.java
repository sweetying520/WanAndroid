package com.dream.wanandroid.presenter.main;

import com.dream.wanandroid.base.presenter.BasePresenter;
import com.dream.wanandroid.contract.main.ArticleDetailContract;
import com.dream.wanandroid.model.DataManager;

import javax.inject.Inject;

/**ArticleDetailPresenter
 * Created by Administrator on 2018/5/8.
 */

public class ArticleDetailPresenter extends BasePresenter<ArticleDetailContract.View> implements ArticleDetailContract.Presenter {

    @Inject
    ArticleDetailPresenter(DataManager dataManager) {
        super(dataManager);
    }


}
