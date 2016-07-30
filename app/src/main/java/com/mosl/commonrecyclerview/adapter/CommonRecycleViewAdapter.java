package com.mosl.commonrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description 通用的adapter 可设置不同的item布局
 * @Author MoseLin
 * @Date 2016/7/21.
 */
public class CommonRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private List datas = new ArrayList<>();
    private Context context;
    private AdapterTemplate template;
    public CommonRecycleViewAdapter(Context context, List datas, AdapterTemplate template){
        this.context = context;
        this.datas = datas;
        this.template = template;
    }
    @Override
    public int getItemViewType(int position)
    {
        return template.getItemViewType().get(datas.get(position).getClass());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        try
        {
            return template.getViewHolder().get(viewType).getConstructor(View.class).newInstance(LayoutInflater.from(context).inflate(viewType,parent,false));
        } catch ( Exception e )
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        BaseViewHolder viewHolder = ( BaseViewHolder ) holder;
        viewHolder.bindViewHolder(datas.get(position));
    }

    @Override
    public int getItemCount()
    {
        return datas.size();
    }

    public interface AdapterTemplate
    {
        /**
         *
         * @return map, class is entity class , integer is layoutId
         */
        Map<Class<?>,Integer> getItemViewType();

        /**
         *
         * @return map, integer is layoutId,class is what extents BaseViewHolder
         */
        Map<Integer,Class<? extends BaseViewHolder>> getViewHolder();
    }
}
