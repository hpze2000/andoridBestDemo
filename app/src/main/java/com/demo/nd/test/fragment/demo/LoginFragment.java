package com.demo.nd.test.fragment.demo;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.demo.nd.test.R;
import com.demo.nd.test.api.MeizituApi;
import com.demo.nd.test.base.BaseApplication;
import com.demo.nd.test.base.BaseFragment;
import com.demo.nd.test.bean.MeizituBaseBean;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Administrator on 2015/7/14.
 */
public class LoginFragment extends BaseFragment {

    Button btnLogin, btnLogout, btnNeedLoginUrl;
    EditText username, password;
    MeizituApi mMeizituApi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login, null);

        btnLogin = (Button) view.findViewById(R.id.btn_login);
        btnLogout = (Button) view.findViewById(R.id.btn_logout);
        btnNeedLoginUrl = (Button) view.findViewById(R.id.btn_test_need_login_url);

        username = (EditText) view.findViewById(R.id.et_username);
        password = (EditText) view.findViewById(R.id.et_password);


        mMeizituApi = createApi(MeizituApi.class, "http://api.meitu.tv");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = username.getText().toString();
                String psw = password.getText().toString();

                if (TextUtils.isEmpty(uname) || TextUtils.isEmpty(psw)) {
                    BaseApplication.showToast("输入用户名和密码");
                    return;
                }

                showWaitDialog().show();
                mMeizituApi.login(username.getText().toString(), password.getText().toString(), "Default.Login", new LoginDataCallback(null));
            }
        });

        btnNeedLoginUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWaitDialog().show();
                mMeizituApi.needLoginUrl("Default.NeedLoginUrl", new NeedLoginDataCallback(null));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWaitDialog().show();
                mMeizituApi.logout("Default.Logout", new LogoutDataCallback(null));
            }
        });

        return view;
    }


    private final class LoginDataCallback extends ActivityCallback<MeizituBaseBean> {
        public LoginDataCallback(Object obj) {
            super(obj);
        }

        @Override
        public void success(MeizituBaseBean requestBean, Response response) {
            if (requestBean.getRet() == 200) {
                if (TextUtils.isEmpty(requestBean.getData())) {
                    BaseApplication.showToast("账号密码 是 admin  123");
                } else {
                    BaseApplication.showToast("登录成功， 可以点击需登录才能请求的按钮测试");
                }
            } else {
                BaseApplication.showToast(requestBean.getMsg());
            }

            hideWaitDialog();
        }

        @Override
        public void failure(RetrofitError error) {

            hideWaitDialog();
        }
    }


    private final class NeedLoginDataCallback extends ActivityCallback<MeizituBaseBean> {
        public NeedLoginDataCallback(Object obj) {
            super(obj);
        }

        @Override
        public void success(MeizituBaseBean requestBean, Response response) {
            if (requestBean.getRet() == 200) {
                if (requestBean.getData().equalsIgnoreCase("true")) {
                    BaseApplication.showToast("服务端有你的登录信息，访问成功");
                } else {
                    BaseApplication.showToast("服务端没有你的登录信息，访问失败");
                }
            } else {
                BaseApplication.showToast(requestBean.getMsg());
            }

            hideWaitDialog();
        }

        @Override
        public void failure(RetrofitError error) {

            hideWaitDialog();
        }
    }

    private final class LogoutDataCallback extends ActivityCallback<MeizituBaseBean> {
        public LogoutDataCallback(Object obj) {
            super(obj);
        }

        @Override
        public void success(MeizituBaseBean requestBean, Response response) {
            if (requestBean.getRet() == 200) {
                BaseApplication.showToast("登录信息已经清除");
            } else {
                BaseApplication.showToast(requestBean.getMsg());
            }

            hideWaitDialog();
        }

        @Override
        public void failure(RetrofitError error) {

            hideWaitDialog();
        }
    }
}
