package com.demo.nd.test.fragment.cnodejs;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.demo.nd.test.R;
import com.demo.nd.test.api.CnodejsApi;
import com.demo.nd.test.base.BaseFragment;
import com.demo.nd.test.bean.CnodejsTopicBean;
import com.demo.nd.test.fragment.adapter.CnodejsCommentAdapter;
import com.demo.nd.test.ui.empty.EmptyLayout;
import com.demo.nd.test.utils.StringUtils;
import com.demo.nd.test.utils.UiHelpUtils;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by johnson on 2015/7/11.
 */
public class CnodejsPageFragment extends BaseFragment {

    CnodejsApi mCnodejsApi;
    String id;
    CnodejsCommentAdapter mCnodejsCommentAdapter;

    EmptyLayout mEmptyLayout;
    WebView mWebView;
    ImageView iv_avatar;
    TextView tv_name, tv_from, tv_time;
    ListView lv_comment;
    SwipeRefreshLayout swiperefreshlayout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_cnodejs_page, null);

        mEmptyLayout = (EmptyLayout) view.findViewById(R.id.error_layout);
        lv_comment = (ListView) view.findViewById(R.id.lv_comment);
        swiperefreshlayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshlayout);
        swiperefreshlayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swiperefreshlayout.setRefreshing(false);
            }
        });

        Bundle bundle = (Bundle) mDataIn;
        id = bundle.getString("id");

        mCnodejsCommentAdapter = new CnodejsCommentAdapter();

        initListHeader();

        init();

        return view;
    }


    @Override
    public void onDestroyView() {
        if (mWebView != null) {
            mWebView.setVisibility(View.GONE);
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }

        super.onDestroyView();
    }

    void init() {
        mCnodejsApi = createApi(CnodejsApi.class, "https://cnodejs.org");

        swiperefreshlayout.setVisibility(View.VISIBLE);
        mEmptyLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
        mCnodejsApi.topic(id, new DataCallback(null));
    }

    void initListHeader() {
        View header = LayoutInflater.from(getActivity()).inflate(
                R.layout.list_header_cnodejs_detail, null);

        mWebView = (WebView) header.findViewById(R.id.webview);
        tv_name = (TextView) header.findViewById(R.id.tv_name);
        tv_time = (TextView) header.findViewById(R.id.tv_time);
        tv_from = (TextView) header.findViewById(R.id.tv_from);
        iv_avatar = (ImageView) header.findViewById(R.id.iv_avatar);

        lv_comment.addHeaderView(header);
    }


    private final class DataCallback extends ActivityCallback<CnodejsTopicBean> {
        public DataCallback(Object obj) {
            super(obj);
        }

        @Override
        public void success(CnodejsTopicBean requestBean, Response response) {

            UiHelpUtils.initWebView(mWebView, requestBean.getData().getContent());
            tv_name.setText(requestBean.getData().getTitle());
            tv_from.setText(requestBean.getData().getTab());

            String dateFormat = StringUtils.formatTZTime(requestBean.getData().getCreate_at());
            if (!TextUtils.isEmpty(dateFormat)) {
                tv_time.setText(StringUtils.friendly_time(dateFormat));
            }
            try {
                String url = requestBean.getData().getAuthor().getAvatar_url().replace("/agent?url=", "");
                url = URLDecoder.decode(url, "utf-8");

                Picasso.with(getContext()).load(url).error(R.drawable.page_icon_empty).into(iv_avatar);
            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
            }

            if (requestBean.getData().getReplies() != null) {
                mCnodejsCommentAdapter.addData(requestBean.getData().getReplies());
                lv_comment.setAdapter(mCnodejsCommentAdapter);
            }

            mEmptyLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
        }

        @Override
        public void failure(RetrofitError error) {
//            mEmptyLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
        }
    }

}
