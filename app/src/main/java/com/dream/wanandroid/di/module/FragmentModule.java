package com.dream.wanandroid.di.module;

import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import com.dream.wanandroid.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/4/28.
 */

@Module
public class FragmentModule {
    private Fragment fragment;
    private DialogFragment dialogFragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
        dialogFragment = null;
    }

    public FragmentModule(DialogFragment dialogFragment) {
        this.dialogFragment = dialogFragment;
        fragment = null;
    }

    @Provides
    @FragmentScope
    Activity provideActivity(){
        if(fragment == null){
            return dialogFragment.getActivity();
        }else {
            return fragment.getActivity();
        }
    }
}
