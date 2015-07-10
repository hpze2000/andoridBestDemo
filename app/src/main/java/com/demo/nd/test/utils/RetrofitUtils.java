package com.demo.nd.test.utils;

import android.content.Context;

import com.demo.nd.test.base.Config;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class RetrofitUtils {

    private static RestAdapter singleton;

    public static <T> T createApi(Context context, Class<T> clazz, String endPoint) {
        if (singleton == null) {
            synchronized (RetrofitUtils.class) {
                if (singleton == null) {
                    RestAdapter.Builder builder = new RestAdapter.Builder();
                    builder.setEndpoint(endPoint);
                    builder.setConverter(new GsonConverter(GsonUtils.newInstance()));
                    builder.setClient(new OkClient(OkHttpUtils.getInstance(context)));
                    builder.setLogLevel(
                            Config.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE);
                    singleton = builder.build();
                }
            }
        }
        return singleton.create(clazz);
    }
}