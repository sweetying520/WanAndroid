package com.dream.wanandroid.ui.project.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.dream.wanandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/4.
 */

public class ProjectListViewHolder extends BaseViewHolder {


    @BindView(R.id.iv_display)
    ImageView ivDisplay;
    @BindView(R.id.tv_project_name)
    TextView tvProjectName;
    @BindView(R.id.tv_project_des)
    TextView tvProjectDes;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.item_project_list_install_tv)
    TextView itemProjectListInstallTv;

    public ProjectListViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
