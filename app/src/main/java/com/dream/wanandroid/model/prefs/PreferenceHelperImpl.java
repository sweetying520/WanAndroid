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
    public void setCurrentPage(int position) {
        mPreferences.edit().putInt(MyConstant.CURRENT_PAGE,position).apply();
    }

    @Override
    public int getCurrentPage() {
        return mPreferences.getInt(MyConstant.CURRENT_PAGE, 0);
    }
}
