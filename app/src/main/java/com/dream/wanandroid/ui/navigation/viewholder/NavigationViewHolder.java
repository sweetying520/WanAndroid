package com.dream.wanandroid.ui.navigation.viewholder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.dream.wanandroid.R;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/4.
 */

public class NavigationViewHolder extends BaseViewHolder {

    @BindView(R.id.tv_navigation)
    TextView tvNavigation;
    @BindView(R.id.tag_fl_navigation)
    TagFlowLayout tagFlNavigation;

    public NavigationViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
