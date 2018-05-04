package com.dream.wanandroid.ui.project.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dream.wanandroid.R;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleData;
import com.dream.wanandroid.ui.project.viewholder.ProjectListViewHolder;
import com.dream.wanandroid.utils.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2018/5/4.
 */

public class ProjectListAdapter extends BaseQuickAdapter<FeedArticleData,ProjectListViewHolder> {

    public ProjectListAdapter( @Nullable List<FeedArticleData> data) {
        super(R.layout.item_list_project, data);
    }

    @Override
    protected void convert(ProjectListViewHolder helper, FeedArticleData item) {
        if(!TextUtils.isEmpty(item.getEnvelopePic())){
            ImageLoader.load(mContext,item.getEnvelopePic(),helper.getView(R.id.iv_display));
        }

        if(!TextUtils.isEmpty(item.getTitle())){
            helper.setText(R.id.tv_project_name, item.getTitle());
        }

        if(!TextUtils.isEmpty(item.getDesc())){
            helper.setText(R.id.tv_project_des, item.getDesc());
        }

        if(!TextUtils.isEmpty(item.getNiceDate())){
            helper.setText(R.id.tv_time, item.getNiceDate());
        }

        if(!TextUtils.isEmpty(item.getAuthor())){
            helper.setText(R.id.tv_author, item.getAuthor());
        }


        helper.addOnClickListener(R.id.item_project_list_install_tv);

    }
}
