package com.dream.wanandroid.ui.main.fragment;

import com.dream.wanandroid.base.fragment.BaseDialogFragment;
import com.dream.wanandroid.contract.main.UsageDialogContract;
import com.dream.wanandroid.presenter.main.UsageDialogPresenter;

/**UsageDialogFragment
 * Created by Administrator on 2018/5/4.
 */

public class UsageDialogFragment extends BaseDialogFragment<UsageDialogPresenter> implements UsageDialogContract.View{


    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void reload() {

    }

    @Override
    protected void initEventAndData() {

    }


}
