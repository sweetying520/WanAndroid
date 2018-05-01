package com.dream.wanandroid.ui.main.activity;

import android.content.Intent;

import com.airbnb.lottie.LottieAnimationView;
import com.dream.wanandroid.R;
import com.dream.wanandroid.WanAndroidApp;
import com.dream.wanandroid.base.activity.BaseActivity;
import com.dream.wanandroid.contract.main.SplashContract;
import com.dream.wanandroid.presenter.main.SplashPresenter;
import com.dream.wanandroid.ui.mainpager.activity.MainActivity;
import com.dream.wanandroid.utils.StatusBarUtil;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/5/1.
 */

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {


    @BindView(R.id.one_animation)
    LottieAnimationView mOneAnimation;
    @BindView(R.id.two_animation)
    LottieAnimationView mTwoAnimation;
    @BindView(R.id.three_animation)
    LottieAnimationView mThreeAnimation;
    @BindView(R.id.four_animation)
    LottieAnimationView mFourAnimation;
    @BindView(R.id.five_animation)
    LottieAnimationView mFiveAnimation;
    @BindView(R.id.six_animation)
    LottieAnimationView mSixAnimation;
    @BindView(R.id.seven_animation)
    LottieAnimationView mSevenAnimation;
    @BindView(R.id.eight_animation)
    LottieAnimationView mEightAnimation;
    @BindView(R.id.nine_animation)
    LottieAnimationView mNineAnimation;
    @BindView(R.id.ten_animation)
    LottieAnimationView mTenAnimation;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        if(WanAndroidApp.isFirstRun){
            jumpToMain();
            return;
        }

        StatusBarUtil.setTranslucentForImageView(this,0,null);
        WanAndroidApp.isFirstRun = true;
        mOneAnimation.setAnimation("W.json");
        mOneAnimation.playAnimation();
        mTwoAnimation.setAnimation("A.json");
        mTwoAnimation.playAnimation();
        mThreeAnimation.setAnimation("N.json");
        mThreeAnimation.playAnimation();
        mFourAnimation.setAnimation("A.json");
        mFourAnimation.playAnimation();
        mFiveAnimation.setAnimation("N.json");
        mFiveAnimation.playAnimation();
        mSixAnimation.setAnimation("D.json");
        mSixAnimation.playAnimation();
        mSevenAnimation.setAnimation("R.json");
        mSevenAnimation.playAnimation();
        mEightAnimation.setAnimation("I.json");
        mEightAnimation.playAnimation();
        mNineAnimation.setAnimation("O.json");
        mNineAnimation.playAnimation();
        mTenAnimation.setAnimation("D.json");
        mTenAnimation.playAnimation();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void jumpToMain() {
       startActivity(new Intent(this, MainActivity.class));
       finish();
       overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    private void cancelAnimation(){
        if (mOneAnimation != null) {
            mOneAnimation.cancelAnimation();
        }
        if (mTwoAnimation != null) {
            mTwoAnimation.cancelAnimation();
        }
        if (mThreeAnimation != null) {
            mThreeAnimation.cancelAnimation();
        }
        if (mFourAnimation != null) {
            mFourAnimation.cancelAnimation();
        }
        if (mFiveAnimation != null) {
            mFiveAnimation.cancelAnimation();
        }
        if (mSixAnimation != null) {
            mSixAnimation.cancelAnimation();
        }
        if (mSevenAnimation != null) {
            mSevenAnimation.cancelAnimation();
        }
        if (mEightAnimation != null) {
            mEightAnimation.cancelAnimation();
        }
        if (mNineAnimation != null) {
            mNineAnimation.cancelAnimation();
        }
        if (mTenAnimation != null) {
            mTenAnimation.cancelAnimation();
        }
    }

    @Override
    protected void onDestroy() {
        cancelAnimation();
        super.onDestroy();
    }
}