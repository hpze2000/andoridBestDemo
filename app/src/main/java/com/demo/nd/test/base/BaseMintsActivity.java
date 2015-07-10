package com.demo.nd.test.base;

import android.app.Activity;

import com.demo.nd.test.R;
import com.demo.nd.test.utils.LogUtils;
import com.demo.nd.test.utils.RetrofitUtils;

import java.lang.ref.WeakReference;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Administrator on 2015/7/10.
 */
public class BaseMintsActivity extends BaseActivity {





    @Override
    protected String getCloseWarning() {
        return getString(R.string.exit_tip);
    }

    @Override
    protected int getFragmentContainerId() {
        return 0;
    }

}
