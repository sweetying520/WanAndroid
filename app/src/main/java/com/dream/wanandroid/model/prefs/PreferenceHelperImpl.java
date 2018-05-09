package com.dream.wanandroid.model.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.dream.wanandroid.WanAndroidApp;
import com.dream.wanandroid.common.MyConstant;

import javax.inject.Inject;


/**
 * PreferenceHelperImpl
 */

public class PreferenceHelperImpl implements PreferenceHelper {

    private static final String MY_SHARED_PREFERENCE = "my_shared_preference";
    private final SharedPreferences mPreferences;

    @Inject
    PreferenceHelperImpl() {
        mPreferences = WanAndroidApp.getInstance().getSharedPreferences(MY_SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    @Override
    public void setCurrentPage(int position) {
        mPreferences.edit().putInt(MyConstant.CURRENT_PAGE,position).apply();
    }

    @Override
    public int getCurrentPage() {
        return mPreferences.getInt(MyConstant.CURRENT_PAGE, 0);
    }

    @Override
    public void setLoginUsername(String username) {
        mPreferences.edit().putString(MyConstant.ACCOUNT,username).apply();
    }

    @Override
    public void setLoginPassword(String password) {
        mPreferences.edit().putString(MyConstant.PASSWORD,password).apply();
    }

    @Override
    public String getLoginUsername() {
        return mPreferences.getString(MyConstant.ACCOUNT,"");
    }

    @Override
    public String getLoginPassword() {
        return mPreferences.getString(MyConstant.PASSWORD,"");
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
    public void setAutoCacheState(boolean isAutoCache) {
        mPreferences.edit().putBoolean(MyConstant.AUTO_CACHE_STATE,isAutoCache).apply();
    }

    @Override
    public boolean getAutoCacheState() {
        return mPreferences.getBoolean(MyConstant.AUTO_CACHE_STATE,true);
    }

    @Override
    public void setNoImageState(boolean isNoImageState) {
        mPreferences.edit().putBoolean(MyConstant.NO_IMAGE_STATE,isNoImageState).apply();
    }

    @Override
    public boolean getNoImageState() {
        return mPreferences.getBoolean(MyConstant.NO_IMAGE_STATE,false);
    }

    @Override
    public void setNightModeState(boolean isNightModeState) {
        mPreferences.edit().putBoolean(MyConstant.NIGHT_MODE_STATE,isNightModeState).apply();
    }

    @Override
    public boolean getNightModeState() {
        return mPreferences.getBoolean(MyConstant.NIGHT_MODE_STATE, false);
    }
}
