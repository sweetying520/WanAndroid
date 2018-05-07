package com.dream.wanandroid.widget;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.dream.wanandroid.R;


/**CommonAlertDialog
 * Created by Administrator on 2018/5/7.
 */

public class CommonAlertDialog {


    private AlertDialog alertDialog;
    public static CommonAlertDialog getInstance(){
        return CommonAlertDialogHolder.commonAlertDialog;
    }

    private static class CommonAlertDialogHolder{
        private static CommonAlertDialog commonAlertDialog = new CommonAlertDialog();
    }

    public void showDialog(Activity mActivity, String content, String okContent, String noContent,
                           View.OnClickListener positiveClickListener,
                           View.OnClickListener negativeClickListener){

        if(mActivity == null){
            return;
        }

        if(alertDialog == null){
            alertDialog = new AlertDialog.Builder(mActivity, R.style.CommonDialogStyle).create();
        }

        if(!alertDialog.isShowing()){
            alertDialog.show();
        }

        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        Window window = alertDialog.getWindow();
        if(window != null){
            View mRootView = LayoutInflater.from(mActivity).inflate(R.layout.item_commen_dialog, null);
            TextView tvContent = mRootView.findViewById(R.id.dialog_content);
            Button btnConfirm = mRootView.findViewById(R.id.dialog_btn);
            Button btnCancel = mRootView.findViewById(R.id.dialog_negative_btn);
            tvContent.setText(content);
            btnConfirm.setText(okContent);
            btnCancel.setText(noContent);
            btnConfirm.setOnClickListener(positiveClickListener);
            btnCancel.setOnClickListener(negativeClickListener);
            window.setContentView(mRootView);
        }
    }

    public void cancelDialog(){
        if(alertDialog != null && alertDialog.isShowing()){
            alertDialog.dismiss();
            alertDialog = null;
        }
    }
}
