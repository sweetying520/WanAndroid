package com.dream.wanandroid.di.component;

import android.app.Activity;

import com.dream.wanandroid.di.module.FragmentModule;
import com.dream.wanandroid.di.scope.FragmentScope;
import com.dream.wanandroid.ui.hierarchy.fragment.KnowledgeHierarchyFragment;
import com.dream.wanandroid.ui.like.fragment.LikeFragment;
import com.dream.wanandroid.ui.main.fragment.SearchDialogFragment;
import com.dream.wanandroid.ui.main.fragment.UsageDialogFragment;
import com.dream.wanandroid.ui.mainpager.fragment.HomePagerFragment;
import com.dream.wanandroid.ui.navigation.fragment.NavigationFragment;
import com.dream.wanandroid.ui.project.fragment.ProjectFragment;
import com.dream.wanandroid.ui.project.fragment.ProjectListFragment;

import dagger.Component;

/**FragmentComponent
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
     * @param homePagerFragment homePagerFragment
     */
    void inject(HomePagerFragment homePagerFragment);

    /**
     *
     * @param knowledgeHierarchyFragment knowledgeHierarchyFragment
     */
    void inject(KnowledgeHierarchyFragment knowledgeHierarchyFragment);

    /**
     *
     * @param navigationFragment navigationFragment
     */
    void inject(NavigationFragment navigationFragment);

    /**
     *
     * @param projectFragment projectFragment
     */
    void inject(ProjectFragment projectFragment);


    /**
     *
     * @param projectListFragment projectListFragment
     */
    void inject(ProjectListFragment projectListFragment);

    /**
     *
     * @param usageDialogFragment usageDialogFragment
     */
    void inject(UsageDialogFragment usageDialogFragment);


    /**
     *
     * @param searchDialogFragment searchDialogFragment
     */
    void inject(SearchDialogFragment searchDialogFragment);

    /**
     *
     * @param likeFragment likeFragment
     */
    void inject(LikeFragment likeFragment);


}
