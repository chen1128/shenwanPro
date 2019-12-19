package com.up72.shenwanapp.ui;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import com.bumptech.glide.Glide;
import com.up72.shenwanapp.R;
import com.up72.shenwanapp.base.BaseActivity;
import com.up72.shenwanapp.utils.MyJzvdStd;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;


public class VideoPlayActivity extends BaseActivity {
    private  MyJzvdStd jzvdStd;
    private  SensorManager mSensorManager;
    private  Jzvd.JZAutoFullscreenListener mSensorEventListener;
    @Override
    protected int getContentView() {
        return R.layout.activity_video;
    }

    @Override
    protected void initView() {
        jzvdStd = (MyJzvdStd) findViewById(R.id.jzVideo);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        jzvdStd.setUp("http://vfx.mtime.cn/Video/2019/03/18/mp4/190318231014076505.mp4"
                , "叶问4预告",Jzvd.SCREEN_FULLSCREEN);
        jzvdStd.startVideo();
//        Glide.with(this).load("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png").into(jzvdStd.thumbImageView);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorEventListener = new Jzvd.JZAutoFullscreenListener();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }


    //重力感应自动进入全屏

    @Override
    protected void onResume() {
        super.onResume();
//        Sensor accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        mSensorManager.registerListener(mSensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }



}