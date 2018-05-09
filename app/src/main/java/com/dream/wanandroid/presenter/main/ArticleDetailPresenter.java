package com.dream.wanandroid.presenter.main;

import android.Manifest;

import com.dream.wanandroid.base.presenter.BasePresenter;
import com.dream.wanandroid.contract.main.ArticleDetailContract;
import com.dream.wanandroid.model.DataManager;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

/**
 * ArticleDetailPresenter
 * Created by Administrator on 2018/5/8.
 */

public class ArticleDetailPresenter extends BasePresenter<ArticleDetailContract.View> implements ArticleDetailContract.Presenter {

    @Inject
    ArticleDetailPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void shareWithPermission(RxPermissions rxPermissions) {
        addSubscribe(rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        mView.shareEvent();
                    } else {
                        mView.shareFailed();
                    }
                }));
    }
}
