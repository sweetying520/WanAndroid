package com.dream.wanandroid.ui.main.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dream.wanandroid.R;
import com.dream.wanandroid.model.dao.HistoryData;
import com.dream.wanandroid.ui.main.viewholder.SearchHistoryViewHolder;
import com.dream.wanandroid.utils.CommonUtils;

import java.util.List;

/**SearchHistoryAdapter
 * Created by Administrator on 2018/5/8.
 */

public class SearchHistoryAdapter extends BaseQuickAdapter<HistoryData,SearchHistoryViewHolder>{

    public SearchHistoryAdapter(@Nullable List<HistoryData> data) {
        super(R.layout.item_search_history, data);
    }

    @Override
    protected void convert(SearchHistoryViewHolder helper, HistoryData item) {
        if(!TextUtils.isEmpty(item.getData())){
            helper.setText(R.id.tv_history, item.getData())
                    .setTextColor(R.id.tv_history, CommonUtils.randomColor());
        }
    }
}
