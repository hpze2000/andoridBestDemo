package com.demo.test.nd.httpdemo.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by johnson on 2015/7/22.
 * GSON (java 类库)  JSON (一种定义好的，字符串格式)
 */
public class GsonUtils {

    public static Gson newInstance() {
        GsonBuilder builder = new GsonBuilder();

        return builder.create();
    }

}
