package com.dream.wanandroid.model.event;

/**
 * Created by Administrator on 2018/5/2.
 */

public class LoginEvent {
    private boolean isLogin;

    public LoginEvent(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
