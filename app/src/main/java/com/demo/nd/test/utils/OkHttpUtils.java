package com.demo.nd.test.utils;

import android.content.Context;

import com.demo.nd.test.base.Config;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.util.concurrent.TimeUnit;


public class OkHttpUtils {

    private static OkHttpClient singleton;

    public static OkHttpClient getInstance(Context context) {
        if (singleton == null) {
            synchronized (OkHttpUtils.class) {
                if (singleton == null) {
                    File cacheDir = new File(context.getCacheDir(), Config.RESPONSE_CACHE);

                    singleton = new OkHttpClient();
                    singleton.setCache(new Cache(cacheDir, Config.RESPONSE_CACHE_SIZE));
                    singleton.setConnectTimeout(Config.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
                    singleton.setReadTimeout(Config.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS);
                }
            }
        }
        return singleton;
    }
}