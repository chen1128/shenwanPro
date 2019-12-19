package com.up72.shenwanapp.model;

import android.support.annotation.Nullable;

/**
 * 说点什么
 * Created by cwb on 2016/12/19.
 */
public class UserModel {
    private long id;
    @Nullable
    private String name;

    private String cityCode="";
    private String jqInsuranceImg="";

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getJqInsuranceImg() {
        return jqInsuranceImg;
    }

    public void setJqInsuranceImg(String jqInsuranceImg) {
        this.jqInsuranceImg = jqInsuranceImg;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", jqInsuranceImg='" + jqInsuranceImg + '\'' +
                '}';
    }
}