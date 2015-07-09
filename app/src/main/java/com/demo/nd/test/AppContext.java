package com.demo.nd.test;

import com.demo.nd.test.base.BaseApplication;

/**
 * Created by Administrator on 2015/7/9.
 */
public class AppContext extends BaseApplication {
    private static AppContext instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }
}
