package com.demo.nd.test.fragment.meizitu;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.nd.test.R;
import com.demo.nd.test.api.MeizituApi;
import com.demo.nd.test.base.BaseApplication;
import com.demo.nd.test.base.BaseFragment;
import com.demo.nd.test.bean.MeizituListBean;
import com.demo.nd.test.fragment.adapter.MeizituListImplBGARefreshViewAdapter;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemLongClickListener;
import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Administrator on 2015/7/15.
 */
public class MeizituListBGAFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate, BGAOnRVItemClickListener, BGAOnRVItemLongClickListener {
    private BGARefreshLayout mRefreshLayout;
    RecyclerView mDataRv;
    MeizituApi mMeizituApi;
    int G_currentPage = 1;
    boolean G_hasMore = true;
    MeizituListImplBGARefreshViewAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_meizitu_bga_list, null);

        mRefreshLayout = (BGARefreshLayout) view.findViewById(R.id.rl_meizitu_refresh);
        mDataRv = (RecyclerView) view.findViewById(R.id.rv_recyclerview_data);

        initRefreshLayout();
        setHasOptionsMenu(true);
        return view;
    }

    private void initRefreshLayout() {
        mMeizituApi = createApi(MeizituApi.class, "http://api.meitu.tv");

        mRefreshLayout.setDelegate(this);
        mAdapter = new MeizituListImplBGARefreshViewAdapter(getContext());
        mAdapter.setOnRVItemClickListener(this);
        mAdapter.setOnRVItemLongClickListener(this);


        BGAMoocStyleRefreshViewHolder style = new BGAMoocStyleRefreshViewHolder(getContext(), true);
        style.setOriginalBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        style.setUltimateColor(getResources().getColor(R.color.day_colorPrimary));
        mRefreshLayout.setRefreshViewHolder(style);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mDataRv.setLayoutManager(layoutManager);

        mDataRv.setAdapter(mAdapter);


        mRefreshLayout.beginRefreshing();
    }

    void getData(boolean isFirstPage) {
        if (isFirstPage) {
            G_currentPage = 1;
        }

        mMeizituApi.list(G_currentPage, 20, 4, "Default.Index", new DataCallback(isFirstPage));

        G_currentPage++;
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {
        getData(true);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {

        getData(false);
        return G_hasMore;
    }

    @Override
    public void onRVItemClick(View view, int i) {
        BaseApplication.showToast("点击了条目 " + mAdapter.getItem(i).getTitle());
    }

    @Override
    public boolean onRVItemLongClick(View view, int i) {
        BaseApplication.showToast("点击了条目 " + mAdapter.getItem(i).getTitle());
        return false;
    }


    private final class DataCallback extends ActivityCallback<MeizituListBean> {
        public DataCallback(Object obj) {
            super(obj);
        }

        @Override
        public void success(MeizituListBean requestBean, Response response) {
            boolean isFirstPage = (boolean) mObject;

            if (requestBean.getData().getPageNum() == G_currentPage) {
                G_hasMore = false;
            }

            if (isFirstPage) {
                mRefreshLayout.endRefreshing();
                mAdapter.addNewDatas(requestBean.getData().getList());
                mDataRv.smoothScrollToPosition(0);
            } else {
                mRefreshLayout.endLoadingMore();
                mAdapter.addMoreDatas(requestBean.getData().getList());
            }
        }

        @Override
        public void failure(RetrofitError error) {
            mRefreshLayout.endRefreshing();
        }
    }
}
