package com.demo.nd.test.fragment.cnodejs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.demo.nd.test.R;
import com.demo.nd.test.api.CnodejsApi;
import com.demo.nd.test.base.BaseFragment;
import com.demo.nd.test.bean.CnodejsTopicsBean;
import com.demo.nd.test.fragment.adapter.CnodejsListAdapter;
import com.demo.nd.test.ui.loadmore.LoadMoreContainer;
import com.demo.nd.test.ui.loadmore.LoadMoreHandler;
import com.demo.nd.test.ui.loadmore.LoadMoreListViewContainer;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Administrator on 2015/7/10.
 */
public class CnodejsListFragment extends BaseFragment {
    int currentPage = 1;

    CnodejsApi mCnodejsApi;
    CnodejsListAdapter mMainListAdapter;

    ListView mListView;
    PtrFrameLayout ptrFrame;
    LoadMoreListViewContainer loadMoreListViewContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View view = inflater.inflate(R.layout.fragment_cnodejs_list, null);

        mListView = (ListView) view.findViewById(R.id.fragment_home_menu_list_view);
        ptrFrame = (PtrFrameLayout) view.findViewById(R.id.fragment_home_ptr_frame);
        loadMoreListViewContainer = (LoadMoreListViewContainer) view.findViewById(R.id.load_more_list_view_container);

        init();

        return view;
    }


    void init() {
        mCnodejsApi = createApi(CnodejsApi.class, "https://cnodejs.org");
        mMainListAdapter = new CnodejsListAdapter();

        initPtrFrame();
    }

    /**
     * 初始化 下拉控件
     */
    void initPtrFrame() {
        ptrFrame.setLoadingMinTime(1000);
        ptrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mListView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                getData(true);
            }
        });

        loadMoreListViewContainer.useDefaultFooter();
        loadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
            @Override
            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
                getData(false);
            }
        });
        loadMoreListViewContainer.loadMoreFinish(false, true);

        ptrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrFrame.autoRefresh();
            }
        }, 100);
    }

    void getData(boolean isFirstPage) {
        if (isFirstPage) {
            currentPage = 1;
        }

        mCnodejsApi.topics(currentPage, 20, "", true, new AjaxDataCallback(isFirstPage));

        currentPage++;
    }

    private final class AjaxDataCallback extends ActivityCallback<CnodejsTopicsBean> {
        public AjaxDataCallback(Object obj) {
            super(obj);
        }

        @Override
        public void success(CnodejsTopicsBean requestBean, Response response) {
            boolean isFirstPage = (boolean) mObject;

            if (isFirstPage) {
                mMainListAdapter.clear();
                mMainListAdapter.addData(requestBean.getData());
                mListView.setAdapter(mMainListAdapter);

                ptrFrame.refreshComplete();
            } else {
                mMainListAdapter.addData(requestBean.getData());
                mMainListAdapter.notifyDataSetChanged();
                loadMoreListViewContainer.loadMoreFinish(mMainListAdapter.isEmpty(), !mMainListAdapter.isEmpty());
            }
        }

        @Override
        public void failure(RetrofitError error) {
            ptrFrame.refreshComplete();
        }
    }

}
