package com.dream.wanandroid.di.component;

import android.app.Activity;

import com.dream.wanandroid.di.module.FragmentModule;
import com.dream.wanandroid.di.scope.FragmentScope;
import com.dream.wanandroid.ui.hierarchy.fragment.KnowledgeHierarchyFragment;
import com.dream.wanandroid.ui.mainpager.fragment.HomePagerFragment;
import com.dream.wanandroid.ui.navigation.fragment.NavigationFragment;
import com.dream.wanandroid.ui.project.fragment.ProjectFragment;

import dagger.Component;

/**
 * Created by Administrator on 2018/4/28.
 */

@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {

    /**
     * 获取Activity的实例
     */
    Activity getActivity();


    /**
     *
     * @param homePagerFragment
     */
    void inject(HomePagerFragment homePagerFragment);

    /**
     *
     * @param knowledgeHierarchyFragment
     */
    void inject(KnowledgeHierarchyFragment knowledgeHierarchyFragment);

    /**
     *
     * @param navigationFragment
     */
    void inject(NavigationFragment navigationFragment);

    /**
     *
     * @param projectFragment
     */
    void inject(ProjectFragment projectFragment);


}
