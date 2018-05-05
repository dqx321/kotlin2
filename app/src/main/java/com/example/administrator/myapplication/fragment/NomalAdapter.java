package com.example.administrator.myapplication.fragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.vise.common_base.adapter.BaseAdapter;
import com.vise.common_base.adapter.BaseViewHolder;
import com.vise.common_utils.log.LogUtils;

import java.util.ArrayList;
import java.util.List;

// Created by CIDI daiqinxue on 2018/5/4.

public class NomalAdapter extends RecyclerView.Adapter<NomalAdapter.RecyclerHolder> {
    private Context mContext;
    private List<String> dataList = new ArrayList<>();

    public NomalAdapter(RecyclerView recyclerView) {
        this.mContext = recyclerView.getContext();
    }

    public void setData(List<String> dataList) {
        if (null != dataList) {
            this.dataList.clear();
            this.dataList.addAll(dataList);
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        holder.textView.setText(dataList.get(position));
    }



    class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView textView;

        private RecyclerHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }




}
