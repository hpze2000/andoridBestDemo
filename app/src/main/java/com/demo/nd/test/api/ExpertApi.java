package com.demo.nd.test.api;

import com.demo.nd.test.bean.Bean;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Administrator on 2015/7/8.
 */
public interface ExpertApi {

    @GET("/chart/showAjaxData")
    void showAjaxData(@Query("dates") String dates, @Query("projectId") int projectId, @Query("type") String type,
                      Callback<Bean> cb);
}
