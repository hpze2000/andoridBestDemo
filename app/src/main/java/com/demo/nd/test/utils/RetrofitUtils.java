package com.demo.nd.test.utils;

import android.content.Context;

import com.demo.nd.test.base.Config;

import java.util.HashMap;

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
                    singletons.put(endPoint, builder.build());
                }
            }
        }
        return singletons.get(endPoint).create(clazz);
    }
}