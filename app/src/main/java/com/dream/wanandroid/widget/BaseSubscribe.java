package com.dream.wanandroid.widget;

import android.text.TextUtils;

import com.dream.wanandroid.R;
import com.dream.wanandroid.WanAndroidApp;
import com.dream.wanandroid.base.view.IBaseView;
import com.dream.wanandroid.model.http.exception.ServerException;
import com.dream.wanandroid.utils.LoggerUtils;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

/**
 * @author quchao
 * @date 2018/4/2
 */

public abstract class BaseSubscribe <T> extends ResourceSubscriber<T> {

    public static final String TAG = BaseSubscribe.class.getSimpleName();
    private IBaseView mView;
    private String mErrorMsg;
    private boolean isShowError = true;

    protected BaseSubscribe(IBaseView view){
        this.mView = view;
    }

    protected BaseSubscribe(IBaseView view, String errorMsg){
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    protected BaseSubscribe(IBaseView view, boolean isShowError){
        this.mView = view;
        this.isShowError = isShowError;
    }

    protected BaseSubscribe(IBaseView view, String errorMsg, boolean isShowError){
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.isShowError = isShowError;
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        if (mView == null) {
            return;
        }
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            mView.showErrorMsg(mErrorMsg);
        } else if (e instanceof ServerException) {
            mView.showErrorMsg(e.toString());
        } else if (e instanceof HttpException) {
            mView.showErrorMsg(WanAndroidApp.getInstance().getString(R.string.http_error));
        } else {
            mView.showErrorMsg(WanAndroidApp.getInstance().getString(R.string.unKnown_error));
            LoggerUtils.d(TAG,e.toString());
        }
        if (isShowError) {
            mView.showError();
        }
    }
}
