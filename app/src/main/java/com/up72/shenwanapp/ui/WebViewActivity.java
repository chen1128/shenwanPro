package com.up72.shenwanapp.ui;

import android.webkit.WebView;

import com.up72.shenwanapp.R;
import com.up72.shenwanapp.base.BaseActivity;
import com.up72.shenwanapp.utils.WebController;


public class WebViewActivity extends BaseActivity implements WebController.Callback {
    public static final String WEB_URL = "WEB_URL";
    private WebController controller;

    @Override
    protected int getContentView() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initView() {
        WebView webView = (WebView) findViewById(R.id.webView);
        controller = new WebController(webView, this);
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            String url = getIntent().getExtras().getString(WEB_URL, null);
            if (url != null && url.length() > 0) {
                if (controller != null) {
                    controller.loadUrl(url);
                }
            }
        }
    }

    @Override
    public void loading(boolean show) {
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onFinish() {
        finish();
    }
}