package com.demo.test.nd.httpdemo;

import android.content.Context;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;


public class OkHttpUtils {
    private static OkHttpClient singleton;

    public static OkHttpClient getInstance(Context context) {
        if (singleton == null) {
            synchronized (OkHttpUtils.class) {
                if (singleton == null) {
                    File cacheDir = new File(context.getCacheDir(), "httpDemo");

                    singleton = new OkHttpClient();
                    singleton.setCache(new Cache(cacheDir, 1024 * 1024 * 10));
                    singleton.setConnectTimeout(10000, TimeUnit.MILLISECONDS);
                    singleton.setReadTimeout(10000, TimeUnit.MILLISECONDS);
                    CookieManager cookieManager = new CookieManager();
                    cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
                    singleton.setCookieHandler(cookieManager);
                }
            }
        }
        return singleton;
    }
}