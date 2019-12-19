package com.up72.shenwanapp.ui;

import com.up72.shenwanapp.model.UserModel;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 店员接口
 * Created by cwb on 2018/12/28.
 */
public interface MainService {
    /*
    * 上线后的数据传输
    * */
    @FormUrlEncoded
    @POST("visitor/show/addpad")
    Observable<String> onlineAddpad(@Field("code") String code, @Field("name") String name);
}