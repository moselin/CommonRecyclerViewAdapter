package com.mosl.commonrecyclerview.adapter;

import android.view.View;
import android.widget.TextView;


import com.mosl.commonrecyclerview.R;
import com.mosl.commonrecyclerview.model.TestEntity;

import butterknife.Bind;

/**
 * @Description
 * @Author MoseLin
 * @Date 2016/7/21.
 */

public class TestViewHolder extends BaseViewHolder<TestEntity>
{
    @Bind( R.id.tvTitle)
    TextView tvTitle;
    @Bind( R.id.tvContext)
    TextView tvContext;
    public TestViewHolder(View itemView)
    {
        super(itemView);
    }

    @Override
    public void bindViewHolder(TestEntity data)
    {
        tvTitle.setText(data.title);
        tvContext.setText(data.content);
    }
}
