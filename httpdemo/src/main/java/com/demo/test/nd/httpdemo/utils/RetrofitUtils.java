package com.demo.test.nd.httpdemo.utils;

import android.content.Context;

import java.util.HashMap;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by johnson on 2015/7/22.
 */
public class RetrofitUtils {

    private static HashMap<String, RestAdapter> singletons = new HashMap();

    public static <T> T createApi(Context context, Class<T> clazz, String endPoint) {
        if (singletons.get(endPoint) == null) {
            synchronized (RetrofitUtils.class) {
                if (singletons.get(endPoint) == null) {
                    RestAdapter.Builder builder = new RestAdapter.Builder();
                    //请求的网址
                    builder.setEndpoint(endPoint);
                    //请求回来的数据，用什么来处理，gson
                    builder.setConverter(new GsonConverter(GsonUtils.newInstance()));
                    //设置请求的客户端，OKHTTP
                    builder.setClient(new OkClient(OkHttpUtils.getInstance(context)));
                    //设置日志的等级
                    builder.setLogLevel(
                            RestAdapter.LogLevel.FULL);

                    singletons.put(endPoint, builder.build());
                }
            }
        }
        return singletons.get(endPoint).create(clazz);
    }

}
