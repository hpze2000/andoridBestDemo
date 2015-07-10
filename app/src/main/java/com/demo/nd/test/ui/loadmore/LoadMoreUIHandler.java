package com.demo.nd.test.ui.loadmore;

/**
 * Created by Administrator on 2015/7/10.
 */
public interface LoadMoreUIHandler {
    public void onLoading(LoadMoreContainer container);

    public void onLoadFinish(LoadMoreContainer container, boolean empty, boolean hasMore);

    public void onWaitToLoadMore(LoadMoreContainer container);

    public void onLoadError(LoadMoreContainer container, int errorCode, String errorMessage);
}
