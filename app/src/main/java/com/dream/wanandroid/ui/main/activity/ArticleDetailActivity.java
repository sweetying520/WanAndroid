package com.dream.wanandroid.ui.main.activity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.dream.wanandroid.R;
import com.dream.wanandroid.base.activity.BaseActivity;
import com.dream.wanandroid.common.MyConstant;
import com.dream.wanandroid.contract.main.ArticleDetailContract;
import com.dream.wanandroid.presenter.main.ArticleDetailPresenter;
import com.dream.wanandroid.utils.CommonUtils;
import com.dream.wanandroid.utils.StatusBarUtils;
import com.just.agentweb.AgentWeb;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;

/**
 * ArticleDetailActivity
 * Created by Administrator on 2018/5/8.
 */

public class ArticleDetailActivity extends BaseActivity<ArticleDetailPresenter> implements ArticleDetailContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fl_group)
    FrameLayout flGroup;
    private Context mContext;

    //餐宿
    private int articleId;
    private String articleTitle;
    private String articleLink;
    private boolean isCollect;
    private boolean isCollectPage;
    private boolean isCollectSite;

    private AgentWeb mAgentWeb;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail_article;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initEventAndData() {
        mContext = this;


        initIntentData();

        initToolbar();

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(flGroup, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setMainFrameErrorView(R.layout.error_view, -1)
                .createAgentWeb()
                .ready()
                .go(articleLink);

        WebView mWebView = mAgentWeb.getWebCreator().getWebView();
        WebSettings mSettings = mWebView.getSettings();

        //加载有图模式
        if (mPresenter.getNoImageState()) {
            mSettings.setBlockNetworkImage(true);
        } else {
            mSettings.setBlockNetworkImage(false);
        }


        //是否自动缓存
        if (mPresenter.getAutoCacheState()) {
            mSettings.setAppCacheEnabled(true);
            mSettings.setDomStorageEnabled(true);
            mSettings.setDatabaseEnabled(true);
            if (CommonUtils.isNetworkConnected()) {
                mSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
            } else {
                mSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            }
        } else {
            mSettings.setAppCacheEnabled(true);
            mSettings.setDomStorageEnabled(true);
            mSettings.setDatabaseEnabled(true);
            mSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        }


        mSettings.setJavaScriptEnabled(true);
        mSettings.setSupportZoom(true);
        mSettings.setBuiltInZoomControls(true);
        //不显示缩放按钮
        mSettings.setDisplayZoomControls(false);
        //设置自适应屏幕，两者合用
        //将图片调整到适合WebView的大小
        mSettings.setUseWideViewPort(true);
        //缩放至屏幕的大小
        mSettings.setLoadWithOverviewMode(true);
        //自适应屏幕
        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mAgentWeb.handleKeyEvent(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    private void initIntentData() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        articleId = bundle.getInt(MyConstant.ARTICLE_ID);
        articleTitle = bundle.getString(MyConstant.ARTICLE_TITLE);
        articleLink = bundle.getString(MyConstant.ARTICLE_LINK);
        isCollect = bundle.getBoolean(MyConstant.IS_COLLECT);
        isCollectPage = bundle.getBoolean(MyConstant.IS_COLLECT_PAGE);
        isCollectSite = bundle.getBoolean(MyConstant.IS_COMMON_SITE);
    }

    private void initToolbar() {
        if (!TextUtils.isEmpty(articleTitle)) {
            setToolBar(toolbar, articleTitle);
        }

        StatusBarUtils.immersive(this);
        StatusBarUtils.setPaddingSmart(mContext, toolbar);

        toolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!isCollectSite) {
            getMenuInflater().inflate(R.menu.menu_article, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_article_common, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                mPresenter.shareWithPermission(new RxPermissions(this));
                break;
            case R.id.open_in_browser:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(articleLink)));
                break;
        }
        return true;
    }

    public static void start(Context mContext, ActivityOptions activityOptions, int id, String articleTitle, String articleLink, boolean isCollect, boolean isCollectPage, boolean isCollectSite) {
        Intent intent = new Intent(mContext, ArticleDetailActivity.class);
        intent.putExtra(MyConstant.ARTICLE_ID, id);
        intent.putExtra(MyConstant.ARTICLE_TITLE, articleTitle);
        intent.putExtra(MyConstant.ARTICLE_LINK, articleLink);
        intent.putExtra(MyConstant.IS_COLLECT, isCollect);
        intent.putExtra(MyConstant.IS_COLLECT_PAGE, isCollectPage);
        intent.putExtra(MyConstant.IS_COMMON_SITE, isCollectSite);
        if (activityOptions != null && !Build.BOARD.contains("samsung") && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mContext.startActivity(intent, activityOptions.toBundle());
        } else {
            mContext.startActivity(intent);
        }

    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void shareEvent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,getString(R.string.share_content,getString(R.string.app_name),articleTitle,articleLink));
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent,getString(R.string.share_to)));
    }

    @Override
    public void shareFailed() {
        CommonUtils.showMessage(this,getString(R.string.write_permission_not_allowed));
    }
}
