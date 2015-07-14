package com.demo.nd.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.demo.nd.test.R;
import com.demo.nd.test.base.BaseActivity;

/**
 * Created by Administrator on 2015/7/10.
 */
public class SimpleBackActivity extends BaseActivity {
    public final static String BUNDLE_KEY_PAGE = "BUNDLE_KEY_PAGE";
    public final static String BUNDLE_KEY_TITLE = "BUNDLE_KEY_TITLE";
    public final static String BUNDLE_KEY_ARGS = "BUNDLE_KEY_ARGS";

    private ActionBar mActionBar;
    protected LayoutInflater mInflater;
    private TextView mTvActionTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_simple_fragment);

        mActionBar = getSupportActionBar();

        mInflater = getLayoutInflater();
        initActionBar(mActionBar);

        Intent data = getIntent();

        String pageValue = data.getStringExtra(BUNDLE_KEY_PAGE);
        String titleValue = data.getStringExtra(BUNDLE_KEY_TITLE);

        setActionBarTitle(titleValue);

        if (!TextUtils.isEmpty(pageValue)) {
            Class c;
            try {
                c = Class.forName(pageValue);

                pushFragmentToBackStack(c, data.getBundleExtra(BUNDLE_KEY_ARGS));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    protected int getActionBarCustomView() {
        return 0;
    }

    protected View inflateView(int resId) {
        return mInflater.inflate(resId, null);
    }

    protected void initActionBar(ActionBar actionBar) {
        if (actionBar == null)
            return;

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
    }

    protected int getActionBarTitle() {
        return R.string.app_name;
    }

    public void setActionBarTitle(int resId) {
        if (resId != 0) {
            setActionBarTitle(getString(resId));
        }
    }

    public void setActionBarTitle(String title) {
        if (mTvActionTitle != null) {
            mTvActionTitle.setText(title);
        }
        mActionBar.setTitle("");
    }

    @Override
    protected String getCloseWarning() {
        return null;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.container;
    }
}
