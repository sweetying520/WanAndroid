package com.dream.wanandroid.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dream.wanandroid.R;
import com.dream.wanandroid.WanAndroidApp;
import com.dream.wanandroid.base.activity.BaseActivity;
import com.dream.wanandroid.base.fragment.BaseFragment;
import com.dream.wanandroid.common.MyConstant;
import com.dream.wanandroid.contract.main.MainContract;
import com.dream.wanandroid.model.event.LoginEvent;
import com.dream.wanandroid.model.http.cookies.CookiesManager;
import com.dream.wanandroid.presenter.main.MainPresenter;
import com.dream.wanandroid.ui.hierarchy.fragment.KnowledgeHierarchyFragment;
import com.dream.wanandroid.ui.like.fragment.LikeFragment;
import com.dream.wanandroid.ui.main.fragment.SearchDialogFragment;
import com.dream.wanandroid.ui.main.fragment.UsageDialogFragment;
import com.dream.wanandroid.ui.mainpager.fragment.HomePagerFragment;
import com.dream.wanandroid.ui.navigation.fragment.NavigationFragment;
import com.dream.wanandroid.ui.project.fragment.ProjectFragment;
import com.dream.wanandroid.utils.BottomNavigationViewHelper;
import com.dream.wanandroid.utils.RxBus;
import com.dream.wanandroid.utils.StatusBarUtils;
import com.dream.wanandroid.widget.CommonAlertDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {


    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab_main)
    FloatingActionButton fabMain;
    @BindView(R.id.bottom_nav_view)
    BottomNavigationView bottomNavView;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_main)
    DrawerLayout drawerMain;
    private List<BaseFragment> fragmentList;
    private int lastIndex;//最后一个fragment的位置

    private HomePagerFragment homePagerFragment;
    private KnowledgeHierarchyFragment knowledgeHierarchyFragment;
    private NavigationFragment navigationFragment;
    private ProjectFragment projectFragment;

    private TextView tvStatus;

    @Override
    protected void initEventAndData() {
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        tvToolbarTitle.setText(getResources().getString(R.string.home));
        StatusBarUtils.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.main_status_bar_blue), 0.5f);
        toolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }


    //菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //菜单的事件监听
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                SearchDialogFragment searchDialogFragment = new SearchDialogFragment();
                searchDialogFragment.show(getSupportFragmentManager(), "SearchDialogFragment");
                break;
            case R.id.usage:
                UsageDialogFragment usageDialogFragment = new UsageDialogFragment();
                usageDialogFragment.show(getSupportFragmentManager(),"UsageDialogFragment");
                break;
        }
        return true;
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
        fragmentList = new ArrayList<>();
