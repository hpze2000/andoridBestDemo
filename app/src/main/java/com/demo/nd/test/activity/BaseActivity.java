package com.demo.nd.test.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.demo.nd.test.utils.RetrofitUtils;
import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Administrator on 2015/7/8.
 */
public class BaseActivity extends AppCompatActivity {

    public  <T> T  createApi(Class<T> cls) {
        return RetrofitUtils.createApi(this, cls);
    }


    public abstract class ActivityCallback<T> implements Callback<T> {
        private final WeakReference<BaseActivity> mRef;

        public ActivityCallback(BaseActivity activity) {
            mRef = new WeakReference<BaseActivity>(activity);
        }

        public Activity getActivity() {
            return mRef.get();
        }

        @Override
        public void failure(RetrofitError error) {
            final BaseActivity activity = mRef.get();

            Response response = error.getResponse();
            if (response != null) {
                Logger.d("code:" + response.getStatus() + ", reason:" + response.getReason());
                error.printStackTrace();
            }
        }
    }

}
