package com.dream.wanandroid.ui.project.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.dream.wanandroid.R;
import com.dream.wanandroid.base.fragment.AbstractRootFragment;
import com.dream.wanandroid.common.MyConstant;
import com.dream.wanandroid.contract.project.ProjectContract;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.project.ProjectTabData;
import com.dream.wanandroid.model.event.JumpToTopEvent;
import com.dream.wanandroid.presenter.project.ProjectPresenter;
import com.dream.wanandroid.utils.CommonUtils;
import com.dream.wanandroid.utils.RxBus;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.List;

import butterknife.BindView;

/**ProjectFragment
 * Created by Administrator on 2018/5/2.
 */

public class ProjectFragment extends AbstractRootFragment<ProjectPresenter> implements ProjectContract.View {


    @BindView(R.id.slide_tab)
    SlidingTabLayout slideTab;
    @BindView(R.id.project_viewpager)
    ViewPager projectViewpager;


    public static ProjectFragment getInstance(String params1, String params2) {
        ProjectFragment fragment = new ProjectFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MyConstant.ARG_PARAM1, params1);
        bundle.putString(MyConstant.ARG_PARAM2, params2);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();

        mPresenter.getProjectTagData();

        if(CommonUtils.isNetworkConnected()){
            showLoadingView();
        }
    }


    @Override
    public void showProjectTabData(BaseResponse<List<ProjectTabData>> listBaseResponse) {
        if(listBaseResponse == null || listBaseResponse.getData() == null){
            showProjectTabDataFailed();
            return;
        }

        projectViewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return listBaseResponse.getData().size();
            }

            @Override
            public Fragment getItem(int position) {
                return ProjectListFragment.getInstance(listBaseResponse.getData().get(position).getId(),null);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return listBaseResponse.getData().get(position).getName();
            }
        });
        slideTab.setViewPager(projectViewpager);
        showNormalView();
    }

    @Override
    public void showProjectTabDataFailed() {
        CommonUtils.showSnackMessage(_mActivity,getString(R.string.failed_to_obtain_project_classify_data));
    }

    public void jumpToTop(){
        RxBus.getDefault().post(new JumpToTopEvent());
    }

}
