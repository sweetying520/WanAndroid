package com.dream.wanandroid.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.dream.wanandroid.R;
import com.dream.wanandroid.base.fragment.BaseFragment;
import com.dream.wanandroid.common.MyConstant;
import com.dream.wanandroid.contract.main.SettingContract;
import com.dream.wanandroid.presenter.main.SettingPresenter;
import com.dream.wanandroid.utils.ACache;
import com.dream.wanandroid.utils.ShareUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设置界面
 * Created by Administrator on 2018/5/9.
 */

public class SettingFragment extends BaseFragment<SettingPresenter> implements SettingContract.View, CompoundButton.OnCheckedChangeListener {


    @BindView(R.id.cb_auto_cache)
    AppCompatCheckBox cbAutoCache;
    @BindView(R.id.cb_no_image_mode)
    AppCompatCheckBox cbNoImageMode;
    @BindView(R.id.cb_setting_night_mode)
    AppCompatCheckBox cbSettingNightMode;
    @BindView(R.id.tv_setting_clean_size)
    TextView tvSettingCleanSize;
    private File cacheFile;


    public static SettingFragment getInstance(String params1, String params2) {
        SettingFragment fragment = new SettingFragment();
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
        return R.layout.fragment_setting;
    }

    @Override
    protected void initEventAndData() {
        cacheFile = new File(MyConstant.PATH_CACHE);
        tvSettingCleanSize.setText(ACache.getCacheSize(cacheFile));
        cbAutoCache.setOnCheckedChangeListener(this);
        cbNoImageMode.setOnCheckedChangeListener(this);
        cbSettingNightMode.setOnCheckedChangeListener(this);
        cbAutoCache.setChecked(mPresenter.getAutoCacheState());
        cbNoImageMode.setChecked(mPresenter.getNoImageState());
        cbSettingNightMode.setChecked(mPresenter.getNightModeState());
    }


    @OnClick({R.id.tv_setting_feed_back, R.id.ll_setting_clean})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_setting_feed_back:
                ShareUtil.sendEmail(_mActivity,getString(R.string.send_email));
                break;
            case R.id.ll_setting_clean:
                ACache.deleteDir(cacheFile);
                tvSettingCleanSize.setText(ACache.getCacheSize(cacheFile));
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_auto_cache:
                mPresenter.setAutoCacheState(isChecked);
                break;
            case R.id.cb_no_image_mode:
                mPresenter.setNoImageState(isChecked);
                break;
            case R.id.cb_setting_night_mode:
                mPresenter.setNightMode(isChecked);
                break;
            default:
                break;
        }
    }
}
