package com.dream.wanandroid.ui.main.activity;

import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dream.wanandroid.R;
import com.dream.wanandroid.base.activity.BaseActivity;
import com.dream.wanandroid.common.MyConstant;
import com.dream.wanandroid.contract.main.LoginContract;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.main.login.LoginData;
import com.dream.wanandroid.model.event.LoginEvent;
import com.dream.wanandroid.presenter.main.LoginPresenter;
import com.dream.wanandroid.utils.CommonUtils;
import com.dream.wanandroid.utils.RxBus;
import com.dream.wanandroid.utils.StatusBarUtils;
import com.dream.wanandroid.widget.RegisterPopupWindow;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * LoginActivity
 * Created by Administrator on 2018/5/6.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View, View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_login_tag)
    TextView tvLoginTag;
    @BindView(R.id.edit_username)
    TextInputEditText editUsername;
    @BindView(R.id.ll_username)
    LinearLayout llUsername;
    @BindView(R.id.view_line_username)
    View viewLineUsername;
    @BindView(R.id.edit_pwd)
    TextInputEditText editPwd;
    @BindView(R.id.ll_pwd)
    LinearLayout llPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_or)
    TextView tvOr;


    private RegisterPopupWindow mRegisterPopupWindow;
    private Map<String, String> loginParams;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        initToolbar();
        loginParams = new HashMap<>();
        mPresenter.addRxBindingSubscribe(RxView.clicks(btnLogin)
                .throttleFirst(MyConstant.CLICK_TIME_AREA, TimeUnit.MILLISECONDS)
                .filter(o -> mPresenter != null)
                .subscribe(o -> {
                    String username = editUsername.getText().toString().trim();
                    String password = editPwd.getText().toString().trim();
                    if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                        CommonUtils.showSnackMessage(this, getString(R.string.account_password_null_tint));
                        return;
                    }

                    loginParams.put("username", username);
                    loginParams.put("password", password);
                    mPresenter.getLoginData(loginParams);
                }));


        mRegisterPopupWindow = new RegisterPopupWindow(this, v -> register());
        mRegisterPopupWindow.setAnimationStyle(R.style.PopupWindowAnimationStyle);
        mRegisterPopupWindow.setOnDismissListener(() -> {
            resetBackgroud();
            mRegisterPopupWindow.dismiss();
        });


    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
        StatusBarUtils.immersive(this);
        StatusBarUtils.setPaddingSmart(this, toolbar);
    }


    @Override
    public void showLoginData(BaseResponse<LoginData> loginDataBaseResponse) {
        if (loginDataBaseResponse == null || loginDataBaseResponse.getData() == null) {
            showLoginDataFailed(loginDataBaseResponse.getErrorMsg());
            return;
        }

        LoginData loginData = loginDataBaseResponse.getData();
        mPresenter.setLoginUsername(loginData.getUsername());
        mPresenter.setLoginPassword(loginData.getPassword());
        mPresenter.setLoginStatus(true);
        RxBus.getDefault().post(new LoginEvent(true));
        CommonUtils.showSnackMessage(this,getString(R.string.login_success));
        onBackPressedSupport();


    }

    @Override
    public void showLoginDataFailed(String errorMsg) {
        CommonUtils.showSnackMessage(this, errorMsg);
    }

    @Override
    public void showRegisterData(BaseResponse<LoginData> registerDataBaseResponse) {
        if (registerDataBaseResponse == null || registerDataBaseResponse.getData() == null) {
            showRegisterDataFailed(registerDataBaseResponse.getErrorMsg());
            return;
        }

        loginParams.put("username", registerDataBaseResponse.getData().getUsername());
        loginParams.put("password", registerDataBaseResponse.getData(). getPassword());
        mPresenter.getLoginData(loginParams);
    }

    @Override
    public void showRegisterDataFailed(String errorMsg) {
        CommonUtils.showSnackMessage(this, errorMsg);
    }



    @OnClick({R.id.btn_to_register})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_to_register:
                mRegisterPopupWindow.showAtLocation(view, Gravity.CENTER,0,0);
                //setBackgroundDim();
                break;
                default:
                    break;
        }
    }

    /**
     * 注册
     */
    private void register() {
        if(mRegisterPopupWindow == null || mPresenter == null){
            return;
        }
        String username = mRegisterPopupWindow.editUsername.getText().toString().trim();
        String pwd = mRegisterPopupWindow.editPwd.getText().toString().trim();
        String confirmPwd = mRegisterPopupWindow.editConfirmPwd.getText().toString().trim();

        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(confirmPwd)){
            CommonUtils.showSnackMessage(this,getString(R.string.account_password_null_tint));
            return;
        }

        if(!pwd.equals(confirmPwd)){
            CommonUtils.showSnackMessage(this,getString(R.string.password_not_same));
            return;
        }
        //username,password,repassword
        Map<String, String> registerParams = new HashMap<>();
        registerParams.put("username",username);
        registerParams.put("password", pwd);
        registerParams.put("repassword", confirmPwd);
        mPresenter.getRegisterParams(registerParams);
    }

//    private void setBackgroundDim(){
//        WindowManager.LayoutParams attributes = getWindow().getAttributes();
//        attributes.alpha = 0.8f;
//        getWindow().setAttributes(attributes);
//    }

    private void resetBackgroud(){
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 1.0f;
        getWindow().setAttributes(attributes);
    }


}
