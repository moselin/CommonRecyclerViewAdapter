package com.mosl.commonrecyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * @Description
 * @Author MoseLin
 * @Date 2016/7/21.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder
{
    BaseViewHolder(View itemView)
    {
        super(itemView);
        ButterKnife.bind(this,itemView);//bind 注解
    }

    public abstract void bindViewHolder(T data);
}
