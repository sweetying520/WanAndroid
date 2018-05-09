package com.dream.wanandroid.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Administrator on 2018/5/9.
 */

public class ShareUtil {

    private static final String EMAIL = "1770385837@qq.com";

    public static void sendEmail(Context mContext,String title){
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + EMAIL));
        mContext.startActivity(Intent.createChooser(intent,title));
    }
}
