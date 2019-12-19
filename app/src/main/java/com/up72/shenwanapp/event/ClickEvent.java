package com.up72.shenwanapp.event;

import android.view.View;

/**
 * 点击事件事件
 * Created by cwb on 2017/8/12.
 */
public class ClickEvent {
    public enum Type {


    }

    public Type type;
    public View view;
    public Object data;
    public String[] a;
    public int position;

    public ClickEvent(Type type, View view, Object data) {
        this.type = type;
        this.view = view;
        this.data = data;
    }


    public ClickEvent(Type type, View view, Object data, int position) {
        this.type = type;
        this.view = view;
        this.data = data;
        this.position = position;
    }

    public ClickEvent(Type type, View view, Object data, String[] a) {
        this.type = type;
        this.view = view;
        this.data = data;
        this.a = a;
    }
}