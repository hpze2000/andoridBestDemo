package com.demo.test.nd.httpdemo.api;

import com.demo.test.nd.httpdemo.bean.DoubanBook;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by johnson on 2015/7/22.
 */
public interface DoubanBookApi {

    @GET("/book/search")
    void search(@Query("start") int start, @Query("count") int count, @Query("q") String q, @Query("tag") String tag,
                Callback<DoubanBook> cb);


}
