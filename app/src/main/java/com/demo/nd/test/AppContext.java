package com.demo.nd.test;

import com.demo.nd.test.base.BaseApplication;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

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

    @Override
    protected void init() {
        super.init();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .build();
        ImageLoader.getInstance().init(config);
    }
}
