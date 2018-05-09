package com.dream.wanandroid.model.prefs;

/**
 * @author quchao
 * @date 2017/11/27
 */

public interface PreferenceHelper {
    /**
     * Set current page
     *
     * @param position Position
     */
    void setCurrentPage(int position);

    /**
     * Get current page
     *
     * @return current page
     */
    int getCurrentPage();

    void setLoginUsername(String username);

    void setLoginPassword(String passowrd);

    String getLoginUsername();

    String getLoginPassword();

    void setLoginStatus(boolean isLogin);

    boolean getLoginStatus();

    void setAutoCacheState(boolean isAutoCache);

    boolean getAutoCacheState();

    void setNoImageState(boolean isNoImageState);

    boolean getNoImageState();

    void setNightModeState(boolean isNightModeState);

    boolean getNightModeState();




}
