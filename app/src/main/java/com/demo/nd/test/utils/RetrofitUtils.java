package com.demo.nd.test.utils;

import android.content.Context;

import com.demo.nd.test.base.Config;

import java.util.HashMap;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class RetrofitUtils {

    private static HashMap<String, RestAdapter> singletons = new HashMap();

    public static <T> T createApi(Context context, Class<T> clazz, String endPoint) {
        if (singletons.get(endPoint) == null) {
            synchronized (RetrofitUtils.class) {
                if (singletons.get(endPoint) == null) {
                    RestAdapter.Builder builder = new RestAdapter.Builder();
                    builder.setEndpoint(endPoint);
                    builder.setConverter(new GsonConverter(GsonUtils.newInstance()));
                    builder.setClient(new OkClient(OkHttpUtils.getInstance(context)));
                    builder.setLogLevel(
                            Config.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE);
                    builder.setRequestInterceptor(new RequestInterceptor() {
                        @Override
                        public void intercept(RequestFacade request) {
                            if (DeviceUtils.hasInternet()) {
                                int maxAge = 60; // read from cache for 1 minute
                                request.addHeader("Cache-Control", "public, max-age=" + maxAge);
                            } else {
                                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                                request.addHeader("Cache-Control",
                                        "public, only-if-cached, max-stale=" + maxStale);
                            }
                        }
                    });
                    singletons.put(endPoint, builder.build());
                }
            }
        }
        return singletons.get(endPoint).create(clazz);
    }
}