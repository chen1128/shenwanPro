package com.up72.library.widget;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.up72.library.R;

public class BigImgActivity extends AppCompatActivity{

    private ImageView ivBigPhoto;
    private String imageUrl = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_big_photo);
        ivBigPhoto = (ImageView) findViewById(R.id.ivBigPhoto);
        imageUrl = getIntent().getStringExtra("imageUrl");
        findViewById(R.id.layoutAct).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //关闭窗体动画显示
                overridePendingTransition(0,R.anim.activity_look_big_photo);
            }
        });
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initData() {
        Glide.with(this).load(imageUrl)
                .crossFade()
                .into(ivBigPhoto);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

}