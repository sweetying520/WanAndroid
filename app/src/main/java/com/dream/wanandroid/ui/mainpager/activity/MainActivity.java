package com.dream.wanandroid.ui.mainpager.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dream.wanandroid.R;
import com.dream.wanandroid.base.activity.BaseActivity;
import com.dream.wanandroid.contract.main.MainContract;
import com.dream.wanandroid.presenter.main.MainPresenter;
import com.dream.wanandroid.utils.BottomNavigationViewHelper;
import com.dream.wanandroid.utils.StatusBarUtil;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {


    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fragment_group)
    FrameLayout fragmentGroup;
    @BindView(R.id.fab_main)
    FloatingActionButton fabMain;
    @BindView(R.id.bottom_nav_view)
    BottomNavigationView bottomNavView;
    @BindView(R.id.nav_view)
    NavigationView navView;

    @Override
    protected void initEventAndData() {
        initToolbar();
    }

    private void initToolbar() {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this,R.color.main_status_bar_blue));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        initNavView();
        BottomNavigationViewHelper.disableShiftMode(bottomNavView);

    }

    private void initNavView() {
        navView.getMenu().findItem(R.id.wanandroid).setOnMenuItemClickListener(item -> {
            return true;
        });

        navView.getMenu().findItem(R.id.collect).setOnMenuItemClickListener(item -> {

            return true;
        });

        navView.getMenu().findItem(R.id.setting).setOnMenuItemClickListener(item -> {

            return true;
        });

        navView.getMenu().findItem(R.id.about).setOnMenuItemClickListener(item -> {

            return true;
        });

        navView.getMenu().findItem(R.id.logout).setOnMenuItemClickListener(item -> {

            return true;
        });
    }
}
