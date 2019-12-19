package com.up72.shenwanapp.task;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * response result
 * Created by cwb on 2016/12/12.
 */
class Result {
    @SerializedName(value = "status",alternate = "success")
    private String state = "";
    @SerializedName(value = "msg", alternate = "message")
    private String msg = "";
    private Object data;

    boolean isSuccess() {
        return state != null && state.equals("200")||  state != null && state.equals("true") ;
    }

    void setState(String state) {
        this.state = state;
    }

    String getMessage() {
        return msg;
    }

    void setMessage(String message) {
        this.msg = message;
    }

    String getData() {
        return new Gson().toJson(data);
    }

    void setData(Object data) {
        this.data = data;
    }

    public String getState() {
        return state;
    }
}