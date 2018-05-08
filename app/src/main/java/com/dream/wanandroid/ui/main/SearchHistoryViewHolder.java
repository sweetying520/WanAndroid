package com.dream.wanandroid.ui.main;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.dream.wanandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/8.
 */

public class SearchHistoryViewHolder extends BaseViewHolder {


    @BindView(R.id.tv_history)
    TextView tvHistory;

    public SearchHistoryViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
