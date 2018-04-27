package com.dream.wanandroid.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;


import com.dream.wanandroid.utils.ActivityCollector;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by Administrator on 2018/4/27.
 */

public abstract class AbstractSimpleActivity extends SupportActivity{
    private Unbinder unbinder;
    protected AbstractSimpleActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        mActivity = this;
        onViewCreated();
        ActivityCollector.getInstance().addActivity(this);
        initEventAndData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unbinder != null){
            unbinder.unbind();
        }
        ActivityCollector.getInstance().removeActivity(this);
    }

    /**
     * 初始化数据
     */
    protected abstract void initEventAndData();

    protected void onViewCreated() {
    }

    protected void setToolBar(Toolbar toolBar, CharSequence title){
        toolBar.setTitle(title);
        setSupportActionBar(toolBar);
        assert getSupportActionBar() != null;

    }

    /**
     * 获取当前activity的布局id
     * @return 布局id
     */
    protected abstract int getLayoutId();
}
