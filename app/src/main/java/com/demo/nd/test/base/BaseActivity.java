package com.demo.nd.test.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.demo.nd.test.R;
import com.demo.nd.test.ui.dialog.DialogControl;
import com.demo.nd.test.ui.dialog.DialogHelper;
import com.demo.nd.test.ui.dialog.WaitDialog;
import com.demo.nd.test.utils.LogUtils;
import com.demo.nd.test.utils.RetrofitUtils;

import java.lang.ref.WeakReference;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by Administrator on 2015/7/9.
 */
public class BaseActivity extends ActionBarActivity implements DialogControl, VisibilityControl {
    public static final String INTENT_ACTION_EXIT_APP = "INTENT_ACTION_EXIT_APP";

    private boolean _isVisible = true;
    private WaitDialog _waitDialog;
    protected LayoutInflater mInflater;
    private ActionBar mActionBar;
    private TextView mTvActionTitle;

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
                LogUtils.log("code:" + response.getStatus() + ", reason:" + response.getReason());
                error.printStackTrace();
            }
        }
    }


    private BroadcastReceiver mExistReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!hasActionBar()) {
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        onBeforeSetContentLayout();
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        mActionBar = getSupportActionBar();
        mInflater = getLayoutInflater();
        if (hasActionBar()) {
            initActionBar(mActionBar);
        }
        init(savedInstanceState);

//        IntentFilter filter = new IntentFilter(INTENT_ACTION_EXIT_APP);
//        registerReceiver(mExistReceiver, filter);
    }

    protected void init(Bundle savedInstanceState) {
    }

    protected void initActionBar(ActionBar actionBar) {
        if (actionBar == null)
            return;
        if (hasBackButton()) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            int layoutRes = getActionBarCustomView();
            View view = inflateView(layoutRes == 0 ? R.layout.actionbar_custom_backtitle
                    : layoutRes);
            View back = view.findViewById(R.id.btn_back);
            if (back == null) {
                throw new IllegalArgumentException(
                        "can not find R.id.btn_back in customView");
            }
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            mTvActionTitle = (TextView) view
                    .findViewById(R.id.tv_actionbar_title);
            if (mTvActionTitle == null) {
                throw new IllegalArgumentException(
                        "can not find R.id.tv_actionbar_title in customView");
            }
            int titleRes = getActionBarTitle();
            if (titleRes != 0) {
                mTvActionTitle.setText(titleRes);
            }
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT);
            actionBar.setCustomView(view, params);
        } else {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
            actionBar.setDisplayUseLogoEnabled(false);
            int titleRes = getActionBarTitle();
            if (titleRes != 0) {
                actionBar.setTitle(titleRes);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    public void setActionBarTitle(int resId) {
        if (resId != 0) {
            setActionBarTitle(getString(resId));
        }
    }

    public void setActionBarTitle(String title) {
        if (hasActionBar()) {
            if (mTvActionTitle != null) {
                mTvActionTitle.setText(title);
            }
            mActionBar.setTitle(title);
        }
    }

    protected int getActionBarTitle() {
        return R.string.app_name;
    }

    protected View inflateView(int resId) {
        return mInflater.inflate(resId, null);
    }

    protected boolean hasBackButton() {
        return false;
    }

    protected int getActionBarCustomView() {
        return 0;
    }

    protected void onBeforeSetContentLayout() {
    }

    protected boolean hasActionBar() {
        return true;
    }

    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void onPause() {
        _isVisible = false;
        hideWaitDialog();
        super.onPause();
    }

    @Override
    protected void onResume() {
        _isVisible = true;
        super.onResume();
    }


    @Override
    public void hideWaitDialog() {
        if (_isVisible && _waitDialog != null) {
            try {
                _waitDialog.dismiss();
                _waitDialog = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
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
        if (_isVisible) {
            if (_waitDialog == null) {
                _waitDialog = DialogHelper.getWaitDialog(this, message);
            }
            if (_waitDialog != null) {
                _waitDialog.setMessage(message);
                _waitDialog.show();
            }
            return _waitDialog;
        }
        return null;
    }

    @Override
    public boolean isVisible() {
        return _isVisible;
    }
}
