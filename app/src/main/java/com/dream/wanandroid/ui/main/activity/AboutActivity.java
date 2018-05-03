package com.dream.wanandroid.ui.main.activity;

import android.animation.ValueAnimator;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.dream.wanandroid.R;
import com.dream.wanandroid.base.activity.AbstractSimpleActivity;
import com.dream.wanandroid.utils.StatusBarUtils;
import com.dream.wanandroid.widget.interpolator.ElasticOutInterpolator;
import com.scwang.smartrefresh.header.FlyRefreshHeader;
import com.scwang.smartrefresh.header.flyrefresh.FlyView;
import com.scwang.smartrefresh.header.flyrefresh.MountainSceneView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/5/2.
 * 关于我们
 */

public class AboutActivity extends AbstractSimpleActivity {


    @BindView(R.id.mountain_about)
    MountainSceneView mountainAbout;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar_about)
    AppBarLayout appbarAbout;
    @BindView(R.id.about_us_fly_refresh)
    FlyRefreshHeader aboutUsFlyRefresh;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.aboutVersion)
    TextView aboutVersion;
    @BindView(R.id.aboutContent)
    TextView aboutContent;
    @BindView(R.id.about_us_refresh_layout)
    SmartRefreshLayout aboutUsRefreshLayout;
    @BindView(R.id.fab_about)
    FloatingActionButton fabAbout;
    @BindView(R.id.fly_view)
    FlyView flyView;
    @BindView(R.id.about_us_content)
    NestedScrollView nestScrollAbout;

    private View.OnClickListener updateThemeListener;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initEventAndData() {
        setSupportActionBar(toolbar);
        StatusBarUtils.immersive(this);
        StatusBarUtils.setPaddingSmart(this, toolbar);

        toolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
        aboutContent.setText(Html.fromHtml(getString(R.string.about_content)));
        aboutContent.setMovementMethod(LinkMovementMethod.getInstance());
        try {
            String aboutVersionStr = getString(R.string.app_name) + " V" + getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            aboutVersion.setText(aboutVersionStr);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        //绑定场景和纸飞机
        aboutUsFlyRefresh.setUp(mountainAbout, flyView);
        aboutUsRefreshLayout.setReboundInterpolator(new ElasticOutInterpolator());//设置回弹动画的插值器
        aboutUsRefreshLayout.setReboundDuration(800);//设置回弹动画的时间
        aboutUsRefreshLayout.setOnRefreshListener(refreshLayout -> {
                    updateTheme();
                    refreshLayout.finishRefresh(1000);
                }
        );

        //设置让Toolbar和AppBarLayout滚动同步 设置多功能监听
        aboutUsRefreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            //头部正在被拉伸
            @Override
            public void onHeaderPulling(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {
                syncToolbarAndAppbarLayout(offset);
            }

            //头部正在被释放
            @Override
            public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {
                syncToolbarAndAppbarLayout(offset);
            }
        });

        //进入界面自动刷新
        aboutUsRefreshLayout.autoRefresh();

        fabAbout.setOnClickListener(v -> aboutUsRefreshLayout.autoRefresh());


         //监听AppBarLayout的关闭和开启 给FlyView和Fab设置动画
        appbarAbout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean misAppbarExpand = true;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int scrollRange = appBarLayout.getTotalScrollRange();
                float fraction = 1f * (scrollRange + verticalOffset) / scrollRange;
                double minFraction = 0.1;
                double maxFraction = 0.8;
                if (fraction < minFraction && misAppbarExpand) {
                    misAppbarExpand = false;
                    fabAbout.animate().scaleX(0).scaleY(0);
                    flyView.animate().scaleX(0).scaleY(0);
                    ValueAnimator animator = ValueAnimator.ofInt(nestScrollAbout.getPaddingTop(), 0);
                    animator.setDuration(300);
                    animator.addUpdateListener(animation ->
                            nestScrollAbout.setPadding(0, (int) animation.getAnimatedValue(), 0, 0));
                    animator.start();
                }
                if (fraction > maxFraction && !misAppbarExpand) {
                    misAppbarExpand = true;
                    fabAbout.animate().scaleX(1).scaleY(1);
                    flyView.animate().scaleX(1).scaleY(1);
                    ValueAnimator animator = ValueAnimator.ofInt(nestScrollAbout.getPaddingTop(), DensityUtil.dp2px(25));
                    animator.setDuration(300);
                    animator.addUpdateListener(animation ->
                            nestScrollAbout.setPadding(0, (int) animation.getAnimatedValue(), 0, 0));
                    animator.start();
                }
            }
        });
    }

    private void syncToolbarAndAppbarLayout(int offset) {
        if (appbarAbout == null || toolbar == null) {
            return;
        }
        appbarAbout.setTranslationY(offset);
        toolbar.setTranslationY(-offset);
    }

    private void updateTheme() {
        if(updateThemeListener == null){
            updateThemeListener = new View.OnClickListener() {
                int index = 0;
                int[] ids = new int[]{
                        R.color.colorPrimary,
                        android.R.color.holo_green_light,
                        android.R.color.holo_red_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_blue_bright,
                };

                @Override
                public void onClick(View v) {
                    int color = ContextCompat.getColor(getApplication(),ids[index % ids.length]);
                    aboutUsRefreshLayout.setPrimaryColors(color);
                    fabAbout.setBackgroundColor(color);
                    fabAbout.setBackgroundTintList(ColorStateList.valueOf(color));
                    collapsingToolbar.setContentScrimColor(color);
                    index++;
                }
            };
        }

        updateThemeListener.onClick(null);
    }

}
