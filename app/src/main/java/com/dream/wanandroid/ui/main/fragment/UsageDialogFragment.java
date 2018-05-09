package com.dream.wanandroid.ui.main.fragment;

import android.app.ActivityOptions;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.dream.wanandroid.R;
import com.dream.wanandroid.base.fragment.BaseDialogFragment;
import com.dream.wanandroid.contract.main.UsageDialogContract;
import com.dream.wanandroid.model.bean.BaseResponse;
import com.dream.wanandroid.model.bean.main.often.OftenUseData;
import com.dream.wanandroid.presenter.main.UsageDialogPresenter;
import com.dream.wanandroid.ui.main.activity.ArticleDetailActivity;
import com.dream.wanandroid.utils.CommonUtils;
import com.dream.wanandroid.widget.CircularRevealAnim;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;

/**
 * UsageDialogFragment
 * Created by Administrator on 2018/5/4.
 */

public class UsageDialogFragment extends BaseDialogFragment<UsageDialogPresenter> implements UsageDialogContract.View, CircularRevealAnim.AnimListener, ViewTreeObserver.OnPreDrawListener {


    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fl_usage)
    TagFlowLayout flUsage;
    private CircularRevealAnim circularRevealAnim;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.DialogStyle);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_usage;
    }


    @Override
    public void onStart() {
        super.onStart();
        initDialog();
    }

    private void initDialog() {
        Window window = getDialog().getWindow();
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        assert window != null;
        window.setLayout((int) (widthPixels * 0.98), WindowManager.LayoutParams.MATCH_PARENT);
        window.setGravity(Gravity.TOP);
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        initCircularRevealAnim();
        initToolbar();

        mPresenter.getUsageData();
    }

    private void initCircularRevealAnim() {
        circularRevealAnim = new CircularRevealAnim();
        circularRevealAnim.setAnimListener(this);
        tvToolbarTitle.getViewTreeObserver().addOnPreDrawListener(this);
    }

    @Override
    public boolean onPreDraw() {
        tvToolbarTitle.getViewTreeObserver().removeOnPreDrawListener(this);
        circularRevealAnim.show(tvToolbarTitle, rootView);
        return true;
    }

    @Override
    public void onHideAnimationEnd() {
        dismissAllowingStateLoss();
    }

    @Override
    public void onShowAnimationEnd() {

    }

    private void initToolbar() {
        toolbar.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));
        toolbar.setNavigationIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_arrow_back_grey_24dp));
        tvToolbarTitle.setText(getString(R.string.useful_sites));
        tvToolbarTitle.setTextColor(ContextCompat.getColor(getActivity(), R.color.title_black));
        toolbar.setNavigationOnClickListener(v -> circularRevealAnim.hide(tvToolbarTitle, rootView));
    }

    @Override
    public void showUsageData(BaseResponse<List<OftenUseData>> listBaseResponse) {
        if (listBaseResponse == null || listBaseResponse.getData() == null) {
            showUsageDataFailed();
            return;
        }

        flUsage.setAdapter(new TagAdapter<OftenUseData>(listBaseResponse.getData()) {

            @Override
            public View getView(FlowLayout parent, int position, OftenUseData oftenUseData) {
                TextView tvTag = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.item_flowlayout_tag, parent, false);
                tvTag.setText(oftenUseData.getName());
                tvTag.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                tvTag.setBackgroundColor(CommonUtils.randomTagColor());
                flUsage.setOnTagClickListener((view, position1, parent1) -> {
                    ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(getActivity(), view, getString(R.string.share_view));
                    ArticleDetailActivity.start(
                            getActivity(),
                            activityOptions,
                            listBaseResponse.getData().get(position1).getId(),
                            listBaseResponse.getData().get(position1).getName(),
                            listBaseResponse.getData().get(position1).getLink(),
                            false,
                            false,
                            true

                    );
                    return true;
                });
                return tvTag;
            }
        });
    }

    @Override
    public void showUsageDataFailed() {
        CommonUtils.showSnackMessage(getActivity(), getString(R.string.failed_to_obtain_useful_sites_data));
    }


}
