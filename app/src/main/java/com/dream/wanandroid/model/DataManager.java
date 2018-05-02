package com.dream.wanandroid.model;

import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.main.banner.BannerData;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleListData;
import com.dream.wanandroid.model.http.HttpHelper;
import com.dream.wanandroid.model.prefs.PreferenceHelper;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/4/27.
 */

public class DataManager implements HttpHelper,PreferenceHelper{

    private HttpHelper mHttpHelper;
    private PreferenceHelper mPreferenceHelper;


    public DataManager(HttpHelper httpHelper, PreferenceHelper preferencesHelper) {
        mHttpHelper = httpHelper;
        mPreferenceHelper = preferencesHelper;
    }


    @Override
    public void setLoginAccount(String account) {

    }

    @Override
    public void setLoginPassword(String password) {

    }

    @Override
    public String getLoginAccount() {
        return null;
    }

    @Override
    public String getLoginPassword() {
        return null;
    }

    @Override
    public void setLoginStatus(boolean isLogin) {

    }

    @Override
    public boolean getLoginStatus() {
        return false;
    }

    @Override
    public void setCookie(String domain, String cookie) {

    }

    @Override
    public String getCookie(String domain) {
        return null;
    }

    @Override
    public void setCurrentPage(int position) {

    }

    @Override
    public int getCurrentPage() {
        return 0;
    }

    @Override
    public void setProjectCurrentPage(int position) {

    }

    @Override
    public int getProjectCurrentPage() {
        return 0;
    }

    @Override
    public boolean getAutoCacheState() {
        return false;
    }

    @Override
    public boolean getNoImageState() {
        return false;
    }

    @Override
    public boolean getNightModeState() {
        return false;
    }

    @Override
    public void setNightModeState(boolean b) {

    }

    @Override
    public void setNoImageState(boolean b) {

    }

    @Override
    public void setAutoCacheState(boolean b) {

    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getFeedArticleList(int page) {
        return mHttpHelper.getFeedArticleList(page);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> addCollectArticle(int id) {
        return mHttpHelper.addCollectArticle(id);
    }

    @Override
    public Observable<BaseResponse<List<BannerData>>> getBannerData() {
        return mHttpHelper.getBannerData();
    }
}
