package com.up72.shenwanapp.ui;


import com.up72.shenwanapp.model.VersionModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 版本更新接口
 * Created by cwb on 2018/12/28.
 */
public interface VersionService {
    @FormUrlEncoded
    @POST("daily/app/version")
    Call<VersionModel> getVersionInfo(@Field("type") int type);
}