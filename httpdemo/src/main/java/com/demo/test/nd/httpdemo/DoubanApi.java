package com.demo.test.nd.httpdemo;


import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Johnson on 2015/7/10.
 */
public interface DoubanApi {

    @GET("/music/search")
    void getMusic(@Query("q") String q, @Query("count") int count, @Query("start") int start,
                Callback<DoubanMusic> cb);


}
