package com.demo.nd.test.fragment.cnodejs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.demo.nd.test.R;
import com.demo.nd.test.activity.SimpleBackActivity;
import com.demo.nd.test.api.CnodejsApi;
import com.demo.nd.test.base.BaseFragment;
import com.demo.nd.test.base.BaseListAdapter;
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
    int G_currentPage = 1;
    String G_tabStr = "";

    CnodejsApi mCnodejsApi;
    CnodejsListAdapter mCnodejsListAdapter;

    ListView mListView;
    PtrFrameLayout ptrFrame;
    LoadMoreListViewContainer loadMoreListViewContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_cnodejs_list, null);

        mListView = (ListView) view.findViewById(R.id.fragment_home_menu_list_view);
        ptrFrame = (PtrFrameLayout) view.findViewById(R.id.fragment_home_ptr_frame);
        loadMoreListViewContainer = (LoadMoreListViewContainer) view.findViewById(R.id.load_more_list_view_container);

        init();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CnodejsTopicsBean.DataEntity data = mCnodejsListAdapter.getItem(position);

                Bundle bundle = new Bundle();
                bundle.putString("id", data.getId());
//                bundle.putString("content", data.getContent());
//                bundle.putString("time", data.getCreate_at());
//                bundle.putString("name", data.getAuthor().getLoginname());
//                bundle.putString("title", data.getTitle());

                Intent intent = new Intent(getContext(), SimpleBackActivity.class);
                intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, "com.demo.nd.test.fragment.cnodejs.CnodejsPageFragment");
                intent.putExtra(SimpleBackActivity.BUNDLE_KEY_TITLE, "主题内容");
                intent.putExtra(SimpleBackActivity.BUNDLE_KEY_ARGS, bundle);
                startActivity(intent);
            }
        });

        setHasOptionsMenu(true);

        return view;
    }


    void init() {
        mCnodejsApi = createApi(CnodejsApi.class, "https://cnodejs.org");
        mCnodejsListAdapter = new CnodejsListAdapter();

        initPtrFrame();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_cnodejs, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_ask:
                G_tabStr = "ask";
                break;
            case R.id.action_good:
                G_tabStr = "good";
                break;
            case R.id.action_job:
                G_tabStr = "job";
                break;
            case R.id.action_share:
                G_tabStr = "share";
                break;
            default:
                G_tabStr = "";
                break;
        }


        ptrFrame.autoRefresh();

        return super.onOptionsItemSelected(item);
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


        ptrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrFrame.autoRefresh();
            }
        }, 100);
    }

    void getData(boolean isFirstPage) {
        if (isFirstPage) {
            G_currentPage = 1;
        }

        mCnodejsApi.topics(G_currentPage, 20, G_tabStr, true, new DataCallback(isFirstPage));

        G_currentPage++;
    }

    private final class DataCallback extends ActivityCallback<CnodejsTopicsBean> {
        public DataCallback(Object obj) {
            super(obj);
        }

        @Override
        public void success(CnodejsTopicsBean requestBean, Response response) {
            boolean isFirstPage = (boolean) mObject;

            if (isFirstPage) {
                mCnodejsListAdapter.clear();
                mCnodejsListAdapter.addData(requestBean.getData());
                mListView.setAdapter(mCnodejsListAdapter);

                ptrFrame.refreshComplete();
            } else {
                mCnodejsListAdapter.addData(requestBean.getData());
                mCnodejsListAdapter.notifyDataSetChanged();
            }

            loadMoreListViewContainer.loadMoreFinish(
                    requestBean.getData().isEmpty(),
                    !requestBean.getData().isEmpty()
            );
        }

        @Override
        public void failure(RetrofitError error) {
            ptrFrame.refreshComplete();
            mCnodejsListAdapter.setState(BaseListAdapter.STATE_NETWORK_ERROR);
        }
    }

}
