package com.demo.nd.test.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/7/14.
 */
public class MeizituBaseBean implements Parcelable {

    /**
     * ret : 200
     * data : admin
     * msg :
     */
    private int ret;
    private String data;
    private String msg;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }


    public void setRet(int ret) {
        this.ret = ret;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getRet() {
        return ret;
    }

    public String getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }
}
