package com.demo.nd.test.base;

import android.os.Bundle;

import com.demo.nd.test.R;
import com.demo.nd.test.base.lifecycle.IComponentContainer;
import com.demo.nd.test.base.lifecycle.LifeCycleComponent;
import com.demo.nd.test.base.lifecycle.LifeCycleComponentManager;
import com.demo.nd.test.ui.dialog.DialogControl;
import com.demo.nd.test.ui.dialog.DialogHelper;
import com.demo.nd.test.ui.dialog.WaitDialog;
import com.demo.nd.test.utils.LogUtils;
import com.demo.nd.test.utils.RetrofitUtils;

import retrofit.Callback;


/**
 * Created by Administrator on 2015/7/9.
 */
public abstract class BaseActivity extends BaseFragmentActivity implements DialogControl, IComponentContainer {

    public <T> T createApi(Class<T> cls, String endPoint) {
        return RetrofitUtils.createApi(this, cls, endPoint);
    }

    public abstract class ActivityCallback<T> implements Callback<T> {
        protected final Object mObject;

        public ActivityCallback(Object obj) {
            mObject = obj;
        }
    }

    private WaitDialog _waitDialog;


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


    @Override
    public WaitDialog showWaitDialog() {
        return showWaitDialog(R.string.loading);
    }

    @Override
    public WaitDialog showWaitDialog(int resid) {
        return showWaitDialog(getString(resid));
    }

    @Override
    public WaitDialog showWaitDialog(String message) {
        if (_waitDialog == null) {
            _waitDialog = DialogHelper.getWaitDialog(this, message);
        }
        if (_waitDialog != null) {
            _waitDialog.setMessage(message);
            _waitDialog.show();
        }
        return _waitDialog;
    }

    @Override
    public void hideWaitDialog() {
        if (_waitDialog != null) {
            try {
                _waitDialog.dismiss();
                _waitDialog = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
