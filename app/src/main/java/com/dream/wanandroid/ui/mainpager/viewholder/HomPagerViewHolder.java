package com.dream.wanandroid.ui.mainpager.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.dream.wanandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/3.
 */

public class HomPagerViewHolder extends BaseViewHolder {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_subject)
    TextView tvSubject;
    @BindView(R.id.tv_summary)
    TextView tvSummary;
    @BindView(R.id.tv_is_project)
    TextView tvIsProject;
    @BindView(R.id.tv_is_new)
    TextView tvIsNew;
    @BindView(R.id.iv_like)
    ImageView ivLike;
    @BindView(R.id.tv_time)
    TextView tvTime;

    public HomPagerViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
