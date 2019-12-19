package com.up72.shenwanapp.task;

/**
 * 自定义数据返回异常
 * Created by cwb on 2016/12/27.
 */
class ResultException extends RuntimeException {
    ResultException(String message) {
        super(message);
    }
}
