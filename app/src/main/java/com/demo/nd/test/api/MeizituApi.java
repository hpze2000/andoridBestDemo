package com.demo.nd.test.api;

import com.demo.nd.test.bean.MeizituListBean;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Administrator on 2015/7/13.
 */
public interface MeizituApi {

    @GET("/Public/meizitu")
    void list(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize, @Query("service") String service,
                Callback<MeizituListBean> cb);

}
