package com.dream.wanandroid.di.module;

import com.dream.wanandroid.BuildConfig;
import com.dream.wanandroid.common.MyConstant;
import com.dream.wanandroid.di.qualifier.WanAndroidUrl;
import com.dream.wanandroid.model.http.api.WanAndroidApi;
import com.dream.wanandroid.model.http.cookies.CookiesManager;
import com.dream.wanandroid.utils.CommonUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/4/28.
 */

@Module
public class HttpModule {

    @Provides
    @Singleton
    WanAndroidApi provideWanAndroidApi(@WanAndroidUrl Retrofit retrofit){
        return retrofit.create(WanAndroidApi.class);
    }


    @Singleton
    @Provides
    @WanAndroidUrl
    Retrofit provideRetrofit(Retrofit.Builder builder,OkHttpClient okHttpClient){
        return createRetrofit(builder,okHttpClient,WanAndroidApi.BASE_URL);
    }


    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder(){
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient.Builder provideOkhttpClientBuilder(){
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkhttpClient(OkHttpClient.Builder builder){
        //在debug情况下开启日期拦截功能
        if(BuildConfig.DEBUG){
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }
        File cacheFile = new File(MyConstant.PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = chain -> {
            Request request = chain.request();
            if(!CommonUtils.isNetworkConnected()){
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            if(CommonUtils.isNetworkConnected()){
                int maxAge = 0;
                //有网络不缓存 读取最新的
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")
                        .build();
            }else {
                //无网络时,设置超时为4周
                int maxAge = 1024*1024*24*28;
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")
                        .build();
            }
            return response;
        };
        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);

        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20,TimeUnit.SECONDS);
        builder.writeTimeout(20,TimeUnit.SECONDS);

        //错误重连
        builder.retryOnConnectionFailure(true);
        //cookie认证
        builder.cookieJar(new CookiesManager());

        return builder.build();
    }




    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient okHttpClient,String base_url){
        return builder
                .baseUrl(base_url)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
