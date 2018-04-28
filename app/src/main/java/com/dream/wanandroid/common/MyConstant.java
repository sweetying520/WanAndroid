package com.dream.wanandroid.common;

import android.graphics.Color;

import com.dream.wanandroid.WanAndroidApp;

import java.io.File;

/**
 * App常量存放处
 * Created by Administrator on 2018/4/27.
 */

public class MyConstant {


    public static String CHACHE_PATH = WanAndroidApp.getInstance().getCacheDir().getAbsolutePath() + File.separator + "WanAndroidCache";

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
}
