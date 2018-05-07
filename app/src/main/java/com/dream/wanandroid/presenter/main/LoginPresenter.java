package com.dream.wanandroid.presenter.main;

import com.dream.wanandroid.base.presenter.BasePresenter;
import com.dream.wanandroid.contract.main.LoginContract;
import com.dream.wanandroid.model.DataManager;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.main.login.LoginData;
import com.dream.wanandroid.utils.RxUtils;
import com.dream.wanandroid.widget.BaseObserver;

import java.util.Map;

import javax.inject.Inject;

/**LoginPresenter
 * Created by Administrator on 2018/5/6.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    @Inject
    LoginPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getLoginData(Map<String, String> loginParams) {
        addSubscribe(mDataManager.getLoginData(loginParams)
        .compose(RxUtils.rxSchedulerHelper())
        .subscribeWith(new BaseObserver<BaseResponse<LoginData>>(mView){

            @Override
            public void onNext(BaseResponse<LoginData> loginDataBaseResponse) {
                if(loginDataBaseResponse.getErrorCode() == BaseResponse.SUCCESS){
                    mView.showLoginData(loginDataBaseResponse);
                }else {
                    mView.showLoginDataFailed(loginDataBaseResponse.getErrorMsg());
                }
            }
        }));
    }

    @Override
    public void getRegisterParams(Map<String, String> registerParams) {
        addSubscribe(mDataManager.getRegisterData(registerParams)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver<BaseResponse<LoginData>>(mView){

                    @Override
                    public void onNext(BaseResponse<LoginData> registerDataBaseResponse) {
                        if(registerDataBaseResponse.getErrorCode() == BaseResponse.SUCCESS){
                            mView.showRegisterData(registerDataBaseResponse);
                        }else {
                            mView.showRegisterDataFailed(registerDataBaseResponse.getErrorMsg());
                        }
                    }
                }));
    }
}
