package com.dream.wanandroid.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * ImageLoader
 * Created by Administrator on 2018/5/4.
 */

public class ImageLoader {

    /**
     *
     * @param mContext 上下文
     * @param o  加载路径
     * @param iv 设置给ImageView对象
     */
    public static void load(Context mContext, Object o, ImageView iv){
        Glide.with(mContext).load(o).into(iv);
    }
}
