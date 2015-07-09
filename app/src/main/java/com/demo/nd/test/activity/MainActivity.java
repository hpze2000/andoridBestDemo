package com.demo.nd.test.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;

import com.demo.nd.test.R;
import com.demo.nd.test.activity.adapter.MainListAdapter;
import com.demo.nd.test.api.ExpertApi;
import com.demo.nd.test.base.BaseActivity;
import com.demo.nd.test.bean.Bean;
import com.demo.nd.test.utils.LogUtils;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends BaseActivity {
    public final static String TAG = "MainActivity";

    ExpertApi mExpertApi;
    MainListAdapter mMainListAdapter;

    ListView mListView;
    PtrFrameLayout ptrFrame;

    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        initUi();
        init();
    }

    void initUi() {
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.fragment_home_menu_list_view);
        ptrFrame = (PtrFrameLayout) findViewById(R.id.fragment_home_ptr_frame);
    }

    void init() {
        mExpertApi = createApi(ExpertApi.class);
        mMainListAdapter = new MainListAdapter();

        initPtrFrame();
    }

    /**
     * 初始化 下拉控件
     */
    void initPtrFrame() {
        final StoreHouseHeader header = new StoreHouseHeader(MainActivity.this);
        header.initWithStringArray(R.array.storehouse);
        ptrFrame.setDurationToCloseHeader(1500);
        ptrFrame.setHeaderView(header);
        ptrFrame.addPtrUIHandler(header);
        ptrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                getData();
            }
        });

        ptrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrFrame.autoRefresh();
            }
        }, 100);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    void getData() {
        mExpertApi.showAjaxData("", 1, "", new AjaxDataCallback(MainActivity.this));
    }

    private final class AjaxDataCallback extends ActivityCallback<Bean> {
        public AjaxDataCallback(MainActivity activity) {
            super(activity);
        }

        @Override
        public void success(Bean requestBean, Response response) {
            if (requestBean.getStatus() == 200) {
                mMainListAdapter.addData(requestBean.getData().getSeries());

                mListView.setAdapter(mMainListAdapter);
            } else {
                LogUtils.error(response.getReason());
            }

            ptrFrame.refreshComplete();
        }

        @Override
        public void failure(RetrofitError error) {
            super.failure(error);

            ptrFrame.refreshComplete();
        }
    }


}
