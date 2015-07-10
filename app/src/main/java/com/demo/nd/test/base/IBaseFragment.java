package com.demo.nd.test.base;

/**
 * Created by Administrator on 2015/7/10.
 */
public interface IBaseFragment {

    void onEnter(Object data);

    void onLeave();

    void onBack();

    void onBackWithData(Object data);

    boolean processBackPressed();
}
