package com.dream.wanandroid.ui.navigation.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dream.wanandroid.R;
import com.dream.wanandroid.model.bean.main.collect.FeedArticleData;
import com.dream.wanandroid.model.bean.navigation.NavigationData;
import com.dream.wanandroid.ui.navigation.viewholder.NavigationViewHolder;
import com.dream.wanandroid.utils.CommonUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * Created by Administrator on 2018/5/4.
 */

public class NavigationAdapter extends BaseQuickAdapter<NavigationData,NavigationViewHolder>{

    public NavigationAdapter( @Nullable List<NavigationData> data) {
        super(R.layout.item_navigation, data);
    }

    @Override
    protected void convert(NavigationViewHolder helper, NavigationData item) {
        if(!TextUtils.isEmpty(item.getName())){
            helper.setText(R.id.tv_navigation,item.getName());
        }

        List<FeedArticleData> articleDataList = item.getArticles();
        ((TagFlowLayout)helper.getView(R.id.tag_fl_navigation)).setAdapter(new TagAdapter<FeedArticleData>(articleDataList) {
            @Override
            public View getView(FlowLayout parent, int position, FeedArticleData feedArticleData) {
                TextView tvFlTag = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_flowlayout_tag,parent,false);
                tvFlTag.setText(feedArticleData.getTitle());
                tvFlTag.setPadding(CommonUtils.dp2px(10),CommonUtils.dp2px(10),CommonUtils.dp2px(10),CommonUtils.dp2px(10));
                tvFlTag.setTextColor(CommonUtils.randomColor());
                return tvFlTag;
            }
        });
    }
}
