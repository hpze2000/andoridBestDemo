package com.demo.nd.test.fragment.adapter;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.nd.test.R;
import com.demo.nd.test.base.BaseListAdapter;
import com.demo.nd.test.bean.CnodejsTopicsBean;
import com.demo.nd.test.utils.StringUtils;

/**
 * Created by Administrator on 2015/7/9.
 */
public class CnodejsListAdapter extends BaseListAdapter<CnodejsTopicsBean.DataEntity> {

    @Override
    protected View getRealView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null || convertView.getTag() == null) {
            convertView = getLayoutInflater(parent.getContext()).inflate(
                    R.layout.list_cell_cnodejs, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        CnodejsTopicsBean.DataEntity data = _data.get(position);


        String dateFormat = StringUtils.formatTZTime(data.getCreate_at());
        if (!TextUtils.isEmpty(dateFormat)) {
            vh.tv_time.setText(StringUtils.friendly_time(dateFormat));

            if (data.isTop()) {
                vh.iv_tip.setImageResource(R.drawable.widget_top_icon);
            } else {
                if (StringUtils.isToday(dateFormat)) {
                    vh.iv_tip.setVisibility(View.VISIBLE);
                } else {
                    vh.iv_tip.setVisibility(View.GONE);
                }
            }
        }

        vh.tv_title.setText(data.getTitle());
        vh.tv_source.setText(data.getAuthor().getLoginname());
        vh.tv_comment_count.setText(String.valueOf(data.getReply_count()));

        String content = data.getContent();
        if (!TextUtils.isEmpty(content)) {
            vh.tv_description.setText(StringUtils.substring(StringUtils.Html2Text(content), 200, "utf-8"));
        }

        return convertView;
    }

    static class ViewHolder {
        public TextView tv_title, tv_description, tv_source, tv_time, tv_comment_count;
        public ImageView iv_tip;
        public ViewHolder(View view) {
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_description = (TextView) view.findViewById(R.id.tv_description);
            tv_source = (TextView) view.findViewById(R.id.tv_source);
            tv_time = (TextView) view.findViewById(R.id.tv_time);
            tv_comment_count = (TextView) view.findViewById(R.id.tv_comment_count);
            iv_tip = (ImageView) view.findViewById(R.id.iv_tip);
        }
    }
}
