package com.dream.wanandroid.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.dream.wanandroid.R;

/**RegisterPopupWindow
 * Created by Administrator on 2018/5/7.
 */

public class RegisterPopupWindow extends PopupWindow{

    public TextInputEditText editUsername;
    public TextInputEditText editPwd;
    public TextInputEditText editConfirmPwd;

    public RegisterPopupWindow(Activity context, View.OnClickListener onClickListener) {
        super(context);

        initView(context,onClickListener);
    }

    /**
     * 初始化
     */
    @SuppressLint("InflateParams")
    private void initView(Activity mContext,View.OnClickListener onClickListener) {
        View popView = LayoutInflater.from(mContext).inflate(R.layout.popup_window_register, null);
        editUsername = popView.findViewById(R.id.edit_username);
         editPwd = popView.findViewById(R.id.edit_pwd);
         editConfirmPwd = popView.findViewById(R.id.edit_confirm_username);

        popView.findViewById(R.id.btn_register).setOnClickListener(onClickListener);



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
