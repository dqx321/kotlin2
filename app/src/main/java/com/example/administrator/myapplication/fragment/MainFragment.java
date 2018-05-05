package com.example.administrator.myapplication.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.ClassBean;
import com.example.administrator.myapplication.util.ExecutorUtil;
import com.vise.common_utils.log.LogUtils;

import java.util.List;

// Created by CIDI daiqinxue on 2018/5/2.
public class MainFragment extends Fragment {
    public OnChangeMainActivityDatas1 changeData1;
    private static String TAG = MainFragment.class.getName();
    //当创建Fragment时被回调。该方法只会被调用一次；

    //当该Fragment被添加到Activity时被回调。该方法只会被调用一次；
    @Override
    public void onAttach(Activity activity) {
        LogUtils.e(TAG + "onAttach");
        mactivity = (Main2Activity) activity;
        super.onAttach(activity);
        changeData1 = (OnChangeMainActivityDatas1) activity;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtils.e(TAG + "onCreate");
        ExecutorUtil.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        super.onCreate(savedInstanceState);

    }

    public  void update(List<ClassBean> list) {

        LogUtils.e(TAG + "update");
    }

    Main2Activity mactivity;

    //每次创建、绘制该Fragment的View组件时回调该方法，Fragment将会显示该方法返回的View 组件；
    //切换回来要调用此方法，相当于onresume
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main2, container, false);
        LogUtils.e(TAG + "onCreateView");
        mactivity.getTitles();
        changeData1.ChangeDatas1("aa");
        return view;
    }


    //    当Fragment的宿主Activity被启动完成后回调该方法；//单独的
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        LogUtils.e(TAG + "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onStart() {
        LogUtils.e(TAG + "onStart");
        super.onStart();
    }

    @Override
    public void onPause() {
        LogUtils.e(TAG + "onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        LogUtils.e(TAG + "onStop");
        super.onStop();
    }

    //    销毁该Fragment所包含的View组件时调用；
    @Override
    public void onDestroyView() {
        LogUtils.e(TAG + "onDestroyView");
        super.onDestroyView();
    }

    //销毁Fragment时被回调。该方法只会被调用一次；
    @Override
    public void onDestroy() {
        LogUtils.e(TAG + "onDestroy");
        super.onDestroy();
    }

    //将Fragment从Activity中删除、替换完成时调用该方法。onDestroy()方法后一定会回调onDetach()方法。该方法只会被调用一次。
    @Override
    public void onDetach() {
        LogUtils.e(TAG + "onDetach");
        super.onDetach();
    }
}
