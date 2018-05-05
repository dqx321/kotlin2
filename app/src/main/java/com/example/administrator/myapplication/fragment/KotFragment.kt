package com.example.administrator.myapplication.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import com.example.administrator.myapplication.R
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder


// Created by CIDI daiqinxue on 2018/5/4.
class Kotfragment : Fragment() {
    lateinit var mcontext: Context;
    override fun onAttach(context: Context?) {
        mcontext = context!!;
        super.onAttach(context)

    }

    private val mList = ArrayList<String>()

    fun setData() {
        for (i in 0..9) {
            mList.add("" + i)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.kotfragment, container, false)
        val mrefresh: SmartRefreshLayout = view.findViewById(R.id.refreshlayout)
        val mrecycle: RecyclerView = view.findViewById(R.id.recyclerview)
        setData()
//        val adapter: NomalAdapter = NomalAdapter(mrecycle);
        mrecycle.setLayoutManager(LinearLayoutManager(mcontext, LinearLayoutManager.VERTICAL, false))
//        mrecycle.setAdapter(adapter);
//        adapter.setData(mList)

        mrecycle.setAdapter(object : CommonAdapter<String>(mcontext, R.layout.item_recycler, mList) {
            override fun convert(holder: ViewHolder?, t: String?, position: Int) {//return type kotlin.Unit
                holder!!.setText(R.id.tv_item, mList.get(position)+"")
            }
        })
        mrefresh.setOnRefreshListener(OnRefreshListener {
            mList.clear()
            setData()
            mrefresh.finishRefresh(2000/*,false*/);//传入false表示刷新失败

        })
        mrefresh.setOnLoadMoreListener(OnLoadMoreListener {
            setData()
            mrefresh.finishLoadMore(2000/*,false*/);//传入false表示刷新失败

        })

        return view
    }


}





