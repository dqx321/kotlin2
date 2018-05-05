package com.example.administrator.myapplication.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Created by CIDI daiqinxue on 2018/5/3.
public class ExecutorUtil {

    private static ExecutorService service = Executors.newCachedThreadPool();

//    private static ThreadPoolExecutor threadPool = null;

    public static synchronized void execute(Runnable runner) {

        service.execute(runner);

    }


}