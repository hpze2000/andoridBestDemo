package com.demo.nd.test.activity.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.nd.test.R;
import com.demo.nd.test.base.BaseListAdapter;
import com.demo.nd.test.bean.Bean;

/**
 * Created by Administrator on 2015/7/9.
 */
public class MainListAdapter extends BaseListAdapter<Bean.DataEntity.SeriesEntity> {

    @Override
    protected View getRealView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null || convertView.getTag() == null) {
            convertView = getLayoutInflater(parent.getContext()).inflate(
                    R.layout.list_cell_main, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Bean.DataEntity.SeriesEntity str = _data.get(position);


        vh.infoTitle.setText(str.getName());
        vh.imageIcon.setImageResource(R.drawable.quick_option_album_nor);

        return convertView;
    }

    static class ViewHolder {
        public TextView infoTitle;
        public ImageView imageIcon;
        public ViewHolder(View view) {
            infoTitle = (TextView) view.findViewById(R.id.tv_name);
            imageIcon = (ImageView) view.findViewById(R.id.iv_image);
        }
    }
}
