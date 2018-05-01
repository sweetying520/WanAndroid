package com.dream.wanandroid.presenter.main;

import com.dream.wanandroid.WanAndroidApp;
import com.dream.wanandroid.base.presenter.BasePresenter;
import com.dream.wanandroid.contract.main.SplashContract;
import com.dream.wanandroid.model.DataManager;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * SplashPresenter
 */
public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {

    private DataManager dataManager;

    @Inject
    SplashPresenter(DataManager dataManager) {
        super(dataManager);
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(SplashContract.View mView) {
        super.attachView(mView);
        //延时两秒进入App
        if (!WanAndroidApp.isFirstRun) {
            long splashTime = 2000;
            addSubscribe(Observable.timer(splashTime, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(aLong -> mView.jumpToMain()));
        }
    }
}
