package com.dream.wanandroid.base.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

/**AbstractSimpleDialogFragmnet
 * Created by Administrator on 2018/5/4.
 */

public abstract class AbstractSimpleDialogFragmnet extends DialogFragment {

    protected View rootView;
    private Unbinder unbinder;
    private CompositeDisposable compositeDisposable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(),container,false);
        unbinder = ButterKnife.bind(this, rootView);
        compositeDisposable = new CompositeDisposable();

        initEventAndData();
        return rootView;
    }



    /**
     * 初始化数据
     */
    protected abstract void initEventAndData();

    /**
     * 获取布局id
     * @return 布局id
     */
    protected abstract int getLayoutId();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(compositeDisposable != null){
            compositeDisposable.clear();
        }

        if(unbinder != null){
            unbinder.unbind();
        }
    }
}
