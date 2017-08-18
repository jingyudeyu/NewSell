package com.hyphenate.easeui.newmore;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 类名称：OkHttp3Utils
 * 创建人：wangliteng
 * 创建时间：2016-5-18 14:57:11
 * 类描述：封装一个OkHttp3的获取类
 *
 */
public class OkHttp3Utils {
    public static Application instance;
    private static OkHttpClient mOkHttpClient;

    //设置缓存目录
  //  private static File cacheDirectory = new File(instance.getApplicationContext().getApplicationContext().getCacheDir().getAbsolutePath(), "MyCache");
  //  private static Cache cache = new Cache(cacheDirectory, 10 * 1024 * 1024);


    /**
     * 获取OkHttpClient对象
     *
     * @return
     */
    public static OkHttpClient getOkHttpClient() {

        if (null == mOkHttpClient) {
            //同样okhttp3后也使用build设计模式
            mOkHttpClient = new OkHttpClient.Builder()
                    //设置一个自动管理cookies的管理器
                    //.cookieJar(new CookiesManager())
                    //添加拦截器
                    //.addInterceptor(new MyIntercepter())
                    //添加网络连接器
                   // .addNetworkInterceptor(new MyIntercepter())
                    //设置请求读写的超时时间
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                  //  .cache(cache)
                    .build();
        }
        return mOkHttpClient;
    }


}
