package com.example.administrator.myapplication.base;

import android.app.Application;
import android.content.Context;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;

public class MyApplication extends Application {
   public static  Context instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        Hawk.init(this).build();
    }
}
