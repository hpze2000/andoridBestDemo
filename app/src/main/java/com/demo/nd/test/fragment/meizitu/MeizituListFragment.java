package com.demo.nd.test.fragment.meizitu;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.demo.nd.test.R;
import com.demo.nd.test.api.MeizituApi;
import com.demo.nd.test.base.BaseFragment;
import com.demo.nd.test.base.BaseListAdapter;
import com.demo.nd.test.bean.MeizituListBean;
import com.demo.nd.test.fragment.adapter.MeizituListAdapter;
import com.demo.nd.test.ui.loadmore.LoadMoreContainer;
import com.demo.nd.test.ui.loadmore.LoadMoreGridViewContainer;
import com.demo.nd.test.ui.loadmore.LoadMoreHandler;
import com.etsy.android.grid.StaggeredGridView;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Administrator on 2015/7/13.
 */
public class MeizituListFragment extends BaseFragment {

    int G_currentPage = 1;

    MeizituApi mMeizituApi;
    MeizituListAdapter mMeizituListAdapter;

    StaggeredGridView mGridView;
    PtrFrameLayout ptrFrame;
    LoadMoreGridViewContainer mLoadMoreGridViewContainer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_meizitu_list, null);

        mGridView = (StaggeredGridView) view.findViewById(R.id.grid_view);
        ptrFrame = (PtrFrameLayout) view.findViewById(R.id.fragment_home_ptr_frame);
        mLoadMoreGridViewContainer = (LoadMoreGridViewContainer) view.findViewById(R.id.load_more_grid_view_container);

        init();

        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_meizitu, menu);
        menu.findItem(R.id.menu_meizitu_photograph).setIcon(
                new IconDrawable(getContext(), Iconify.IconValue.fa_camera)
                        .colorRes(R.color.white)
                        .actionBarSize());
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_meizitu_photograph:
                //调用系统相机
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, 1);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    void init() {
        mMeizituListAdapter = new MeizituListAdapter();

        mMeizituApi = createApi(MeizituApi.class, "http://api.meitu.tv");


        ptrFrame.setLoadingMinTime(1000);
        ptrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mGridView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                getData(true);
            }
        });

        mLoadMoreGridViewContainer.useDefaultFooter();
        mLoadMoreGridViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
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

        mMeizituApi.list(G_currentPage, 20, "Default.Index", new DataCallback(isFirstPage));

        G_currentPage++;
    }

    private final class DataCallback extends ActivityCallback<MeizituListBean> {
        public DataCallback(Object obj) {
            super(obj);
        }

        @Override
        public void success(MeizituListBean requestBean, Response response) {
            boolean isFirstPage = (boolean) mObject;

            ptrFrame.refreshComplete();
            if (isFirstPage) {
                mMeizituListAdapter.clear();
                mMeizituListAdapter.addData(requestBean.getData().getList());
                mGridView.setAdapter(mMeizituListAdapter);
            } else {
                mMeizituListAdapter.addData(requestBean.getData().getList());
                mMeizituListAdapter.notifyDataSetChanged();
            }

            mLoadMoreGridViewContainer.loadMoreFinish(
                    requestBean.getData().getList().isEmpty(),
                    (Integer.valueOf(requestBean.getData().getPageNum()) < Integer.valueOf(requestBean.getData().getTotalPage()))
            );
        }

        @Override
        public void failure(RetrofitError error) {
            mMeizituListAdapter.setState(BaseListAdapter.STATE_NETWORK_ERROR);

            ptrFrame.refreshComplete();
        }
    }

}
