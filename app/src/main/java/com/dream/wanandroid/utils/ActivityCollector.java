package com.dream.wanandroid.utils;

import android.app.Activity;

import java.util.HashSet;
import java.util.Set;

/**
 * Activity管理器
 * Created by Administrator on 2018/4/27.
 */

public class ActivityCollector {
    private static ActivityCollector mActivityCollector;
    private Set<Activity> activitySet;
    public static ActivityCollector getInstance() {
        if (mActivityCollector == null) {
            synchronized (ActivityCollector.class) {
                if (mActivityCollector == null) {
                    mActivityCollector = new ActivityCollector();
                }
            }

        }
        return mActivityCollector;
    }

    /**
     * 添加activity
     * @param mActivity
     */
    public void addActivity(Activity mActivity){
        if(activitySet == null){
            activitySet = new HashSet<>();
        }
        activitySet.add(mActivity);
    }


    public void removeActivity(Activity mActivity){
        if(activitySet != null){
            activitySet.remove(mActivity);
        }
    }

    public void exitApp(){
        if(activitySet != null){
            synchronized (activitySet){
                for (Activity activity : activitySet) {
                    activity.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }


}
