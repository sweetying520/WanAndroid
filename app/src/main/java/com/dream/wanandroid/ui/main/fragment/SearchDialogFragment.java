package com.dream.wanandroid.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.TextView;

import com.dream.wanandroid.R;
import com.dream.wanandroid.base.fragment.BaseDialogFragment;
import com.dream.wanandroid.common.MyConstant;
import com.dream.wanandroid.contract.main.SearchDialogContract;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.main.search.HotSearchData;
import com.dream.wanandroid.presenter.main.SearchDialogPresenter;
import com.dream.wanandroid.utils.CommonUtils;
import com.dream.wanandroid.utils.KeyBoardUtils;
import com.dream.wanandroid.widget.CircularRevealAnim;
import com.jakewharton.rxbinding2.view.RxView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * SearchDialogFragment
 * Created by Administrator on 2018/5/4.
 */

public class SearchDialogFragment extends BaseDialogFragment<SearchDialogPresenter> implements SearchDialogContract.View, CircularRevealAnim.AnimListener, ViewTreeObserver.OnPreDrawListener {


    @BindView(R.id.edit_search)
    TextInputEditText editSearch;
    @BindView(R.id.fl_search)
    TagFlowLayout flSearch;
    @BindView(R.id.tv_clear)
    TextView tvClear;
    @BindView(R.id.tv_search_null)
    TextView tvSearchNull;
    @BindView(R.id.search_rv)
    RecyclerView searchRv;
    @BindView(R.id.tv_hint)
    TextView tvHint;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    private CircularRevealAnim circularRevealAnim;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        initCircularRevealAnim();
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString())) {
                    tvHint.setText(R.string.search_tint);
                } else {
                    tvHint.setText("");
                }
            }
        });

        mPresenter.addRxBindingSubscribe(RxView.clicks(tvSearch)
                .throttleFirst(MyConstant.CLICK_TIME_AREA, TimeUnit.MILLISECONDS)
                .filter(o -> !TextUtils.isEmpty(editSearch.getText().toString().trim()))
                .subscribe(o -> CommonUtils.showMessage(getActivity(), "等待开通")));

        mPresenter.getHotSearchData();
    }

    private void initCircularRevealAnim() {
        circularRevealAnim = new CircularRevealAnim();
        circularRevealAnim.setAnimListener(this);
        editSearch.getViewTreeObserver().addOnPreDrawListener(this);
    }

    @Override
    public boolean onPreDraw() {
        editSearch.getViewTreeObserver().removeOnPreDrawListener(this);
        circularRevealAnim.show(editSearch,rootView);
        return true;
    }

    @Override
    public void onHideAnimationEnd() {
        editSearch.setText("");
        dismissAllowingStateLoss();
    }

    private void onBackEvent(){
        KeyBoardUtils.closeKeyboard(getActivity(),editSearch);
        circularRevealAnim.hide(editSearch,rootView);
    }

    @Override
    public void onShowAnimationEnd() {
        KeyBoardUtils.openKeyboard(getActivity(),editSearch);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dialog_search;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.DialogStyle);
    }

    @Override
    public void onStart() {
        super.onStart();
        initDialog();
    }

    /**
     * 初始化Dialog
     */
    private void initDialog() {
        Window window = getDialog().getWindow();
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        assert window != null;
        window.setLayout((int) (widthPixels * 0.98), ViewGroup.LayoutParams.MATCH_PARENT);
        window.setGravity(Gravity.TOP);
        window.setWindowAnimations(R.style.DialogStyleAnimation);
    }

    @Override
    public void showHotSearchData(BaseResponse<List<HotSearchData>> listBaseResponse) {
        if (listBaseResponse == null || listBaseResponse.getData() == null) {
            showHotSearchDataFailed();
            return;
        }

        flSearch.setAdapter(new TagAdapter<HotSearchData>(listBaseResponse.getData()) {
            @Override
            public View getView(FlowLayout parent, int position, HotSearchData hotSearchData) {
                TextView tvTag = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.item_flowlayout_tag, parent, false);
                tvTag.setText(hotSearchData.getName());
                assert getActivity() != null;
                tvTag.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                tvTag.setBackgroundColor(CommonUtils.randomTagColor());
                return tvTag;
            }
        });


    }

    @Override
    public void showHotSearchDataFailed() {
        CommonUtils.showSnackMessage(getActivity(), getString(R.string.failed_to_obtain_top_data));
    }

    @OnClick({R.id.iv_back, R.id.tv_search, R.id.fab_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
               onBackEvent();
                break;
            case R.id.tv_search:
                break;
            case R.id.fab_search:
                break;
        }
    }
}
