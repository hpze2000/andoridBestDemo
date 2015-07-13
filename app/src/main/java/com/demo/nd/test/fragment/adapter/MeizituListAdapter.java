package com.demo.nd.test.fragment.adapter;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.demo.nd.test.R;
import com.demo.nd.test.base.BaseListAdapter;
import com.demo.nd.test.bean.MeizituListBean;
import com.etsy.android.grid.util.DynamicHeightImageView;
import com.etsy.android.grid.util.DynamicHeightTextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/13.
 */
public class MeizituListAdapter extends BaseListAdapter<MeizituListBean.DataEntity.ListEntity> {

    static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

    @Override
    protected View getRealView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null || convertView.getTag() == null) {
            convertView = getLayoutInflater(parent.getContext()).inflate(
                    R.layout.list_cell_meizitu, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        MeizituListBean.DataEntity.ListEntity data = _data.get(position);


        vh.tv_title.setText(data.getTitle());
        final ProgressBar spinner = vh.progressBar_PhotoView;

        ImageLoader.getInstance().displayImage(data.getPicPath(), vh.iv_image, null, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if (loadedImage != null) {
                    DynamicHeightImageView imageView = (DynamicHeightImageView) view;
                    // 是否第一次显示
                    boolean firstDisplay = !displayedImages.contains(imageUri);
                    if (firstDisplay) {
                        // 图片淡入效果
                        FadeInBitmapDisplayer.animate(imageView, 500);
                        displayedImages.add(imageUri);
                    }
                }
                spinner.setVisibility(View.GONE);
            }
        });

        return convertView;
    }

    static class ViewHolder {
        public DynamicHeightTextView tv_title;
        public DynamicHeightImageView iv_image;
        public ProgressBar progressBar_PhotoView;
        public ViewHolder(View view) {
            tv_title = (DynamicHeightTextView) view.findViewById(R.id.tv_title);
            iv_image = (DynamicHeightImageView) view.findViewById(R.id.iv_image);
            progressBar_PhotoView = (ProgressBar) view.findViewById(R.id.progressBar_PhotoView);
        }
    }

}
