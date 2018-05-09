package com.dream.wanandroid.base.presenter;

import com.dream.wanandroid.base.view.IBaseView;
import com.dream.wanandroid.model.DataManager;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**BasePresenter
 * Created by Administrator on 2018/4/27.
 */

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V>{

    protected V mView;
    private CompositeDisposable mCompositeDisposable;
    protected DataManager mDataManager;


    public BasePresenter(DataManager dataManager){
        mDataManager = dataManager;
    }

    /**
     * 添加订阅者
     * @param disposable disposable
     */
    protected void addSubscribe(Disposable disposable){
        if(mCompositeDisposable == null){
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    /**
     * 绑定View
     * @param mView mView
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

    @Override
    public int getCurrentPage() {
        return mDataManager.getCurrentPage();
    }

    @Override
    public void setLoginStatus(boolean isLogin) {
        mDataManager.setLoginStatus(isLogin);
    }

    @Override
    public boolean getLoginStatus() {
        return mDataManager.getLoginStatus();
    }

    @Override
    public String getLoginUsername() {
        return mDataManager.getLoginUsername();
    }

    @Override
    public boolean getAutoCacheState() {
        return mDataManager.getAutoCacheState();
    }

    @Override
    public boolean getNightModeState() {
        return mDataManager.getNightModeState();
    }

    @Override
    public boolean getNoImageState() {
        return mDataManager.getNoImageState();
    }
}
