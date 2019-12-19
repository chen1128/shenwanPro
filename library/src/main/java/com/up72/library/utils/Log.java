package com.up72.library.utils;

/**
 * 日志工具
 * Created by cwb on 2015/8/18.
 */
public class Log {
    private String tag;
    public static boolean debug = false;

    public Log(String tag) {
        this.tag = "### UP72 ### " + tag;
    }

    public Log(Class<?> clazz) {
        this.tag = "### UP72 ### " + clazz.getName() + "-" + clazz.hashCode();
    }

    public void d(String msg) {
        if (debug) {
            android.util.Log.d(tag, msg);
        }
    }

    public void i(String msg) {
        if (debug) {
            android.util.Log.i(tag, msg);
        }
    }

    public void w(String msg) {
        if (debug) {
            android.util.Log.w(tag, msg);
        }
    }

    public void e(String msg) {
        if (debug) {
            android.util.Log.e(tag, msg);
        }
    }

    public void e(Throwable e) {
        if (debug) {
            android.util.Log.e(tag, "", e);
        }
    }

    /**
     * 截断输出日志
     * @param msg
     */
    public void e(String tag, String msg) {
        if (tag == null || tag.length() == 0
                || msg == null || msg.length() == 0)
            return;

        int segmentSize = 512;
        long length = msg.length();
        if (length <= segmentSize ) {// 长度小于等于限制直接打印
            android.util.Log.d(tag, msg);
        }else {
            while (msg.length() > segmentSize ) {// 循环分段打印日志
                String logContent = msg.substring(0, segmentSize );
                msg = msg.replace(logContent, "");
                android.util.Log.d(tag, logContent);
            }
            android.util.Log.d(tag, msg);// 打印剩余日志
        }
    }

}