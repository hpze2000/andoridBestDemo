package com.demo.nd.test.fragment.adapter;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.nd.test.R;
import com.demo.nd.test.base.BaseListAdapter;
import com.demo.nd.test.bean.CnodejsTopicBean;
import com.demo.nd.test.bean.CnodejsTopicsBean;
import com.demo.nd.test.utils.LogUtils;
import com.demo.nd.test.utils.StringUtils;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by Administrator on 2015/7/9.
 */
public class CnodejsCommentAdapter extends BaseListAdapter<CnodejsTopicBean.DataEntity.RepliesEntity> {

    @Override
    protected View getRealView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null || convertView.getTag() == null) {
            convertView = getLayoutInflater(parent.getContext()).inflate(
                    R.layout.list_cell_cnodejs_comment, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        CnodejsTopicBean.DataEntity.RepliesEntity data = _data.get(position);


        String content = data.getContent();
        if (!TextUtils.isEmpty(content)) {
            vh.tv_content.setText(StringUtils.substring(StringUtils.Html2Text(content), 200, "utf-8"));
        }
        vh.tv_name.setText(data.getAuthor().getLoginname());
        String dateFormat = StringUtils.formatTZTime(data.getCreate_at());
        if (!TextUtils.isEmpty(dateFormat)) {
            vh.tv_time.setText(StringUtils.friendly_time(dateFormat));
        }

        try {
            String url = data.getAuthor().getAvatar_url().replace("/agent?url=", "");
            url = URLDecoder.decode(url, "utf-8");

            Picasso.with(parent.getContext()).load(url).into(vh.iv_avatar);
        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();

            LogUtils.log(CnodejsCommentAdapter.class.getName(), e.toString());
        }


        return convertView;
    }

    static class ViewHolder {
        public TextView tv_name, tv_time, tv_content, tv_from;
        public ImageView iv_avatar;
        public ViewHolder(View view) {
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_time = (TextView) view.findViewById(R.id.tv_time);
            tv_content = (TextView) view.findViewById(R.id.tv_content);
            tv_from = (TextView) view.findViewById(R.id.tv_from);
            iv_avatar = (ImageView) view.findViewById(R.id.iv_avatar);
        }
    }


}
