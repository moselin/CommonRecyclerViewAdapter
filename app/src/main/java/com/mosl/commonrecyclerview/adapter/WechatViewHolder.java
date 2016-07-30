package com.mosl.commonrecyclerview.adapter;

import android.view.View;
import android.widget.TextView;


import com.mosl.commonrecyclerview.R;
import com.mosl.commonrecyclerview.model.WechatEntity;

import butterknife.Bind;

/**
 * @Description
 * @Author MoseLin
 * @Date 2016/7/21.
 */

public class WechatViewHolder extends BaseViewHolder<WechatEntity>
{
    @Bind( R.id.tvTitle)
    TextView tvTitle;
    @Bind( R.id.tvContext)
    TextView tvContext;
    public WechatViewHolder(View itemView)
    {
        super(itemView);
    }

    @Override
    public void bindViewHolder(WechatEntity data)
    {
        tvTitle.setText(data.title);
        tvContext.setText(data.source);
    }
}
