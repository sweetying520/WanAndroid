package com.dream.wanandroid.ui.hierarchy.activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;

import com.dream.wanandroid.R;
import com.dream.wanandroid.base.activity.BaseActivity;
import com.dream.wanandroid.common.MyConstant;
import com.dream.wanandroid.contract.hierarchy.KnowledgeHierarchyDetailContract;
import com.dream.wanandroid.model.bean.hierarchy.HierarchyData;
import com.dream.wanandroid.model.event.JumpToTopEvent;
import com.dream.wanandroid.presenter.hierarchy.KnowledgeHierarchyDetailPresenter;
import com.dream.wanandroid.ui.hierarchy.fragment.KnowledgeHierarchyListFragment;
import com.dream.wanandroid.utils.RxBus;
import com.dream.wanandroid.utils.StatusBarUtils;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**KnowledgeHierarchyDetailActivity
 * Created by Administrator on 2018/5/9.
 */

public class KnowledgeHierarchyDetailActivity extends BaseActivity<KnowledgeHierarchyDetailPresenter> implements KnowledgeHierarchyDetailContract.View {

    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_knowledge_hierarchy)
    SlidingTabLayout mTab;
    @BindView(R.id.vp_knowledge_hierarchy)
    ViewPager vpKnowledgeHierarchy;
    private Context mContext;
    private HierarchyData hierarchyData;
    private List<Fragment> fragmentList;


    @Override
    protected void initEventAndData() {
        mContext = this;
        initToolbar();

        vpKnowledgeHierarchy.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return hierarchyData.getChildren().get(position).getName();
            }
        });

        mTab.setViewPager(vpKnowledgeHierarchy);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        StatusBarUtils.immersive(this);
        StatusBarUtils.setPaddingSmart(mContext,toolbar);

        hierarchyData = (HierarchyData) getIntent().getExtras().getSerializable(MyConstant.ARG_PARAM1);
        if(!TextUtils.isEmpty(hierarchyData.getName())){
            tvToolbarTitle.setText(hierarchyData.getName());
        }
        fragmentList = new ArrayList<>();
        for (HierarchyData.ChildrenBean childrenBean : hierarchyData.getChildren()) {
            fragmentList.add(KnowledgeHierarchyListFragment.getInstance(childrenBean.getId(),null));
        }

        toolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail_knowledge_hierarchy;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }



    @OnClick(R.id.fab_knowledge_hierarchy)
    public void onClick() {
        RxBus.getDefault().post(new JumpToTopEvent());
    }

    public static void start(Context mContext, ActivityOptions activityOptions, HierarchyData hierarchyData){
        Intent intent = new Intent(mContext, KnowledgeHierarchyDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(MyConstant.ARG_PARAM1,hierarchyData);
        intent.putExtras(bundle);
        mContext.startActivity(intent,activityOptions.toBundle());

    }
}
