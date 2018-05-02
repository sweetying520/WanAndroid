package com.dream.wanandroid.model.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.dream.wanandroid.WanAndroidApp;
import com.dream.wanandroid.common.MyConstant;

import javax.inject.Inject;



/**
 * @author quchao
 * @date 2017/11/27
 */

public class PreferenceHelperImpl implements PreferenceHelper {

    private static final String MY_SHARED_PREFERENCE = "my_shared_preference";
    private final SharedPreferences mPreferences;

    @Inject
    PreferenceHelperImpl() {
        mPreferences = WanAndroidApp.getInstance().getSharedPreferences(MY_SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    @Override
    public void setLoginAccount(String account) {
        mPreferences.edit().putString(MyConstant.ACCOUNT, account).apply();
    }

    @Override
    public void setLoginPassword(String password) {
        mPreferences.edit().putString(MyConstant.PASSWORD, password).apply();
    }

    @Override
    public String getLoginAccount() {
        return mPreferences.getString(MyConstant.ACCOUNT, "");
    }

    @Override
    public String getLoginPassword() {
        return mPreferences.getString(MyConstant.PASSWORD, "");
    }

    @Override
    public void setLoginStatus(boolean isLogin) {
        mPreferences.edit().putBoolean(MyConstant.LOGIN_STATUS, isLogin).apply();
    }

    @Override
    public boolean getLoginStatus() {
        return mPreferences.getBoolean(MyConstant.LOGIN_STATUS, false);
    }

    @Override
    public void setCookie(String domain, String cookie) {
        mPreferences.edit().putString(domain, cookie).apply();
    }

    @Override
    public String getCookie(String domain) {
        return mPreferences.getString(MyConstant.COOKIE, "");
    }

    @Override
    public void setCurrentPage(int position) {
        mPreferences.edit().putInt(MyConstant.CURRENT_PAGE, position).apply();
    }

    @Override
    public int getCurrentPage() {
        return mPreferences.getInt(MyConstant.CURRENT_PAGE, 0);
    }

    @Override
    public void setProjectCurrentPage(int position) {
        mPreferences.edit().putInt(MyConstant.PROJECT_CURRENT_PAGE, position).apply();
    }

    @Override
    public int getProjectCurrentPage() {
        return mPreferences.getInt(MyConstant.PROJECT_CURRENT_PAGE, 0);
    }

    @Override
    public boolean getAutoCacheState() {
        return mPreferences.getBoolean(MyConstant.AUTO_CACHE_STATE, true);
    }

    @Override
    public boolean getNoImageState() {
        return mPreferences.getBoolean(MyConstant.NO_IMAGE_STATE, false);
    }

    @Override
    public boolean getNightModeState() {
        return mPreferences.getBoolean(MyConstant.NIGHT_MODE_STATE, false);
    }

    @Override
    public void setNightModeState(boolean b) {
        mPreferences.edit().putBoolean(MyConstant.NIGHT_MODE_STATE, b).apply();
    }

    @Override
    public void setNoImageState(boolean b) {
        mPreferences.edit().putBoolean(MyConstant.NO_IMAGE_STATE, b).apply();
    }

    @Override
    public void setAutoCacheState(boolean b) {
        mPreferences.edit().putBoolean(MyConstant.AUTO_CACHE_STATE, b).apply();
    }




}
