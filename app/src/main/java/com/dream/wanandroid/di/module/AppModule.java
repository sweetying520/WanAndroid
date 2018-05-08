package com.dream.wanandroid.di.module;

import com.dream.wanandroid.WanAndroidApp;
import com.dream.wanandroid.model.DataManager;
import com.dream.wanandroid.model.db.DbHelper;
import com.dream.wanandroid.model.db.GreenDaoHelper;
import com.dream.wanandroid.model.http.HttpHelper;
import com.dream.wanandroid.model.http.RetrofitHelper;
import com.dream.wanandroid.model.prefs.PreferenceHelper;
import com.dream.wanandroid.model.prefs.PreferenceHelperImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**AppModule
 * Created by Administrator on 2018/4/28.
 */

@Module
public class AppModule {
    private final WanAndroidApp mWanAndroidApp;

    public AppModule(WanAndroidApp mWanAndroidApp) {
        this.mWanAndroidApp = mWanAndroidApp;
    }

    @Provides
    @Singleton
    WanAndroidApp provideApplicationContext(){
        return mWanAndroidApp;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper mRetrofitHelper){
        return mRetrofitHelper;
    }

    @Provides
    @Singleton
    PreferenceHelper providePreferenceHelper(PreferenceHelperImpl implPreferencesHelper){
        return implPreferencesHelper;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(GreenDaoHelper greenDaoHelper){
        return greenDaoHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, PreferenceHelper preferenceHelper,DbHelper dbHelper){
        return new DataManager(httpHelper,preferenceHelper,dbHelper);
    }




}
