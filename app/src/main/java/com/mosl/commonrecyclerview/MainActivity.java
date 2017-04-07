package com.mosl.commonrecyclerview;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;

import com.mosl.commonrecyclerview.adapter.BaseViewHolder;
import com.mosl.commonrecyclerview.adapter.CommonRecycleViewAdapter;
import com.mosl.commonrecyclerview.adapter.DividerItemDecoration;
import com.mosl.commonrecyclerview.adapter.TestViewHolder;
import com.mosl.commonrecyclerview.adapter.WechatViewHolder;
import com.mosl.commonrecyclerview.model.TestEntity;
import com.mosl.commonrecyclerview.model.WechatEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements CommonRecycleViewAdapter.AdapterTemplate
{

    @Bind(R.id.ryView)
    RecyclerView ryView;
    private CommonRecycleViewAdapter adapter;
    private List<Object> datas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new CommonRecycleViewAdapter(this,datas,this);
        ryView.setLayoutManager(new LinearLayoutManager(this));
        ryView.addItemDecoration(DividerItemDecoration.createHorizontalDivider(this,12));
        ryView.setAdapter(adapter);
        refreshUi();
    }

    public void refreshUi()
    {

        TestEntity testEntity = new TestEntity();
        testEntity.title = "测试头部";
        testEntity.content = "测试头部&测试头部*测试头部";
        datas.add(testEntity);
        for (int i = 0 ;i<10;i++){
            WechatEntity entity = new WechatEntity();
            entity.title = "item"+i;
            datas.add(entity);
        }

        adapter.notifyDataSetChanged();
    }



    @Override
    public Map<Class<?>, Integer> getItemViewType()
    {
        Map<Class<?>, Integer> map = new HashMap<>();
        map.put(WechatEntity.class,R.layout.main_item);
        map.put(TestEntity.class,R.layout.test_item);
        return map;
    }

    @Override
    public SparseArray<Class<? extends BaseViewHolder>> getViewHolder()
    {
        SparseArray<Class<? extends BaseViewHolder>> array = new SparseArray<>();
        array.put(R.layout.main_item, WechatViewHolder.class);
        array.put(R.layout.test_item, TestViewHolder.class);
        return array;
    }
}
