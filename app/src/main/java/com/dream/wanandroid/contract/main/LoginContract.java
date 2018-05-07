package com.dream.wanandroid.contract.main;

import com.dream.wanandroid.base.presenter.IBasePresenter;
import com.dream.wanandroid.base.view.IBaseView;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.main.login.LoginData;

import java.util.Map;

/**
 * Created by Administrator on 2018/5/6.
 */

public interface LoginContract {
    interface View extends IBaseView{
        void showLoginData(BaseResponse<LoginData> loginDataBaseResponse);
        void showLoginDataFailed(String errorMsg);

        void showRegisterData(BaseResponse<LoginData> registerDataBaseResponse);
        void showRegisterDataFailed(String errorMsg);

    }

    interface Presenter extends IBasePresenter<View>{
        void getLoginData(Map<String, String> loginParams);
        void getRegisterParams(Map<String, String> loginParams);

        void setLoginUsername(String username);

        void setLoginPassword(String password);
    }
}
