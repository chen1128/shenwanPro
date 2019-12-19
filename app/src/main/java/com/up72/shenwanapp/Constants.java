package com.up72.shenwanapp;

/**
 * 常量类
 * Created by cwb on 2016/12/12.
 */
public class Constants {

    private static final String baseUrlOfOnline = "http://47.93.124.203/";
    private static final String baseUrlOfDebug = "http://47.93.124.203/";
    public static final String baseHostUrl = BuildConfig.DEBUG ? baseUrlOfDebug : baseUrlOfOnline;
    public static final String baseImageUrl = baseHostUrl;
}