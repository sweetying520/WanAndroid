package com.dream.wanandroid.ui.main.activity;

import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dream.wanandroid.R;
import com.dream.wanandroid.base.activity.BaseActivity;
import com.dream.wanandroid.contract.main.LoginContract;
import com.dream.wanandroid.presenter.main.LoginPresenter;
import com.dream.wanandroid.utils.StatusBarUtils;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/5/6.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

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
    @BindView(R.id.btn_register)
    Button btnRegister;

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
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
        StatusBarUtils.immersive(this);
        StatusBarUtils.setPaddingSmart(this,toolbar);
    }


}
