package com.up72.shenwanapp.ui;

import android.view.View;
import android.widget.Button;

import com.up72.shenwanapp.R;
import com.up72.shenwanapp.base.BaseActivity;
import com.up72.shenwanapp.manager.RouteManager;
import com.up72.shenwanapp.widget.BannerView;

import java.util.HashMap;


public class BannerActivity extends BaseActivity {


    private BannerView bannerView;
    private String[] strings =new String[]{"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=4173875767,2016487392&fm=11&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3752957978,3563296968&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1744669125,3145495985&fm=26&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2046382758,1082789661&fm=26&gp=0.jpg"};

    @Override
    protected int getContentView() {
        return R.layout.activity_banner;
    }

    @Override
    protected void initView() {
        bannerView =findViewById(R.id.banner);
        bannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        bannerView.setDefaultRes(R.mipmap.ic_launcher);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        bannerView.setData(strings);
    }

}