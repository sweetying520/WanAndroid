package com.dream.wanandroid.presenter.main;

import com.dream.wanandroid.base.presenter.BasePresenter;
import com.dream.wanandroid.contract.main.MainContract;
import com.dream.wanandroid.model.DataManager;
import com.dream.wanandroid.model.event.LoginEvent;
import com.dream.wanandroid.utils.RxBus;

import javax.inject.Inject;

/**MainPresenter
 * Created by Administrator on 2018/5/1.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter{


    @Inject
    MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void attachView(MainContract.View mView) {
        super.attachView(mView);
        registerEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
        .filter(LoginEvent::isLogin)
        .subscribe(loginEvent -> mView.showLoginView()));

        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
                .filter(loginEvent -> !loginEvent.isLogin())
                .subscribe(loginEvent -> mView.showLogoutView()));

    }

    @Override
    public void setCurrentPage(int position) {
        mDataManager.setCurrentPage(position);
    }
}
