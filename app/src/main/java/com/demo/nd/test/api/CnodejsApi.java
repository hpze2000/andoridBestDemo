package com.demo.nd.test.api;

import com.demo.nd.test.bean.CnodejsTopicBean;
import com.demo.nd.test.bean.CnodejsTopicsBean;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Johnson on 2015/7/10.
 */
public interface CnodejsApi {

    @GET("/api/v1/topics")
    void topics(@Query("page") int page, @Query("limit") int limit, @Query("tab") String tab, @Query("mdrender") boolean mdrender,
                      Callback<CnodejsTopicsBean> cb);

    @GET("/api/v1/topic/{id}")
    void topic(@Path("id") String id, Callback<CnodejsTopicBean> cb);


}
