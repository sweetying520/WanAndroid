package com.dream.wanandroid.common;

import android.graphics.Color;

import com.dream.wanandroid.R;
import com.dream.wanandroid.WanAndroidApp;

import java.io.File;

/**
 * App常量存放处
 * Created by Administrator on 2018/4/27.
 */

public class MyConstant {


    public static String CHACHE_PATH = WanAndroidApp.getInstance().getCacheDir().getAbsolutePath() + File.separator + "WanAndroidCache";

    public static final String COOKIE = "Cookie";

    /**
     * Tag fragment classify
     */
    public static final int TYPE_MAIN_PAGER = 0;

    public static final int TYPE_KNOWLEDGE = 1;

    public static final int TYPE_NAVIGATION = 2;

    public static final int TYPE_PROJECT = 3;

    public static final int TYPE_COLLECT = 4;

    public static final int TYPE_SETTING = 5;


    /**
     * Bottom Navigation tab classify
     */
    public static final int TAB_ONE = 0;

    /**
     * Intent params
     */
    public static final String ARG_PARAM1 = "param1";

    public static final String ARG_PARAM2 = "param2";

    /**
     * Tab colors
     */
    public static final int[] TAB_COLORS = new int[]{
            Color.parseColor("#90C5F0"),
            Color.parseColor("#91CED5"),
            Color.parseColor("#F88F55"),
            Color.parseColor("#C0AFD0"),
            Color.parseColor("#E78F8F"),
            Color.parseColor("#67CCB7"),
            Color.parseColor("#F6BC7E")
    };

    /**
     * Shared Preference key
     */
    public static final String ACCOUNT = "account";

    public static final String PASSWORD = "password";

    public static final String LOGIN_STATUS = "login_status";

    public static final String AUTO_CACHE_STATE = "auto_cache_state";

    public static final String NO_IMAGE_STATE = "no_image_state";

    public static final String NIGHT_MODE_STATE = "night_mode_state";

    /**
     * Refresh theme color
     */
    public static final int BLUE_THEME = R.color.colorPrimary;

    /**
     * Avoid double click time area
     */
    public static final long CLICK_TIME_AREA = 1000;


    public static final String ARTICLE_LINK = "article_link";

    public static final String ARTICLE_TITLE = "article_title";

    public static final String ARTICLE_ID = "article_id";

    public static final String IS_COLLECT = "is_collect";

    public static final String IS_COMMON_SITE = "is_common_site";

    public static final String IS_COLLECT_PAGE = "is_collect_page";

    public static final String CHAPTER_ID = "chapter_id";

    public static final String IS_SINGLE_CHAPTER = "is_single_chapter";

    public static final String CHAPTER_NAME = "is_chapter_name";

    public static final String SUPER_CHAPTER_NAME = "super_chapter_name";

    public static final String DB_NAME = "wan_android.db";

    public static final String CURRENT_PAGE = "current_page";

    public static final String PROJECT_CURRENT_PAGE = "project_current_page";

    public static final String SEARCH_PARAMS = "search_params";
}
