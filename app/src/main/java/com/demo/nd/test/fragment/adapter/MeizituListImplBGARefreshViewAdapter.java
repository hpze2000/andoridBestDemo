package com.demo.nd.test.fragment.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ProgressBar;

import com.demo.nd.test.R;
import com.demo.nd.test.bean.MeizituListBean;
import com.etsy.android.grid.util.DynamicHeightImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by Administrator on 2015/7/15.
 */
public class MeizituListImplBGARefreshViewAdapter extends BGARecyclerViewAdapter<MeizituListBean.DataEntity.ListEntity> {

    public MeizituListImplBGARefreshViewAdapter(Context context) {
        super(context, R.layout.list_cell_meizitu);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, MeizituListBean.DataEntity.ListEntity model) {
        viewHolderHelper.setText(R.id.tv_title, model.getTitle());

        final ProgressBar progressBar = viewHolderHelper.getView(R.id.progressBar_PhotoView);
        ImageLoader.getInstance().displayImage(model.getPicPath(), (DynamicHeightImageView) viewHolderHelper.getView(R.id.iv_image), null, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if (loadedImage != null) {
                    DynamicHeightImageView imageView = (DynamicHeightImageView) view;
                    FadeInBitmapDisplayer.animate(imageView, 500);
                }

                progressBar.setVisibility(View.GONE);
            }
        });
    }
}