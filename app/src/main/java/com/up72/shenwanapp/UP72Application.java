package com.up72.shenwanapp;

import android.app.Application;

import com.up72.library.UP72System;
import com.up72.library.utils.Log;
import com.up72.shenwanapp.manager.UserManager;


/**
 * Application
 * Created by cwb on 2018/12/9.
 */
public class UP72Application extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化用户信息类
        UserManager.getInstance().init(this);
        //根据当前运行环境开启和关闭日志（不用管）
        Log.debug = BuildConfig.DEBUG;
        //初始化相关功能
        UP72System.getInstance().init(this);
    }
}