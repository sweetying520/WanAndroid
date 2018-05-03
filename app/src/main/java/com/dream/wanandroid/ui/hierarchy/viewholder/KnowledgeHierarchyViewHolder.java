package com.dream.wanandroid.ui.hierarchy.viewholder;

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

public class KnowledgeHierarchyViewHolder extends BaseViewHolder {


    @BindView(R.id.iv_enter)
    ImageView ivEnter;
    @BindView(R.id.tv_hierarchy_title)
    TextView tvHierarchyTitle;
    @BindView(R.id.tv_hierarchy_child)
    TextView tvHierarchyChild;

    public KnowledgeHierarchyViewHolder(View view) {
        super(view);
        ButterKnife.bind(this,view);
    }
}
