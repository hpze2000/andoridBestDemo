package com.demo.test.nd.httpdemo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtils {

    public static Gson newInstance() {
        GsonBuilder builder = new GsonBuilder();

//        builder.setFieldNamingStrategy(new AnnotateNaming());

        return builder.create();
    }

//    private static class AnnotateNaming implements FieldNamingStrategy {
//
//        @Override
//        public String translateName(Field field) {
//            ParamName a = field.getAnnotation(ParamName.class);
//            return a != null ? a.value() : FieldNamingPolicy.IDENTITY.translateName(field);
//        }
//    }
}