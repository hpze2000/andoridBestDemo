package com.demo.nd.test.fragment.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.nd.test.R;
import com.demo.nd.test.base.BaseListAdapter;
import com.demo.nd.test.bean.CnodejsTopicsBean;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by Administrator on 2015/7/9.
 */
public class CnodejsListAdapter extends BaseListAdapter<CnodejsTopicsBean.DataEntity> {

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

        CnodejsTopicsBean.DataEntity data = _data.get(position);


        vh.infoTitle.setText(data.getTitle());

        try {
            String url = data.getAuthor().getAvatar_url().replace("/agent?url=", "");
            url = URLDecoder.decode(url, "utf-8");

            Picasso.with(parent.getContext()).load(url).into(vh.imageIcon);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


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
