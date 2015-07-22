package com.demo.test.nd.httpdemo.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.demo.test.nd.httpdemo.R;
import com.demo.test.nd.httpdemo.api.DoubanBookApi;
import com.demo.test.nd.httpdemo.bean.DoubanBook;
import com.demo.test.nd.httpdemo.utils.RetrofitUtils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {


    Button btnSeach;
    TextView tvContent;
    DoubanBookApi mDoubanBookApi;
    ProgressDialog mProgressDialog;
    final String END_POINT = "https://api.douban.com/v2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btnSeach = (Button) this.findViewById(R.id.btn_search);
        tvContent = (TextView) this.findViewById(R.id.tv_content);
        mDoubanBookApi = RetrofitUtils.createApi(this, DoubanBookApi.class, END_POINT);

        btnSeach.setOnClickListener(this);

    }

    void btnSeachClick() {
        mProgressDialog = ProgressDialog.show(this, "请稍等...", "正在请求网络数据...");
        mDoubanBookApi.search(0, 10, "韩寒", "", new DoubanBookCallbak());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                btnSeachClick();
                break;
        }
    }

    public class DoubanBookCallbak implements Callback<DoubanBook> {

        @Override
        public void success(DoubanBook doubanBook, Response response) {
            //异步返回结果成功后 执行 这里
            String bookContent = "";
            for (DoubanBook.BooksEntity book : doubanBook.getBooks()) {
                bookContent += "书名：" + book.getTitle() + "（"+ book.getAuthor().get(0) +"）\n\n";

            }

            tvContent.setText(bookContent);

            mProgressDialog.dismiss();
        }

        @Override
        public void failure(RetrofitError error) {
            //异步返回结果失败后 执行 这里

            System.out.println(error);

            mProgressDialog.dismiss();
        }
    }
}
