package com.dream.wanandroid.ui.main.fragment;

import com.dream.wanandroid.R;
import com.dream.wanandroid.base.fragment.BaseDialogFragment;
import com.dream.wanandroid.contract.main.SearchDialogContract;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.main.search.HotSearchData;
import com.dream.wanandroid.presenter.main.SearchDialogPresenter;
import com.dream.wanandroid.utils.CommonUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/5/4.
 */

public class SearchDialogFragment extends BaseDialogFragment<SearchDialogPresenter> implements SearchDialogContract.View{



    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void showHotSearchData(BaseResponse<List<HotSearchData>> listBaseResponse) {
        if(listBaseResponse == null || listBaseResponse.getData() == null){
            showHotSearchDataFailed();
            return;
        }


    }

    @Override
    public void showHotSearchDataFailed() {
        CommonUtils.showSnackMessage(getActivity(),getString(R.string.failed_to_obtain_top_data));
    }
}
