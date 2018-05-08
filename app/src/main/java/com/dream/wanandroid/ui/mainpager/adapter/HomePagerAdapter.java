package com.dream.wanandroid.ui.mainpager.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dream.wanandroid.R;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleData;
import com.dream.wanandroid.ui.mainpager.viewholder.HomPagerViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/5/3.
 */

public class HomePagerAdapter extends BaseQuickAdapter<FeedArticleData, HomPagerViewHolder> {


    public HomePagerAdapter(int layoutResId, @Nullable List<FeedArticleData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(HomPagerViewHolder helper, FeedArticleData item) {
        if (!TextUtils.isEmpty(item.getAuthor())) {
            helper.setText(R.id.tv_title, Html.fromHtml(item.getAuthor()));
        }

        if (!TextUtils.isEmpty(item.getSuperChapterName()) && !TextUtils.isEmpty(item.getChapterName())) {
            helper.setText(R.id.tv_subject, item.getSuperChapterName() + " / " + item.getChapterName());
        }

        if (!TextUtils.isEmpty(item.getTitle())) {
            helper.setText(R.id.tv_summary, Html.fromHtml(item.getTitle()));
        }

        if (!TextUtils.isEmpty(item.getNiceDate())) {
            helper.setText(R.id.tv_time, item.getNiceDate());
        }

        if (item.isCollect()) {
            helper.setImageResource(R.id.iv_like, R.drawable.icon_like);
        } else {
            helper.setImageResource(R.id.iv_like, R.drawable.icon_like_article_not_selected);
        }

        setTag(helper, item);

        helper.addOnClickListener(R.id.iv_like);
        helper.addOnClickListener(R.id.tv_subject);
        helper.addOnClickListener(R.id.tv_is_project);

    }

    private void setTag(HomPagerViewHolder helper, FeedArticleData item) {
        helper.getView(R.id.tv_is_project).setVisibility(View.GONE);
        helper.getView(R.id.tv_is_new).setVisibility(View.GONE);

        if (item.getSuperChapterName().contains(mContext.getString(R.string.open_project))) {
            helper.getView(R.id.tv_is_project).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_is_project, R.string.open_project);
            helper.setTextColor(R.id.tv_is_project, ContextCompat.getColor(mContext, R.color.light_deep_red));
            helper.setBackgroundRes(R.id.tv_is_project, R.drawable.shape_rectangle_stroke_light_deep_red);
        }

        if (item.getSuperChapterName().contains(mContext.getString(R.string.navigation))) {
            helper.getView(R.id.tv_is_project).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_is_project, R.string.navigation);
            helper.setTextColor(R.id.tv_is_project, ContextCompat.getColor(mContext, R.color.light_deep_red));
            helper.setBackgroundRes(R.id.tv_is_project, R.drawable.shape_rectangle_stroke_light_deep_red);
        }

        if (item.getNiceDate().contains(mContext.getString(R.string.minute))
                || item.getNiceDate().contains(mContext.getString(R.string.hour))
                || item.getNiceDate().contains(mContext.getString(R.string.one_day))) {
            helper.getView(R.id.tv_is_new).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_is_new, R.string.text_new);
            helper.setTextColor(R.id.tv_is_new, ContextCompat.getColor(mContext, R.color.light_green));
            helper.setBackgroundRes(R.id.tv_is_new, R.drawable.shape_rectangle_stroke_light_green);
        }

    }
}
