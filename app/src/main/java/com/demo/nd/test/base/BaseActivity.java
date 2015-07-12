package com.demo.nd.test.base;

import android.os.Bundle;

import com.demo.nd.test.base.lifecycle.IComponentContainer;
import com.demo.nd.test.base.lifecycle.LifeCycleComponent;
import com.demo.nd.test.base.lifecycle.LifeCycleComponentManager;
import com.demo.nd.test.utils.LogUtils;


/**
 * Created by Administrator on 2015/7/9.
 */
public abstract class BaseActivity extends BaseFragmentActivity implements IComponentContainer {

//    public <T> T createApi(Class<T> cls, String endPoint) {
//        return RetrofitUtils.createApi(this, cls, endPoint);
//    }
//
//    public abstract class ActivityCallback<T> implements Callback<T> {
//        private final WeakReference<BaseActivity> mRef;
//        protected final Object mobj;
//
//        public ActivityCallback(BaseActivity activity, Object obj) {
//            mRef = new WeakReference<>(activity);
//            mobj = obj;
//        }
//
//        public Activity getActivity() {
//            return mRef.get();
//        }
//
//        @Override
//        public void failure(RetrofitError error) {
//            final BaseActivity activity = mRef.get();
//
//            Response response = error.getResponse();
//            if (response != null) {
//                LogUtils.log("code:" + response.getStatus() + ", reason:" + response.getReason());
//                error.printStackTrace();
//            }
//        }
//    }




    private LifeCycleComponentManager mComponentContainer = new LifeCycleComponentManager();

    private static final boolean DEBUG = Config.DEBUG;

    @Override
    protected void onRestart() {
        super.onStart();
        mComponentContainer.onBecomesVisibleFromTotallyInvisible();
        if (DEBUG) {
            showStatus("onRestart");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mComponentContainer.onBecomesPartiallyInvisible();
        if (DEBUG) {
            showStatus("onPause");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mComponentContainer.onBecomesVisibleFromPartiallyInvisible();
        if (DEBUG) {
            showStatus("onResume");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (DEBUG) {
            showStatus("onCreate");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mComponentContainer.onBecomesTotallyInvisible();
        if (DEBUG) {
            showStatus("onStop");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mComponentContainer.onDestroy();
        if (DEBUG) {
            showStatus("onDestroy");
        }
    }

    @Override
    public void addComponent(LifeCycleComponent component) {
        mComponentContainer.addComponent(component);
    }

    private void showStatus(String status) {
        final String[] className = ((Object) this).getClass().getName().split("\\.");
        LogUtils.log("lifecycle", String.format("%s %s", className[className.length - 1], status));
    }
}