//        if (savedInstanceState == null) {
//
//        } else {
//
//        }
        homePagerFragment = HomePagerFragment.getInstance(true, null);
        fragmentList.add(homePagerFragment);
        initData();
        init();
        swicthFragment(MyConstant.TYPE_MAIN_PAGER);

    }

    private void swicthFragment(int position) {
        if (position >= MyConstant.TYPE_COLLECT) {
            fabMain.setVisibility(View.INVISIBLE);
            bottomNavView.setVisibility(View.INVISIBLE);
        } else {
            fabMain.setVisibility(View.VISIBLE);
            bottomNavView.setVisibility(View.VISIBLE);
        }

        if (position >= fragmentList.size()) {
            return;
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment targetFragment = fragmentList.get(position);
        Fragment lastFragment = fragmentList.get(lastIndex);
        lastIndex = position;
        ft.hide(lastFragment);
        if (!targetFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(targetFragment).commit();
            ft.add(R.id.fragment_group, targetFragment);
        }
        ft.show(targetFragment);
        ft.commitAllowingStateLoss();
    }

    private void switchKnowledgeHierarchyFragment() {
        mPresenter.setCurrentPage(MyConstant.TYPE_KNOWLEDGE);
        tvToolbarTitle.setText(WanAndroidApp.getInstance().getString(R.string.knowledge_hierarchy));
        swicthFragment(MyConstant.TYPE_KNOWLEDGE);

    }

    private void switchNavigation() {
        mPresenter.setCurrentPage(MyConstant.TYPE_NAVIGATION);
        tvToolbarTitle.setText(WanAndroidApp.getInstance().getString(R.string.navigation));
        swicthFragment(MyConstant.TYPE_NAVIGATION);

    }

    private void switchHomePager() {
        mPresenter.setCurrentPage(MyConstant.TYPE_MAIN_PAGER);
        tvToolbarTitle.setText(WanAndroidApp.getInstance().getString(R.string.home));
        swicthFragment(MyConstant.TYPE_MAIN_PAGER);

    }

    private void switchProject() {
        mPresenter.setCurrentPage(MyConstant.TYPE_PROJECT);
        tvToolbarTitle.setText(WanAndroidApp.getInstance().getString(R.string.project));
        swicthFragment(MyConstant.TYPE_PROJECT);

    }

    private void switchCollect() {
        tvToolbarTitle.setText(WanAndroidApp.getInstance().getString(R.string.collect));
        swicthFragment(MyConstant.TYPE_COLLECT);
        drawerMain.closeDrawer(Gravity.START);
    }

    private void startMainPager(){
        tvToolbarTitle.setText(WanAndroidApp.getInstance().getString(R.string.home));
        bottomNavView.setSelectedItemId(R.id.tab_home);
        drawerMain.closeDrawer(Gravity.START);
    }





    private void initData() {
        knowledgeHierarchyFragment = KnowledgeHierarchyFragment.getInstance(null, null);
        navigationFragment = NavigationFragment.getInstance(null, null);
        projectFragment = ProjectFragment.getInstance(null, null);
        LikeFragment likeFragment = LikeFragment.getInstance(null, null);
        fragmentList.add(knowledgeHierarchyFragment);
        fragmentList.add(navigationFragment);
        fragmentList.add(projectFragment);
        fragmentList.add(likeFragment);
    }

    private void init() {
        initNavView();
        mPresenter.setCurrentPage(MyConstant.TYPE_MAIN_PAGER);
        BottomNavigationViewHelper.disableShiftMode(bottomNavView);
        bottomNavView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.tab_home:
                    switchHomePager();
                    break;
                case R.id.tab_knowledge_hierarchy:
                    switchKnowledgeHierarchyFragment();
                    break;
                case R.id.tab_navigation:
                    switchNavigation();
                    break;
                case R.id.tab_project:
                    switchProject();
                    break;
            }
            return true;
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerMain,
                toolbar,
                R.string.nav_open,
                R.string.nav_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
                //获取抽屉的view
                View mContent = drawerMain.getChildAt(0);
                float scale = 1 - slideOffset;
                float endScale = 0.8f + scale * 0.2f;
                float startScale = 1 - 0.3f * scale;

                //设置左边菜单滑动后的占据屏幕大小
                drawerView.setScaleX(startScale);
                drawerView.setScaleY(startScale);
                //设置菜单透明度
                drawerView.setAlpha(0.6f + 0.4f * (1 - scale));

                //设置内容界面水平和垂直方向偏转量
                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                mContent.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));
                //设置内容界面操作无效（比如有button就会点击无效）
                mContent.invalidate();
                //设置右边菜单滑动后的占据屏幕大小
                mContent.setScaleX(endScale);
                mContent.setScaleY(endScale);
            }
        };
        toggle.syncState();
        drawerMain.addDrawerListener(toggle);
    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            ActivityCompat.finishAfterTransition(this);
        }

    }

    public void jumpToTop() {
        switch (mPresenter.getCurrentPage()) {
            case MyConstant.TYPE_MAIN_PAGER:
                if (homePagerFragment != null) {
                    homePagerFragment.jumpToTop();
                }
                break;
            case MyConstant.TYPE_KNOWLEDGE:
                if (knowledgeHierarchyFragment != null) {
                    knowledgeHierarchyFragment.jumpToTop();
                }
                break;
            case MyConstant.TYPE_NAVIGATION:
                if (navigationFragment != null) {
                    navigationFragment.jumpToTop();
                }
                break;
            case MyConstant.TYPE_PROJECT:
                if (projectFragment != null) {
                    projectFragment.jumpToTop();
                }
                break;
        }
    }

    private void initNavView() {
        if(mPresenter.getLoginStatus()){
            showLoginView();
        }else {
            showLogoutView();
        }

        navView.getMenu().findItem(R.id.wanandroid).setOnMenuItemClickListener(item -> {
            startMainPager();
            return true;
        });

        navView.getMenu().findItem(R.id.collect).setOnMenuItemClickListener(item -> {
            switchCollect();
            return true;
        });
//
//        navView.getMenu().findItem(R.id.setting).setOnMenuItemClickListener(item -> {
//
//            return true;
//        });

        navView.getMenu().findItem(R.id.about).setOnMenuItemClickListener(item -> {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        });

        navView.getMenu().findItem(R.id.logout).setOnMenuItemClickListener(item -> {
            logout();
            return true;
        });
    }

    /**
     * 登出
     */
    private void logout() {
        CommonAlertDialog.getInstance().showDialog(this,
                getString(R.string.logout_tint),
                getString(R.string.ok),
                getString(R.string.no),
                v -> {
                    CommonAlertDialog.getInstance().cancelDialog();
                    navView.getMenu().findItem(R.id.logout).setVisible(false);
                    CookiesManager.clearAllCookies();
                    mPresenter.setLoginStatus(false);
                    RxBus.getDefault().post(new LoginEvent(false));
                    startActivity(new Intent(this,LoginActivity.class));
                },
                v -> CommonAlertDialog.getInstance().cancelDialog());
    }

    @OnClick({R.id.fab_main})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_main:
                jumpToTop();
                break;
            default:
                break;
        }

    }

    /**
     * 登录
     */
    @Override
    public void showLoginView() {
        tvStatus = navView.getHeaderView(0).findViewById(R.id.tv_log_state);
        tvStatus.setText(mPresenter.getLoginUsername());
        tvStatus.setOnClickListener(null);

        navView.getMenu().findItem(R.id.logout).setVisible(true);
    }

    /**
     * 登出
     */
    @Override
    public void showLogoutView() {
        tvStatus = navView.getHeaderView(0).findViewById(R.id.tv_log_state);
        tvStatus.setText(getString(R.string.login));
        tvStatus.setOnClickListener(v -> startActivity(new Intent(this,LoginActivity.class)));

        navView.getMenu().findItem(R.id.logout).setVisible(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CommonAlertDialog.getInstance().cancelDialog();
    }
}
