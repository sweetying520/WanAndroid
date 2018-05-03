package com.dream.wanandroid.ui.hierarchy.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dream.wanandroid.R;
import com.dream.wanandroid.model.bean.hierarchy.HierarchyData;
import com.dream.wanandroid.ui.hierarchy.viewholder.KnowledgeHierarchyViewHolder;
import com.dream.wanandroid.utils.CommonUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/5/3.
 */

public class KnowledgeHierarchyAdapter extends BaseQuickAdapter<HierarchyData,KnowledgeHierarchyViewHolder>{

    public KnowledgeHierarchyAdapter(@Nullable List<HierarchyData> data) {
        super(R.layout.item_knowledge_hierarchy, data);
    }

    @Override
    protected void convert(KnowledgeHierarchyViewHolder helper, HierarchyData item) {
        if(!TextUtils.isEmpty(item.getName())){
            helper.setText(R.id.tv_hierarchy_title,item.getName());
            helper.setTextColor(R.id.tv_hierarchy_title, CommonUtils.randomTagColor());
        }

        if(item.getChildren() != null && item.getChildren().size() > 0){
            StringBuilder childStr = new StringBuilder();
            for (HierarchyData.ChildrenBean childrenBean : item.getChildren()) {
                childStr.append(childrenBean.getName()).append("  ");
            }
            helper.setText(R.id.tv_hierarchy_child,childStr);
        }
    }
}
