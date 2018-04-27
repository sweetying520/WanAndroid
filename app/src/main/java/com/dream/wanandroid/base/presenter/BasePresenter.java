package com.dream.wanandroid.base.presenter;

import com.dream.wanandroid.base.view.IBaseView;
import com.dream.wanandroid.model.DataManager;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/4/27.
 */

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V>{

    protected V mView;
    private CompositeDisposable mCompositeDisposable;
    private DataManager mDataManager;


    public BasePresenter(DataManager dataManager){
        mDataManager = dataManager;
    }

    /**
     * 添加订阅者
     * @param disposable
     */
    protected void addSubscribe(Disposable disposable){
        if(mCompositeDisposable == null){
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    /**
     * 绑定View
     * @param mView
     */
    @Override
    public void attachView(V mView) {
        this.mView = mView;
    }

    /**
     * 解除View
     */
    @Override
    public void detachView() {
        this.mView = null;
        if(mCompositeDisposable != null){
            mCompositeDisposable.clear();
        }
    }

    @Override
    public void addRxBindingSubscribe(Disposable disposable) {
        addSubscribe(disposable);
    }
}