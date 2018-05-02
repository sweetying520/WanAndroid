package com.dream.wanandroid.base.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.dream.wanandroid.R;
import com.dream.wanandroid.base.presenter.BasePresenter;

/**
 * Created by Administrator on 2018/4/27.
 */

public abstract class AbstractRootFragment<P extends BasePresenter> extends BaseFragment<P> {
    private static final int NORMAL_STATE = 0;
    private static final int LOADING_STATE = 1;
    private static final int ERROR_STATE = 2;

    private LottieAnimationView mLottieAnimationView;
    private View mLoadingView;
    private ViewGroup mNormalView;

    private int currentState = NORMAL_STATE;
    private View mErrorView;

    @Override
    protected void initEventAndData() {
        if(getView() == null){
            return;
        }

        mNormalView = getView().findViewById(R.id.normal_view);
        if (mNormalView == null) {
            throw new IllegalStateException(
                    "The subclass of RootActivity must contain a View named 'mNormalView'."
            );
        }

        if (!(mNormalView.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException(
                    "mNormalView's ParentView should be a ViewGroup."
            );
        }

        ViewGroup mParent = (ViewGroup) mNormalView.getParent();
        View.inflate(_mActivity, R.layout.loading_view, mParent);
        View.inflate(_mActivity, R.layout.error_view, mParent);
        mLoadingView = mParent.findViewById(R.id.loading_group);
        mErrorView = mParent.findViewById(R.id.error_group);
        TextView tvReload = mErrorView.findViewById(R.id.tv_error_reload);
        tvReload.setOnClickListener(v -> reload());
        mLottieAnimationView = mLoadingView.findViewById(R.id.loading_view);
        mErrorView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
        mNormalView.setVisibility(View.VISIBLE);

    }

    @Override
    public void showNormalView() {
        if(currentState == NORMAL_STATE){
            return;
        }
        hideCurrentView();
        currentState = NORMAL_STATE;
        mNormalView.setVisibility(View.VISIBLE);
    }

    private void hideCurrentView(){
        switch (currentState){
            case LOADING_STATE:
                mLottieAnimationView.cancelAnimation();
                mNormalView.setVisibility(View.GONE);
                break;
            case ERROR_STATE:
                mErrorView.setVisibility(View.GONE);
                break;
            case NORMAL_STATE:
                mNormalView.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void showLoadingView() {
        if(currentState == LOADING_STATE){
            return;
        }
        hideCurrentView();
        currentState = LOADING_STATE;
        mLoadingView.setVisibility(View.VISIBLE);
        mLottieAnimationView.setAnimation("loading_bus.json");
        mLottieAnimationView.loop(true);
        mLottieAnimationView.playAnimation();

    }

    @Override
    public void showErrorView() {
        if(currentState == ERROR_STATE){
            return;
        }
        hideCurrentView();
        currentState = ERROR_STATE;
        mErrorView.setVisibility(View.VISIBLE);
    }

    /**
     * 重新加载
     */
    @Override
    public void reload() {}


}
