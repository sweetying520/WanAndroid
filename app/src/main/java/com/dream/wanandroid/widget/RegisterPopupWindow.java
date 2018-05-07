package com.dream.wanandroid.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.dream.wanandroid.R;

/**
 * Created by Administrator on 2018/5/7.
 */

public class RegisterPopupWindow extends PopupWindow{

    public RegisterPopupWindow(Activity context) {
        super(context);

        initView(context);
    }

    /**
     * 初始化
     */
    @SuppressLint("InflateParams")
    private void initView(Activity mContext) {
        View popView = LayoutInflater.from(mContext).inflate(R.layout.popup_window_register, null);
        setContentView(popView);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setOutsideTouchable(true);
        setFocusable(true);



        WindowManager windowManager = mContext.getWindowManager();
        int width = (int) (windowManager.getDefaultDisplay().getWidth()*0.7);
        int height = (int) (windowManager.getDefaultDisplay().getHeight()*0.5);
        this.setWidth(width);
        this.setHeight(height);
    }
}
