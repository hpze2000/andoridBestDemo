package com.demo.nd.test.api;

import com.demo.nd.test.bean.MeizituBaseBean;
import com.demo.nd.test.bean.MeizituListBean;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;
import retrofit.mime.TypedFile;

/**
 * Created by Administrator on 2015/7/13.
 */
public interface MeizituApi {

    @GET("/Public/meizitu")
    void list(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize, @Query("menuId") int menuId, @Query("service") String service,
                Callback<MeizituListBean> cb);



    @GET("/Public/meizitu")
    void login(@Query("username") String username, @Query("password") String password, @Query("service") String service,
              Callback<MeizituBaseBean> cb);

    @GET("/Public/meizitu")
    void logout(@Query("service") String service, Callback<MeizituBaseBean> cb);

    @GET("/Public/meizitu")
    void needLoginUrl(@Query("service") String service, Callback<MeizituBaseBean> cb);

    @Multipart
    @POST("/MeiziTu/upload.php")
    void upload(@Part("file") TypedFile file, @Query("service") String service, Callback<MeizituBaseBean> cb);

}
