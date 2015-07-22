package com.demo.test.nd.httpdemo.utils;

import android.content.Context;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

/**
 * Created by johnson on 2015/7/22.
 * okhttp 定义的一个 类
 */
public class OkHttpUtils {
    private static OkHttpClient singleton;

    public static OkHttpClient getInstance(Context context) {
        if (singleton == null) {
            synchronized (OkHttpUtils.class) {
                if (singleton == null) {
                    // /data/packagename/mainaction/
                    File cacheDir = new File(context.getCacheDir(), "okhttpCache");

                    singleton = new OkHttpClient();
                    //设置缓存文件的大小限制
                    singleton.setCache(new Cache(cacheDir, 10 * 1024 * 1024));
                    //设置连接超时的时间限制
                    singleton.setConnectTimeout(10000, TimeUnit.MILLISECONDS);
                    //设置读取超时的时间限制
                    singleton.setReadTimeout(10000, TimeUnit.MILLISECONDS);
                    //cookie 的管理
                    CookieManager cookieManager = new CookieManager();
                    cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
                    singleton.setCookieHandler(cookieManager);
                }
            }
        }
        return singleton;
    }
}
