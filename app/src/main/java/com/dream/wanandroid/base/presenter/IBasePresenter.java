package com.dream.wanandroid.base.presenter;

import com.dream.wanandroid.base.view.IBaseView;

import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/4/27.
 */

public interface IBasePresenter<V extends IBaseView> {

    /**
     * 绑定View
     * @param mView
     */
    void attachView(V mView);

    /**
     * 解除View
     */
    void detachView();

    void addRxBindingSubscribe(Disposable disposable);

    int getCurrentPage();


}
