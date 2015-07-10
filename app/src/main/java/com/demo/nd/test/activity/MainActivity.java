package com.demo.nd.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.demo.nd.test.R;
import com.demo.nd.test.base.BaseMintsActivity;


public class MainActivity extends BaseMintsActivity {

    Button btnCnodejsList;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        btnCnodejsList = (Button) findViewById(R.id.btn_demo_list);

        btnCnodejsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SimpleBackActivity.class);
                intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, "com.demo.nd.test.fragment.cnodejs.CnodejsListFragment");
                intent.putExtra(SimpleBackActivity.BUNDLE_KEY_TITLE, "cnodejs.com API");
                startActivity(intent);
            }
        });
    }




//    public final static String TAG = "MainActivity";
//    int currentPage = 1;
//
//    CnodejsApi mCnodejsApi;
//    CnodejsListAdapter mMainListAdapter;
//
//    ListView mListView;
//    PtrFrameLayout ptrFrame;
//    LoadMoreListViewContainer loadMoreListViewContainer;
//
//
//    private Handler handler = new Handler();
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//
//        initUi();
//        init();
//    }
//
//    void initUi() {
//        setContentView(R.layout.activity_main);
//
//        mListView = (ListView) findViewById(R.id.fragment_home_menu_list_view);
//        ptrFrame = (PtrFrameLayout) findViewById(R.id.fragment_home_ptr_frame);
//    }
//
//    void init() {
//        mCnodejsApi = createApi(CnodejsApi.class, "https://cnodejs.org");
//        mMainListAdapter = new CnodejsListAdapter();
//
//        initPtrFrame();
//    }
//
//    /**
//     * 初始化 下拉控件
//     */
//    void initPtrFrame() {
//        ptrFrame.setLoadingMinTime(1000);
//        ptrFrame.setPtrHandler(new PtrHandler() {
//            @Override
//            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
//                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mListView, header);
//            }
//
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
//                getData(true);
//            }
//        });
//
//
//        loadMoreListViewContainer = (LoadMoreListViewContainer) findViewById(R.id.load_more_list_view_container);
//        loadMoreListViewContainer.useDefaultFooter();
//
//        loadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
//            @Override
//            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
//                getData(false);
//            }
//        });
//        loadMoreListViewContainer.loadMoreFinish(false, true);
//
//
//        ptrFrame.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                ptrFrame.autoRefresh();
//            }
//        }, 100);
//    }
//
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }
//
//
//    void getData(boolean isFirstPage) {
//        if (isFirstPage) {
//            currentPage = 1;
//        }
//
//        mCnodejsApi.topics(currentPage, 20, "", true, new AjaxDataCallback(this, isFirstPage));
//
//        currentPage++;
//    }
//
//    @Override
//    protected String getCloseWarning() {
//        return null;
//    }
//
//    @Override
//    protected int getFragmentContainerId() {
//        return 0;
//    }
//
//    @Override
//    public void addComponent(LifeCycleComponent component) {
//
//    }
//
//    private final class AjaxDataCallback extends ActivityCallback<CnodejsTopicsBean> {
//        public AjaxDataCallback(MainActivity activity, Object obj) {
//            super(activity, obj);
//        }
//
//        @Override
//        public void success(CnodejsTopicsBean requestBean, Response response) {
//            boolean isFirstPage = (boolean) mobj;
//
//            if (isFirstPage) {
//                mMainListAdapter.clear();
//                mMainListAdapter.addData(requestBean.getData());
//                mListView.setAdapter(mMainListAdapter);
//
//                ptrFrame.refreshComplete();
//            } else {
//                mMainListAdapter.addData(requestBean.getData());
//                mMainListAdapter.notifyDataSetChanged();
//                loadMoreListViewContainer.loadMoreFinish(mMainListAdapter.isEmpty(), !mMainListAdapter.isEmpty());
//            }
//        }
//
//        @Override
//        public void failure(RetrofitError error) {
//            super.failure(error);
//
//            ptrFrame.refreshComplete();
//        }
//    }


}
