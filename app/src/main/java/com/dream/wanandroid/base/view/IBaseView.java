package com.dream.wanandroid.base.view;

/**
 * Created by Administrator on 2018/4/27.
 */

public interface IBaseView {

    /**
     * showErrorView
     */
    void showErrorView();

    /**
     * showLoadingView
     */
    void showLoadingView();

    /**
     * showNormalView
     */
    void showNormalView();

    /**
     * reload
     */
    void reload();

    /**
     * showLoginView
     */
    void showLoginView();

    /**
     * showLogoutView
     */
    void showLogoutView();

    /**
     * Show error message
     *
     * @param errorMsg error message
     */
    void showErrorMsg(String errorMsg);

    void showError();

    void showCollectFail();
}
