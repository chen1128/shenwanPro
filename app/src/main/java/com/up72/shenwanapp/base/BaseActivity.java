package com.up72.shenwanapp.base;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.up72.library.utils.Log;
import com.up72.shenwanapp.R;
import com.up72.shenwanapp.event.ClickEvent;
import com.up72.shenwanapp.event.LoginEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * BaseActivity
 * Created by cwb on 2016/12/15.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Log log = new Log(getClass());
    private Toast toast;
    private Dialog mDialog;
    private ImageView ivLoading;
    private TextView tvLoading;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        EventBus.getDefault().register(this);
        setContentView(getContentView());
        initView();
        initListener();
        initData();
    }


    public void showToast(String msg) {
        cancelToast();
        if (toast == null) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    public void cancelToast() {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
    }

    protected void showLoading(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mDialog == null || tvLoading == null || ivLoading == null) {
                    View view = LayoutInflater.from(BaseActivity.this).inflate(R.layout.layout_loading, null, false);
                    tvLoading = (TextView) view.findViewById(R.id.tvContent);
                    ivLoading = (ImageView) view.findViewById(R.id.ivLoading);
                    mDialog = new Dialog(BaseActivity.this, R.style.dialog_transparent);
                    mDialog.setCancelable(false);
                    mDialog.setContentView(view);
                    Window win = mDialog.getWindow();
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    params.setMargins(0, 48, 0, 0);
                    if (win.getDecorView() != null)
                        win.getDecorView().setLayoutParams(params);
                    mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                        @Override
                        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                            if (keyCode == KeyEvent.KEYCODE_BACK) {
                                dialog.dismiss();
                                finish();
                            }
                            return false;
                        }
                    });
                }
                if (msg == null) {
                    tvLoading.setVisibility(View.GONE);
                } else {
                    tvLoading.setVisibility(View.VISIBLE);
                    tvLoading.setText(msg);
                }
//                Glide.with(BaseActivity.this).load(R.drawable.ic_loading).asGif()
//                        .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(ivLoading);
                if (!mDialog.isShowing()) {
                    mDialog.show();
                }
            }
        });
    }

    @LayoutRes
    protected abstract int getContentView();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initData();

    protected final void initTitle(String strContent) {
        this.initTitle(0, null, strContent, null, 0);
    }

    protected final void initTitle(int resLeft, String strContent) {
        this.initTitle(resLeft, null, strContent, null, 0);
    }

    protected final void initTitle(int resLeft, String strContent, String strRight) {
        this.initTitle(resLeft, null, strContent, strRight, 0);
    }

    protected final void initTitle(int resLeft, String strContent, int resRight) {
        this.initTitle(resLeft, null, strContent, null, resRight);
    }

    protected final void initTitle(String strLeft, String strContent, String strRight) {
        this.initTitle(0, strLeft, strContent, strRight, 0);
    }

    protected final void initTitle(int resLeft, String strLeft, String strContent, String strRight, int resRight) {
        ImageView ivTitleLeft = (ImageView) findViewById(R.id.iv_title_left);
        ImageView ivTitleRight = (ImageView) findViewById(R.id.iv_title_right);
        TextView tvTitleLeft = (TextView) findViewById(R.id.tv_title_left);
        TextView tvTitleContent = (TextView) findViewById(R.id.tv_title_content);
        TextView tvTitleRight = (TextView) findViewById(R.id.tv_title_right);

        if (ivTitleLeft != null) {
            if (resLeft == 0) {
                ivTitleLeft.setVisibility(View.GONE);
            } else {
                ivTitleLeft.setVisibility(View.VISIBLE);
                ivTitleLeft.setImageResource(resLeft);
                ivTitleLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickTitleLeft(v);
                    }
                });
            }
        }

        if (ivTitleRight != null) {
            if (resRight == 0) {
                ivTitleRight.setVisibility(View.GONE);
            } else {
                ivTitleRight.setVisibility(View.VISIBLE);
                ivTitleRight.setImageResource(resRight);
                ivTitleRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickTitleRight(v);
                    }
                });
            }
        }

        if (tvTitleLeft != null) {
            if (strLeft == null) {
                tvTitleLeft.setVisibility(View.GONE);
            } else {
                tvTitleLeft.setVisibility(View.VISIBLE);
                tvTitleLeft.setText(strLeft);
                tvTitleLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickTitleLeft(v);
                    }
                });
            }
        }
        if (tvTitleContent != null) {
            if (strContent == null) {
                tvTitleContent.setVisibility(View.GONE);
            } else {
                tvTitleContent.setVisibility(View.VISIBLE);
                tvTitleContent.setText(strContent);
            }
        }
        if (tvTitleRight != null) {
            if (strRight == null) {
                tvTitleRight.setVisibility(View.GONE);
            } else {
                tvTitleRight.setVisibility(View.VISIBLE);
                tvTitleRight.setText(strRight);
                tvTitleRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickTitleRight(v);
                    }
                });
            }
        }
    }

    protected void onClickTitleLeft(View v) {
        finish();
    }

    protected void onClickTitleRight(View v) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent event) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onClickEvent(ClickEvent event) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        Glide.with(this).resumeRequests();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Glide.with(this).pauseRequests();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.right_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}