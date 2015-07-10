package com.demo.nd.test.event;

/**
 * Created by Administrator on 2015/7/10.
 */
public class ErrorMessageDataEvent {

    public String dataTag;
    public String message;

    public ErrorMessageDataEvent(String dataTag, String msg) {
        this.dataTag = dataTag;
        this.message = msg;
    }
}
