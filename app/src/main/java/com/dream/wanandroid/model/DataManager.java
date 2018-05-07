package com.dream.wanandroid.model;

import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.hierarchy.HierarchyData;
import com.dream.wanandroid.model.bean.main.banner.BannerData;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleListData;
import com.dream.wanandroid.model.bean.main.login.LoginData;
import com.dream.wanandroid.model.bean.main.often.OftenUseData;
import com.dream.wanandroid.model.bean.main.search.HotSearchData;
import com.dream.wanandroid.model.bean.main.search.SearchData;
import com.dream.wanandroid.model.bean.navigation.NavigationData;
import com.dream.wanandroid.model.bean.project.ProjectListData;
import com.dream.wanandroid.model.bean.project.ProjectTabData;
import com.dream.wanandroid.model.http.HttpHelper;
import com.dream.wanandroid.model.prefs.PreferenceHelper;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**DataManager
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
    public void setCurrentPage(int position) {
        mPreferenceHelper.setCurrentPage(position);
    }

    @Override
    public int getCurrentPage() {
        return mPreferenceHelper.getCurrentPage();
    }

    @Override
    public void setLoginUsername(String username) {
        mPreferenceHelper.setLoginUsername(username);
    }

    @Override
    public void setLoginPassword(String password) {
        mPreferenceHelper.setLoginPassword(password);
    }

    @Override
    public String getLoginUsername() {
        return mPreferenceHelper.getLoginUsername();
    }

    @Override
    public String getLoginPassword() {
        return mPreferenceHelper.getLoginPassword();
    }

    @Override
    public void setLoginStatus(boolean isLogin) {
        mPreferenceHelper.setLoginStatus(isLogin);
    }

    @Override
    public boolean getLoginStatus() {
        return mPreferenceHelper.getLoginStatus();
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

    @Override
    public Observable<BaseResponse<List<HierarchyData>>> getHierarchyData() {
        return mHttpHelper.getHierarchyData();
    }

    @Override
    public Observable<BaseResponse<List<NavigationData>>> getNavigationData() {
        return mHttpHelper.getNavigationData();
    }

    @Override
    public Observable<BaseResponse<List<ProjectTabData>>> getProjectTabData() {
        return mHttpHelper.getProjectTabData();
    }

    @Override
    public Observable<BaseResponse<ProjectListData>> getProjecListData(int page, int cid) {
        return mHttpHelper.getProjecListData(page,cid);
    }

    @Override
    public Observable<BaseResponse<List<HotSearchData>>> getHotSearchData() {
        return mHttpHelper.getHotSearchData();
    }

    @Override
    public Observable<BaseResponse<SearchData>> getSearchData(int page, String key) {
        return mHttpHelper.getSearchData(page,key);
    }

    @Override
    public Observable<BaseResponse<List<OftenUseData>>> getOftenUseData() {
        return mHttpHelper.getOftenUseData();
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getCollectList(int page) {
        return mHttpHelper.getCollectList(page);
    }

    @Override
    public Observable<BaseResponse<LoginData>> getLoginData(Map<String, String> loginParams) {
        return mHttpHelper.getLoginData(loginParams);
    }

    @Override
    public Observable<BaseResponse<LoginData>> getRegisterData(Map<String, String> registerParams) {
        return mHttpHelper.getRegisterData(registerParams);
    }
}
